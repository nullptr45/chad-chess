class ChessPiece {
    name: string;
    color: string;
    id: number;
    killed: boolean;

    constructor(name: string, id: number, killed: boolean, color: string) {
        this.name = name;
        this.id = id;
        this.killed = killed;
        this.color = color;
    }
}