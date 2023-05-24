var username = document.getElementById('username');
var bio = document.getElementById('bio');
var profile = document.getElementById("pp");
var user = {
    name: "Peter the second",
    bio: 'big thick boy',
    stats: {
        wins: 99,
        losses: 5
    }
}

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const id = urlParams.get('id');


const users = [
    {
        name: "Peter the zeroth",
        bio: 'big thick boy',
        stats: {
            wins: 99,
            losses: 5
        }
    }
    ,
    {
        name: "Peter the first",
        bio: 'big thick boy',
        stats: {
            wins: 99,
            losses: 5
        }
    }
    ,
    {
        name: "Peter the second",
        bio: 'big thick boy',
        stats: {
            wins: 99,
            losses: 5
        }
    }
];

username.innerText = users[id].name;
bio.innerText = users[id].bio;

function getUser() {

}
