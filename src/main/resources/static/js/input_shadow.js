function get_shadow() {
    var email_message = document.getElementsByClassName("error");
    var email = document.getElementById("email");

    if (email_message != null) {
        email.classList.add("invalid");
    }
}
