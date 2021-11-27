var email = document.getElementById("email").focus();
function showHidePassword() {
    const password = document.querySelectorAll(".password"),
    iconEye = document.querySelector(".form__icon.eye"),
    iconEyeSrc = document.querySelector(".form__icon.eye img");

    iconEye.addEventListener("click", showPass);
    
    function showPass() {
        password.forEach(function (el) {
            if (el.type == "password") {
                iconEyeSrc.src = "assets/eye.svg";
                el.type = "text";
            } else {
                iconEyeSrc.src = "assets/eye-off.svg";
                el.type = "password";
            }
        });
    }
}
showHidePassword();

function error() {
    const field = document.querySelectorAll(".field"),
    alert = document.querySelector(".alert"),
    alertText = document.querySelector(".alert__text"),
    email = document.getElementById("email"),
    password = document.getElementById("password"),
    form = document.querySelector("form");

    field.forEach(function (el) {
        el.addEventListener("keyup", removeError);

        function removeError() {
            alert.classList.remove("show");
        }
    })

    form.addEventListener("submit", submit);

    function submit(e) {
        e.preventDefault();
        email.addEventListener("keyup", showError);
        password.addEventListener("keyup", showError);
        showError();
        
        if (!alert.classList.contains("show")) {
            window.location.href = form.getAttribute("action");
        }
    }
    
    // email.addEventListener("keyup", showError);
    function showError() {
        let pattern = /^[A-Za-z][\w$.]+@[\w]+\.\w+$/;
        if (email.value == "") {
            alert.classList.add("show");
            alertText.innerHTML = "Email không được để trống!";
            exit();
        } else if (!email.value.match(pattern)) {
            alert.classList.add("show");
            alertText.innerHTML = "Email không hợp lệ!";
            exit();
        } else {
            alert.classList.remove("show");
        }
        
        if (password.value == "") {
            alert.classList.add("show");
            alertText.innerHTML = "Pass không được để trống!";
            exit();
            
        } else if (password.value.length < 2) {
            alert.classList.add("show");
            alertText.innerHTML = "Mật khẩu ít nhất 2 ký tự!";
            exit();
        } else {
            alert.classList.remove("show");
        }
    }
}
error();