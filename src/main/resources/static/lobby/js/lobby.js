loadBoards();

function joinBoard(ev) {
    console.log(ev.target);
}

async function loadBoards() {
    var response = await fetch('/getboards');
    var idArr = await response.json();
    idArr.sort();
    const boards = document.getElementById('boards');

    for(i = 0; i < idArr.length; i++) {
        boards.innerHTML += `<a class="board" href="/game/index.html?id=${idArr[i]}" >${idArr[i]}</a>`;
    }
}