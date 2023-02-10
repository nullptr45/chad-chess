package com.goofygoobers.chadchess.logic.pieces;

import com.goofygoobers.chadchess.logic.Board;
import com.goofygoobers.chadchess.logic.Piece;
import com.goofygoobers.chadchess.logic.Tile;

public class King extends Piece {
    public King(boolean firstTeam) {
        super(firstTeam);
    }

    @Override
    public String toString() {
        return "King";
    }

    @Override
    public boolean isMoveValid(Board board, Tile start, Tile end) {
        if (end.getPiece().isFirstTeam() == this.isFirstTeam()) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if (x + y == 1) {
            return true;
        }

        return false;
    }
    
}
