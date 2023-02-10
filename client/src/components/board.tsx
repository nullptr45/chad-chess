import * as React from "react";

type BoardProps = {
    board: any[],
};

const Board: React.FC<BoardProps> = (props) => {
    let rows: any[] = [];

    props.board.forEach((row, index) => {
        const cols = row.map((column: any, index: number) => <div className={`col-${column}`} key={index} />);

        rows.push(<div className="chess-board__row" key={index}>{cols}</div>);
    })
 
    return (
        <div className="chess-board">
            <div className="chess-board__info">
                <p className="chess-board__text">Works!</p>
                <div className="chess-board__board">{rows}</div>
            </div>
        </div>
    );
}

export default Board;