package br.com.carlos.api.repository;

import br.com.carlos.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuario extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
