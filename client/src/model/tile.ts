class Tile {
    x: number;
    y: number;
    piece: ChessPiece;

    constructor(x: number, y: number) {
        this.x = x;
        this.y = y;
    }

    toString(): string {
        return this.x + "," + this.y;
    }
}