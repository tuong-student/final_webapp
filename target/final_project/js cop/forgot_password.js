var email = document.getElementById("email").focus();
function error() {
    const alert = document.querySelector(".alert"),
    alertText = document.querySelector(".alert__text"),
    email = document.getElementById("email"),
    form = document.querySelector("form");

    form.addEventListener("submit", submit);

    function submit(e) {
        e.preventDefault();
        email.addEventListener("keyup", showError);
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
        } else if (!email.value.match(pattern)) {
            alert.classList.add("show");
            alertText.innerHTML = "Email không hợp lệ!";
        } else {
            alert.classList.remove("show");
        }
    }
}

error();