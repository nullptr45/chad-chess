import * as React from "react";
import * as ReactDOM from "react-dom";

import ChessGame from "./ui/game";

ReactDOM.render(
    <div>
        <ChessGame isWhite={true}></ChessGame>
    </div>,
    document.getElementById("root")
);