package br.com.carlos.api.controller;

import br.com.carlos.api.dto.LoginRequestDto;
import br.com.carlos.api.dto.UserDto;
import br.com.carlos.api.model.User;
import br.com.carlos.api.repository.IUser;
import br.com.carlos.api.token.Token;
import br.com.carlos.api.token.TokenUtil;
import br.com.carlos.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;
    private final IUser iUser;

    public UserController(UserService userService, IUser iUser) {
        this.userService = userService;
        this.iUser = iUser;
    }


    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(200).body(userService.listUsers());
    }

    @PostMapping
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDto userDto) {
        try {
            return ResponseEntity.status(201).body(userService.updateUser(userDto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error ao cria o usuário");
        }
    }

    @PutMapping
    public ResponseEntity<?> toUpdate(@RequestBody @Valid UserDto userDto) {
        try {
            return ResponseEntity.status(201).body(userService.toUpdateUser(userDto));
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Erro ao altera o usuário");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        boolean deletado = userService.deleteUser(id);

        if (deletado) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> validPassword(@RequestBody LoginRequestDto loginRequestDto) {
        Boolean valid = userService.validPassword(loginRequestDto);
        Optional<User> optFindByEmail = iUser.findByEmail(loginRequestDto.getEmail());

        if (!valid) {
            return ResponseEntity.status(404).body("Senha ou email incorreto");
        }

        User user = optFindByEmail.get();
        String createToken = TokenUtil.createToken(user);
        Token token = new Token(createToken);

        return ResponseEntity.ok(token);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExeption(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fiedName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fiedName, errorMessage);
        });
        return errors;
    }
}
