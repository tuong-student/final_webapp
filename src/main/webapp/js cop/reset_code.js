var password = document.getElementById("otp").focus();
function error() {
    const alert = document.querySelector(".alert"),
    alertText = document.querySelector(".alert__text"),
    otp = document.getElementById("otp"),
    form = document.querySelector("form");

    form.addEventListener("submit", submit);

    function submit(e) {
        e.preventDefault();
        otp.addEventListener("keyup", showError);
        showError();

        if (!alert.classList.contains("show")) {
            window.location.href = form.getAttribute("action");
        }
    }
    

    // email.addEventListener("keyup", showError);

    function showError() {
        if (otp.value == "") {
            alert.classList.add("show");
            alertText.innerHTML = "OTP không được để trống!";
            
        } else if (otp.value.length < 6) {
            alert.classList.add("show");
            alertText.innerHTML = "OTP ít nhất 6 ký tự!";
        } else {
            alert.classList.remove("show");
        }
    }
}
error();