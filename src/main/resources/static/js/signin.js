const formulario = document.querySelector('form');

const Iemail = document.querySelector('.email');
const Ipassword = document.querySelector('.password');
const errorMessage = document.querySelector('.error-message');

function signin() {
    fetch("http://localhost:8080/users/signin", {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify({
            email: Iemail.value,
            password: Ipassword.value
        })
    })
    .then(async (res) => {

        if (res.status === 200) {
            const data = await res.json();

            if (data.token) {
                localStorage.setItem("token", data.token);
                window.location.href = "/home";
            }

            return;
        }

        if (res.status === 404) {
            return showError();
        }

    })
    .catch(err => {
        console.error(err);
    });
}

function showError() {
    errorMessage.classList.add('show');
    setTimeout(function() {
        errorMessage.classList.remove('show');
    }, 3000)
};

function clearFields() {
    Iemail.value = "";
    Ipassword.value = "";
};

formulario.addEventListener('submit', function(event) {
    event.preventDefault();
    signin();
    clearFields();
});
