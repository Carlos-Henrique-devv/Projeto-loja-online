package br.com.carlos.api.repository;

import br.com.carlos.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUser extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
