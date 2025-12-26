package br.com.carlos.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
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
                        .requestMatchers(HttpMethod.GET, "/home", "/signin", "/cadastro", "/error", "/css/**", "/js/**", "/img/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/usuarios").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/usuarios/**").hasRole("ADMIN")

                        .anyRequest().permitAll())

                .formLogin(form -> form.loginPage("/auth")
                        .failureUrl("/auth?erro=true")
                        .defaultSuccessUrl("/usuarios")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());


        return http.build();
    }
}
