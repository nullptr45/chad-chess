var ui = {
    inputs: {
        username: document.getElementById("username"),
        password: document.getElementById("password"),
    },
    buttons: {
        signUp: document.getElementById("sign-up"),
    }
}

ui.buttons.signUp.addEventListener("click", (ev) => {
    var username = ui.inputs.username.value;
    var password = ui.inputs.password.value;
    
    //build and send request to sign up
    const req = new XMLHttpRequest();
    req.addEventListener("load", () => {
        console.log(req.response);
    });
    req.open("GET", `/register-user?username=${username}&password=${password}`);
    req.send();
});



