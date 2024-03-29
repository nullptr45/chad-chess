var board = {};

//load board
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
loadBoard(urlParams.get('id'));

//set up canvas
const canvas = document.getElementById("chess-board");
canvas.width = canvas.offsetWidth;
canvas.height = canvas.offsetHeight;
const ctx = canvas.getContext("2d");
ctx.webkitImageSmoothingEnabled = false;
ctx.imageSmoothingEnabled = false;
var palette = {
    white: "rgb(250, 240, 230)",
    black: "rgb(20, 20, 20)",
    highlight: "rgb(100, 100, 100, 0.5)"
};
var images = new Map();
var currentMove = {
    moving: false,
    startX: 0,
    startY: 0
};

function loadPieceImage(src) {
    image = new Image();
    image.src = "../assets/pieces/" + src + ".PNG";
    image.onload = () => {
        update();
    }

    images.set(src, image);
}

loadPieceImage("white/pawn");
loadPieceImage("white/rook");
loadPieceImage("white/knight");
loadPieceImage("white/bishop");
loadPieceImage("white/queen");
loadPieceImage("white/king");

loadPieceImage("black/pawn");
loadPieceImage("black/rook");
loadPieceImage("black/knight");
loadPieceImage("black/bishop");
loadPieceImage("black/queen");
loadPieceImage("black/king");



function update() {
    if(board.pieces === undefined) {
        return;
    }

    var count = 0;
    for(x = 0; x < 8; x++) {
        for(y = 0; y < 8; y++) {
            ctx.fillStyle = count % 2 == 0 ? palette.white : palette.black;

            var xStart = x*canvas.offsetWidth/8;
            var yStart = y*canvas.offsetHeight/8;
            var squareSize = canvas.offsetWidth/8;

            ctx.fillRect(xStart, yStart, squareSize, squareSize);
            if(board.pieces[x][y] !== null) {
                ctx.drawImage(images.get(board.pieces[x][y]), xStart + 5, yStart + 5, squareSize - 10, squareSize - 10);    
            }

            count++;
        }
        count--;
    }
}

function win() {
    canvas.remove();
    document.querySelector('#canvas-wrapper h1').textContent = board.winner + ' Won!'
}

async function loadBoard(id) {
    board = await getData('/getboard?id=' + id + '&player=1');
    console.log(board);
    window.history.pushState('page1', 'Title', '/game/index.html?id=' + board.id);
    connect(board.id);
    update();
}

async function validateMove(sx, sy, tx, ty) {
    result = await getData(`/validatemove?id=${board.id}&s=${sx},${sy}&t=${tx},${ty}`);
    return result;
}

async function move(sx, sy, tx, ty) {
    fetch(`/move?id=${board.id}&s=${sx},${sy}&t=${tx},${ty}`);
}

canvas.addEventListener("click", (ev) => {
    ctx.fillStyle = palette.highlight;
    x = ~~(ev.offsetX/canvas.offsetWidth*8);
    y = ~~(ev.offsetY/canvas.offsetHeight*8);
    
    if(!currentMove.moving) {
        highlightMoves(x, y);
        currentMove.moving = true;
        currentMove.startX = x;
        currentMove.startY = y;
    } else {
        move(currentMove.startX, currentMove.startY, x, y);
        currentMove.moving = false;
    }


});

async function highlightMoves(x, y) {
    var squareSize = canvas.offsetWidth/8;
    var validMovesArr = await getData(`/getvalidmoves?id=${board.id}&s=${x},${y}`);
    for(validMove of validMovesArr) {
        ctx.beginPath()
        ctx.arc((validMove.x+0.5)/8*canvas.offsetWidth, (validMove.y+0.5)/8*canvas.offsetHeight, squareSize*0.4, 0, 2 * Math.PI, false);
        ctx.fill();
        ctx.closePath();
    }

}