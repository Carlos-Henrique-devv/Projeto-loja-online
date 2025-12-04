package br.com.carlos.api.service;

import br.com.carlos.api.dto.LoginRequest;
import br.com.carlos.api.model.Usuario;
import br.com.carlos.api.repository.IUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private IUsuario repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Usuario> listarUsuario() {
        List<Usuario> listarUsuario = repository.findAll();
        return listarUsuario;
    }

    public Usuario salvaUsuario(Usuario usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioSalvo = repository.save(usuario);
        return usuarioSalvo;
    }

    public Usuario editarUsuario(Usuario usuario) {
        String encoder = this.passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(encoder);
        Usuario usuarioEditado = repository.save(usuario);
        return usuarioEditado;
    }

    public boolean excluirUsuario(Integer id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Boolean validarSenha(LoginRequest loginRequest) {
        Optional<Usuario> usuarioOpt = repository.findByEmail(loginRequest.getEmail());


        if(usuarioOpt.isEmpty()) {
            return false;
        }

        Usuario usuarioDB = usuarioOpt.get();
        boolean senhaCorreta = passwordEncoder.matches(loginRequest.getSenha(), usuarioDB.getSenha());

        return senhaCorreta;
    }
}
