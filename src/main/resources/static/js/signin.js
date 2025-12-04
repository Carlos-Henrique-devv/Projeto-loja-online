const formulario = document.querySelector('form');

const Iemail = document.querySelector('.email');
const Isenha = document.querySelector('.senha');
const errorMessage = document.querySelector('.error-message');

function signin() {
    fetch("http://localhost:8080/usuarios/signin", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            email: Iemail.value,
            senha: Isenha.value
        })
    })
    .then(function (res) {
        if (res.ok) {
            console.log("Login realizado com sucesso!");
            window.location.href = "/home";
        } else {
            console.log("Erro no login:", res);
            mostrarErro();
        }
        return;
    })
    .catch(function (error) {
        console.log("Erro na requisição:", error);
        mostrarErro();
    });
}

function mostrarErro() {
    errorMessage.classList.add('show');
    setTimeout(function() {
        errorMessage.classList.remove('show');
    }, 3000);
}

function limparCampos() {
    Iemail.value = "";
    Isenha.value = "";
}

formulario.addEventListener('submit', function(event) {
    event.preventDefault();
    signin();
    limparCampos();
});