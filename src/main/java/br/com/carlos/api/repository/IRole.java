package br.com.carlos.api.repository;

import br.com.carlos.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRole extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
