const formulario = document.querySelector('form');

const Inome = document.querySelector('.nome');
const Iusername = document.querySelector('.username');
const Isobrenome = document.querySelector('.sobrenome');
const Iemail = document.querySelector('.email');
const Isenha = document.querySelector('.senha');
const Itel = document.querySelector('.telefone');
const errorMessage = document.querySelector('.error-message');

function cadastrar() {

    fetch("http://localhost:8080/usuarios",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: Inome.value,
                username: Iusername.value,
                sobrenome: Isobrenome.value,
                email: Iemail.value,
                senha: Isenha.value,
                telefone: Itel.value
            })
        })
        .then(function (res) {
             if(res.ok) {
                window.location.href = "/signin"
            } else {
                mostrarErro();
            }
        })
};

function mostrarErro() {
    errorMessage.classList.add('show');
    setTimeout(function() {
        errorMessage.classList.remove('show');
    }, 3000)
};

function limparCampos() {
    Inome.value = "",
    Iusername.value = "",
    Isobrenome.value = "",
    Iemail.value = "",
    Isenha.value = "",
    Itel.value = ""
};

formulario.addEventListener('submit', function(event) {
    event.preventDefault();

    cadastrar();
    limparCampos();
});

