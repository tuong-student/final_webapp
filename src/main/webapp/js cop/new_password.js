var password = document.getElementById("password").focus();
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
    const alert = document.querySelector(".alert"),
    alertText = document.querySelector(".alert__text"),
    password = document.getElementById("password"),
    re_password = document.getElementById("re_password"),
    form = document.querySelector("form");

    form.addEventListener("submit", submit);

    function submit(e) {
        e.preventDefault();
        password.addEventListener("keyup", showError);
        re_password.addEventListener("keyup", showError);
        showError();

        if (!alert.classList.contains("show")) {
            window.location.href = form.getAttribute("action");
        }
    }
    
    // email.addEventListener("keyup", showError);

    function showError() {
        if (password.value == "") {
            alert.classList.add("show");
            alertText.innerHTML = "Password không được để trống!";
            exit();
        } else if (password.value.length < 2) {
            alert.classList.add("show");
            alertText.innerHTML = "Password ít nhất 2 ký tự!";
            exit();
        } else {
            alert.classList.remove("show");
        }

        if (re_password.value == "") {
            alert.classList.add("show");
            alertText.innerHTML = "Repeat Password Không được để trống!";
            exit();
        } else if (re_password.value.length < 2) {
            alert.classList.add("show");
            alertText.innerHTML = "Repeat Password ít nhất 2 ký tự!";
            exit();
        } else {
            alert.classList.remove("show");
        }

        if (password.value !== re_password.value) {
            alert.classList.add("show");
            alertText.innerHTML = "Mật khẩu nhập lại không đúng!";
        } else {
            alert.classList.remove("show");
        }
    }
}
error();