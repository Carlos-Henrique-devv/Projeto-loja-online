package br.com.carlos.api.repository;

import br.com.carlos.api.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserAuth extends JpaRepository<UserAuth, Integer> {
    Optional<UserAuth> findByUsername(String username);
}
