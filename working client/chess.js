var canvas = document.getElementById("chess-board");
canvas.width = canvas.offsetWidth;
canvas.height = canvas.offsetHeight;
var board = getBoard(-1);
var ctx = canvas.getContext("2d");
var palette = {
    white: "rgb(250, 240, 230)",
    black: "rgb(20, 20, 20)"
};

//draw squares
var count = 0;
for(x = 0; x < 8; x++) {
    for(y = 0; y < 8; y++) {
        ctx.fillStyle = count % 2 == 0 ? palette.white : palette.black;

        var xStart = x*canvas.offsetWidth/8;
        var yStart = y*canvas.offsetHeight/8;
        var squareSize = canvas.offsetWidth/8;

        ctx.fillRect(xStart, yStart, squareSize, squareSize);

        console.log(x*canvas.offsetWidth/8);

        count++;
    }
    count--;
}

function getBoard(id) {
    fetch('http://localhost:8080/getboard?id=' + id)
        .then((response) => response.json())
        .then((data) => console.log(data));
}