const form = document.querySelector('form');

const Iname = document.querySelector('.name');
const Iusername = document.querySelector('.username');
const Isurname = document.querySelector('.surname');
const Iemail = document.querySelector('.email');
const Ipassword = document.querySelector('.password');
const Iphone = document.querySelector('.phone');

const IerrorMessage = document.querySelector('.error-message');

function register() {

    fetch("http://localhost:8080/users",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                name: Iname.value,
                username: Iusername.value,
                surname: Isurname.value,
                email: Iemail.value,
                password: Ipassword.value,
                phone: Iphone.value
            })
        })
        .then(async (res) => {

        if (res.status === 201) {
            window.location.href = "/signin";
            return;
        }

        if (res.status === 400) {
            showError();
            return;
        }

        })
        .catch(err => {
            console.error("Erro inesperado:", err);
        });
};

function showError() {
    IerrorMessage.classList.add('show');
    setTimeout(function() {
        errorMessage.classList.remove('show');
    }, 3000)
};

function clearFields() {
    Iname.value = "",
    Iusername.value = "",
    Isurname.value = "",
    Iemail.value = "",
    Ipassword.value = "",
    Iphone.value = ""
};

form.addEventListener('submit', function(event) {
    event.preventDefault();

    register();
    clearFields();
});

