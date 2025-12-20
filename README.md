# 🔐 Spring Security com Java 25 - Autenticação e Autorização com Roles + Token JWT + Fetch

Este projeto é um exemplo completo de aplicação web utilizando **Spring Boot**, **Spring Security**, **Java 25**, **Token JWT**, **Fetch com JS** e um banco de dados **MySQL**.  
Nesse projeto, eu usei as **roles** para autenticar rotas que o usuário comum não tem permissão para acessar, como a rota **/auth** e a rota **/usuarios**.  
Já o **JWT** é utilizado para gerar um token quando o usuário faz login.  
Atenção! O usuário que faz login na rota de autenticação não tem relação com o usuário que faz login e recebe o token.  
O usuário que faz login primeiro precisa se cadastrar no banco de dados. Neste caso, estamos utilizando um banco de dados **MySQL**, e vou mostrar mais à frente como criar esse banco de dados.  
Também vamos usar **Thymeleaf** para retornar nossos templates, e o controle de acesso será feito com **Spring MVC**.

---

## 🚀 Tecnologias utilizadas

- Java 25
- Spring boot
- Spring Security
- Thymefeaf
- Maven
- Jwt
- Spring data jpa
- Mysql
- Spring boot devtools
- Lombok

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
   │   │                       ├── Token.java
   │   │                       └── TokenUtil.java
   │   └── resources
   │       ├── application.properties
   │       ├── static
   │       │   ├── css
   │       │   │   ├── auth.css
   │       │   │   ├── cadastro.css
   │       │   │   ├── home.css
   │       │   │   └── signin.css
   │       │   ├── imgs
   │       │   │   ├── 360_F_567611992_70X7W94aXPfKYgUzVAcOEh03CcI7ivBC.jpg
   │       │   │   ├── calabresa.jpg
   │       │   │   ├── frango.jpg
   │       │   │   ├── garfo1.png
   │       │   │   ├── mms.jpg
   │       │   │   ├── mussarela.jpg
   │       │   │   └── pizzagrande1.png
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
                           └── ProjetoApiMotoCodeApplicationTests.java

                           
   ```


---

## ⚙️ Como executar

### Pré-requisitos

- Java 25
- Maven 3.8.3
- As outras tecnologia eu usei tudo no formato automatico no meu pom.xml

### Passo a passo

1. clone o repositorio

    ```bash
    git clone https://github.com/Carlos-Henrique-devv/Projeto-loja-online.git
    cd Projeto-loja-online

É necessário criar um banco de dados MySQL com o nome projeto_api.
Caso você já possua um banco com esse nome, crie outro banco com um nome diferente e atualize a configuração no arquivo application.properties.

O projeto utiliza as seguintes tabelas no banco de dados:

- usuario
- auth
- roles
- auth_roles

   ```
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

2. Acesse a pasta do projeto com Intellij
3. Espere baixar as dependências com Maven  
4. Execute o projeto  
5. Pode cadastra usuários e fazer login e só acessa as rota: localhost:8080/cadastro e fazer o cadastro depois o login na rota: localhost:8080/signin
6. Acesse: localhost:8080/usuarios, Coloque no User: "admin" e na senha "Admin123" e vai ver os usuários cadastrado
