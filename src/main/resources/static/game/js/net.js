var stompClient;

function connect(id) {
    var socket = new SockJS('/websocket-endpoint');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/board/' + id, function (message) {
            board = JSON.parse(message.body);

            if(board.winner == undefined) {
                update();
            } else {
                win();
            }
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

async function getData(link) {
    var data;

    data = (await fetch(link)).json()

    return data;
}