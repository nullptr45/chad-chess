import * as React from "react";
import Game from '../model/chess';

type GameState = {
    game: Game,
    whiteWins: boolean,
    blackWins: boolean,
    mousePos: MousePos
};

type GameProps = {
    isWhite: boolean,
};

type MousePos = {
    x: number,
    y: number
};

class ChessGame extends React.Component<GameProps, GameState> {
    state = {
        game: new Game(this.props.isWhite),
        whiteWins: false,
        blackWins: false,
        mousePos: { x: 0, y: 0}
    };

    mouseMoveCallback = (event: MouseEvent) => {
        this.state.mousePos = { x: event.clientX, y: event.clientY };
    };

    init() {
        window.addEventListener('mousemove', this.mouseMoveCallback);
    }

    render() {
        return (
            <div>
                The mouse is at position{' '}
                <b>
                    ({this.state.mousePos.x}, {this.state.mousePos.y})
                </b>
            </div>
        );
    }

    movePiece(selectedID: number, targetPosition: { x: number, y: number }) {

    }
}

export default ChessGame;