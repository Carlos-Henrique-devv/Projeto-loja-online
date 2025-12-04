package br.com.carlos.api.controller;

import br.com.carlos.api.dto.LoginRequest;
import br.com.carlos.api.model.Usuario;
import br.com.carlos.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario() {
        return ResponseEntity.status(200).body(usuarioService.listarUsuario());
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioService.salvaUsuario(usuario));
    }

    @PutMapping
    public ResponseEntity<Usuario> editarUsuario(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(201).body(usuarioService.editarUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Integer id) {
        boolean deletado = usuarioService.excluirUsuario(id);

        if (deletado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado");
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> validarSenha(@Valid @RequestBody LoginRequest loginRequest) {
        Boolean valid = usuarioService.validarSenha(loginRequest);

        if (!valid) {
            Map<String, String> error = new HashMap<>();
            error.put("Messagem", "Email ou senha invalida");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

        Map<String, String> sucess = new HashMap<>();
        sucess.put("Messagem", "Login realizado com sucesso!");
        return ResponseEntity.status(200).body(sucess);
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
