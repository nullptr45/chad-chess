package com.goofygoobers.chadchess.logic;

public abstract class Piece {
    private boolean alive = true;
    private boolean firstTeam = false;

    public Piece(boolean firstTeam) {
        this.firstTeam = firstTeam;
    }

    public boolean isFirstTeam() {
        return firstTeam;
    }

    public void setTeam(boolean first) {
        firstTeam = first;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setLife(boolean alive) {
        this.alive = alive;
    }

    public abstract boolean isMoveValid(Board board, Tile start, Tile end);
}
