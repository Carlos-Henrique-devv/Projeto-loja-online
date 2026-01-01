package br.com.carlos.api.service;

import br.com.carlos.api.model.UserAuth;
import br.com.carlos.api.repository.IUserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserAuth iUserAuth;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserAuth userAuth = iUserAuth.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado")
                );

        return new org.springframework.security.core.userdetails.User(
                userAuth.getUsername(),
                userAuth.getPassword(),
                userAuth.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .toList()
        );
    }
}
