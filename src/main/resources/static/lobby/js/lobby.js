loadBoards();

function joinBoard(ev) {
    console.log(ev.target);
}

async function loadBoards() {
    var idArr = await getData('/getboards').then((value) => {return value});
    idArr.sort();
    const boards = document.getElementById('boards');

    for(i = 0; i < idArr.length; i++) {
        boards.innerHTML += `<a class="board" href="/?id=${idArr[i]}" >${idArr[i]}</a>`;
    }
}