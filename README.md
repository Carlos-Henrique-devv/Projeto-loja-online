# 🔐 Spring Security com Java 25 – Autenticação e Autorização com Roles + Token JWT + Fetch

---

## 📌 Sobre o projeto

Este projeto é um exemplo completo de aplicação web utilizando **Spring Boot**, **Spring Security**, **Java 25**, **Token JWT**, **Fetch com JS** e um banco de dados **MySQL**.  
Neste projeto, utilizei roles para autenticar rotas que o usuário comum não tem permissão para acessar, como as rotas (`/auth`) e (`/usuarios`).  
O **JWT** é utilizado para gerar um token quando o usuário realiza o login. Atenção! O usuário que faz login na rota de autenticação, nesse caso a rota de autenticação
e (`/auth`), não tem relação com o usuário que faz login na rota de login que é a rota (`/signin`).  
O usuário que faz login na rota (`/signin`), primeiro precisa se cadastrar no banco de dados. então ele acessa a rota (`/cadastro`)  e faz o cadastro do usuário.  
Neste caso estamos utilizando um banco de dados **MySQL**, e vou mostrar mais em frente como criar esse banco de dados.  
Já as nossas **ROLES** são criada via CommandLineRunner e só da run no projeto ela já criada, Vamos usar **Thymeleaf** para retornar nossos templates, e o controle de acesso será feito com **Spring MVC**.

---

## 🚀 Tecnologias utilizadas

- Java 25
- Spring Boot
- Spring Security
- Thymeleaf
- Maven
- JWT (JSON WEb TOKEN)
- Spring Data Jpa
- MySQL
- Spring Boot Devtools
- Lombok

---

## 🔒 Segurança

## 🔒 Segurança

- Senhas armazenadas de forma criptografada utilizando BCrypt
- Geração de token JWT no processo de login
- Autenticação de credenciais realizada via Controller no Back-end
- Autorização baseada em ROLES utilizando Spring Security
- Controle de acesso às rotas feito com base nas ROLES do usuário
- Rotas sensíveis protegidas pelo Spring Security

Este projeto retorna o token JWT na resposta de login.  
Em aplicações de produção, recomenda-se o uso de cookies HttpOnly para armazenamento do token.

---

## 📂 Estrutura do projeto

   ```
src/
├── main
│   ├── java
│   │   └── br
│   │       └── com
│   │           └── carlos
│   │               └── api
│   │                   ├── ProjetoApiApplication.java
│   │                   ├── controller
│   │                   │   ├── AppController.java
│   │                   │   └── UsuarioController.java
│   │                   ├── dto
│   │                   │   └── LoginRequest.java
│   │                   ├── model
│   │                   │   ├── Role.java
│   │                   │   ├── UserAuth.java
│   │                   │   └── Usuario.java
│   │                   ├── repository
│   │                   │   ├── IRole.java
│   │                   │   ├── IUserAuth.java
│   │                   │   └── IUsuario.java
│   │                   ├── security
│   │                   │   └── SecurityConfig.java
│   │                   ├── service
│   │                   │   ├── UserDetailsServiceImpl.java
│   │                   │   └── UsuarioService.java
│   │                   └── token
│   │                       ├── Token.java
│   │                       └── TokenUtil.java
│   └── resources
│       ├── application.properties
│       ├── static
│       │   ├── css
│       │   │   ├── auth.css
│       │   │   ├── cadastro.css
│       │   │   ├── home.css
│       │   │   └── signin.css
│       │   ├── img
│       │   │   ├── calabresa.jpg
│       │   │   └── header
│       │   │       ├── garfo1.png
│       │   │       ├── imager-header.jpg
│       │   │       └── menu-harburger.png
│       │   └── js
│       │       ├── cadastro.js
│       │       ├── home.js
│       │       └── signin.js
│       └── templates
│           ├── auth.html
│           ├── cadastro.html
│           ├── error.html
│           ├── home.html
│           └── signin.html
└── test
    └── java
        └── br
            └── com
                └── carlos
                    └── api
                        └── ProjetoApiApplicationTests.java

   ```

---

## ⚙️ Configuração do application.properties

    spring.application.name=PROJETO-API
    spring.datasource.username = root
    spring.datasource.password = password
    spring.datasource.url = jdbc:mysql://localhost:3306/projeto_api
    spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL8Dialect

---

## ⚙️ Como executar

### Pré-requisitos

- Java 25
- Maven 3.8.3
- git

### Passo a passo

1. clone o repositorio

    ```bash
    git clone https://github.com/Carlos-Henrique-devv/Projeto-loja-online.git
    cd Projeto-loja-online

2. Cria DB + tablelas + colunas padrão.

    script:
    ```
      create database projeto_api;
    
      use projeto_api;
    
      create table usuario(
         id INTEGER PRIMARY KEY AUTO_INCREMENT,  
         nome VARCHAR(200) NOT NULL,  
         nomecomleto VARCHAR(200),  
         username VARCHAR(100) NOT NULL UNIQUE,  
         email VARCHAR(50) NOT NULL UNIQUE,  
         senha VARCHAR(100) NOT NULL UNIQUE,  
         telefone VARCHAR(15) NOT NULL UNIQUE  
      );
    
      create table roles(
         id INTEGER PRIMARY KEY AUTO_INCREMENT, 
         name VARCHAR(100) NOT NULL NULL UNIQUE
      );
    
      create table auth(
         id INTEGER PRIMARY KEY AUTO_INCREMENT, 
         username VARCHAR(100) NOT NULL UNIQUE,
         senha VARCHAR(100) NOT NULL
      );
    
      CREATE TABLE auth_roles (
       auth_id INT NOT NULL,
       role_id INT NOT NULL,
       PRIMARY KEY (auth_id, role_id)
       );
   ```
3. Agora e só copia esse script é cola no seu servidor MySQL
4. Acessa o projeto e da um run
5. Funcionalidades logo abaixo no fluxo de autenticação

---

## 🔐 Fluxo de autenticação

1. O usuário se cadastra no sistema (`/cadastro`)

2. O usuário faz login (`/signin`)

3. O backend valida as credenciais

4. Um token JWT é gerado

5. O token é enviado para o frontend

6. O usuário é autenticado com sucesso

7. Ao acessar (`/usuarios`), a rota só pode ser acessada por usuários que tenham autorização

8. Caso não esteja autorizado, será redirecionado para a rota (`/auth`), página de interceptação do Spring Security

10. Usuário padrão autenticado: User: admin, Senha: Admin123.

11. Será possível visualizar o usuário cadastrado que você criou
