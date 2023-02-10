import * as React from "react";
import Board from "./board";

type GameProps = {
    // p1: string,
    // p2: string,
};

type GameState = {
    board: any[],
};

export default class Game extends React.Component<GameProps, GameState> {
    constructor(props: any) {
        super(props);

        let board = [];

        for (let y = 0; y < 8; y++) {
            let row = [];

            for (let x = 0; x < 8; x++) {
                row.push((y % 2) ^ (x % 2));
            }

            board.push(row);
        }

        this.state = {
            board: board,
        };
    }

    render(): React.ReactNode {
        return (
            <div className="chess">
                <Board board={this.state.board}></Board>
            </div>
        );
    }
}
