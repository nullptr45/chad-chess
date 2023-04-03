import * as React from "react";
import { Image } from "react-konva";

type PieceProps = {
    imageUrl: string,
    color: string,
    id: number,
};

function Piece(props: PieceProps) {
    return (
        <Image draggable image={undefined}></Image>
    )
}

export default Piece;
