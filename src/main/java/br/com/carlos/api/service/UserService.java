package br.com.carlos.api.service;

import br.com.carlos.api.dto.LoginRequestDto;
import br.com.carlos.api.dto.UserDto;
import br.com.carlos.api.model.User;
import br.com.carlos.api.repository.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final IUser iUser;
    private final PasswordEncoder passwordEncoder;

    public UserService(IUser iUser) {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.iUser = iUser;
    }

    public List<User> listUsers() {
        return iUser.findAll();
    }

    public User updateUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        return iUser.save(user);
    }

    public User toUpdateUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPhone(userDto.getPhone());
        return iUser.save(user);
    }

    public boolean deleteUser(Integer id) {
        if (iUser.existsById(id)) {
            iUser.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Boolean validPassword(LoginRequestDto loginRequestDto) {
        Optional<User> usuarioOpt = iUser.findByEmail(loginRequestDto.getEmail());

        if (usuarioOpt.isEmpty()) {
            return false;
        }

        User userDB = usuarioOpt.get();
        boolean senhaCorreta = passwordEncoder.matches(loginRequestDto.getPassword(), userDB.getPassword());

        return senhaCorreta;
    }
}
