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
    const id = await (await fetch(`/register-user?username=${username}&password=${password}`)).json()
    window.location.href = `${window.location.origin}/user/profile/index.html?id=${id}`;

});



