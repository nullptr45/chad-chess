import * as React from "react";
import Game from '../model/chess';

type GameState = {
    game: Game,
    whiteWins: boolean,
    blackWins: boolean,
};

type GameProps = {
    isWhite: boolean,
};

class ChessGame extends React.Component<GameProps, GameState> {
    state = {
        game: new Game(this.props.isWhite),
        whiteWins: false,
        blackWins: false,
    };

    render() {
        return (
            <div></div>
        );
    }

    movePiece(selectedID: number, targetPosition: { x: number, y: number }) {
        
    }
}

export default ChessGame;