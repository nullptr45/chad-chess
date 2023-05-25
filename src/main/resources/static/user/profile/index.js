var username = document.getElementById('username');
var bio = document.getElementById('bio');
var pfp = document.getElementById("img");

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');

async function getUser() {
    user = await (await fetch(`/get-user?id=${id}`)).json();
    return user;
}

async function loadUser(id) {
    user = await (await fetch(`/get-user?id=${id}`)).json();
    username.innerText = user.name;
    bio.innerText = user.bio;
    pfp.src = user.pfp;
}

loadUser(id);
