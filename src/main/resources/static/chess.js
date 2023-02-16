var canvas = document.getElementById("chess-board");
var board = {};
loadBoard(-1);
canvas.width = canvas.offsetWidth;
canvas.height = canvas.offsetHeight;
var ctx = canvas.getContext("2d");
ctx.webkitImageSmoothingEnabled = false;
ctx.imageSmoothingEnabled = false;
var palette = {
    white: "rgb(250, 240, 230)",
    black: "rgb(20, 20, 20)"
};
var images = new Map();

function loadPieceImage(src) {
    image = new Image();
    image.src = "/assets/pieces/" + src + ".PNG";
    image.onload = () => {
        //update();
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
    var count = 0;
    for(x = 0; x < 8; x++) {
        for(y = 0; y < 8; y++) {
            ctx.fillStyle = count % 2 == 0 ? palette.white : palette.black;

            var xStart = x*canvas.offsetWidth/8;
            var yStart = y*canvas.offsetHeight/8;
            var squareSize = canvas.offsetWidth/8;

            ctx.fillRect(xStart, yStart, squareSize, squareSize);
            if(board.pieces[x][y] != undefined) {
                ctx.drawImage(images.get(board.pieces[x][y]), xStart, yStart, squareSize, squareSize);    
            }

            count++;
        }
        count--;
    }
}

async function loadBoard(id) {
    tempBoard = await getData('http://localhost:8080/getboard?id=' + id).then((value) => {return value});
    console.log(tempBoard);
    board.id = tempBoard.ID;
    board.pieces = JSON.parse(tempBoard.JSON_BOARD);
    update();
}

async function getData(link) {
    var data;

    await fetch(link)
    .then((response) => {
        data = response.json();
    }).then((data) => {
        returnBoard = data;
    });

    return data;
}