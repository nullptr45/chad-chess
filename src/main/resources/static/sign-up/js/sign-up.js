var ui = {
    inputs: {
        name: document.getElementById("name"),
        email: document.getElementById("email"),
    },
    buttons: {
        signUp: document.getElementById("sign-up"),
    }
}

ui.buttons.signUp.addEventListener("click", (ev) => {
    var name = ui.inputs.name.value;
    var email = ui.inputs.email.value;
    
    //build and send request to sign up
    const req = new XMLHttpRequest();
    req.addEventListener("load", () => {
        console.log(req.response);
    });
    req.open("GET", `/registeruser?name=${name}&email=${email}`);
    req.send();
});



