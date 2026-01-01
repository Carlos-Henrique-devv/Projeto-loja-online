package br.com.carlos.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "surname", length = 200)
    private String surname;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 100, nullable = false, unique = true)
    private String password;

    @Column(name = "phone", length = 15, nullable = false, unique = true)
    private String phone;
}
