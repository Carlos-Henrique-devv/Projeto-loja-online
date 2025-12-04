package br.com.carlos.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.GET, "/usuarios").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "usuarios").authenticated()

                        .anyRequest().permitAll())

                .formLogin(form -> form.loginPage("/auth").permitAll()
                        .defaultSuccessUrl("/usuarios"))
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
