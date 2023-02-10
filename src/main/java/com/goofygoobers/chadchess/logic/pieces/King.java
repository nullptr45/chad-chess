package com.goofygoobers.chadchess.logic.pieces;

<<<<<<< HEAD
import com.goofygoobers.chadchess.logic.Color;
import com.goofygoobers.chadchess.logic.V2;

import java.util.ArrayList;

public class King extends Piece{

    public King(Color color) {
        super(color);
    }

    public ArrayList<V2> getValidMoves() {
        ArrayList<V2> moves = new ArrayList<>();
        moves.add( new V2(1, 1) );
        moves.add( new V2(1, 0) );
        moves.add( new V2(1, -1) );
        moves.add( new V2(0, 1) );
        moves.add( new V2(0, -1) );
        moves.add( new V2(-1, 1) );
        moves.add( new V2(-1, 1) );
        moves.add( new V2(-1, 1) );

        return moves;
    }

    public ArrayList<V2> getValidAttackMoves() {
        ArrayList<V2> moves = new ArrayList<>();
        moves.add( new V2(1, 1) );
        moves.add( new V2(1, 0) );
        moves.add( new V2(1, -1) );
        moves.add( new V2(0, 1) );
        moves.add( new V2(0, -1) );
        moves.add( new V2(-1, 1) );
        moves.add( new V2(-1, 1) );
        moves.add( new V2(-1, 1) );

        return moves;
    }

    public boolean goesStraigt() {
        return false;
    }

    public boolean goesDiagonally() {
        return false;
=======
import com.goofygoobers.chadchess.logic.Board;
import com.goofygoobers.chadchess.logic.Piece;
import com.goofygoobers.chadchess.logic.Tile;

public class King extends Piece {
    public King(boolean firstTeam) {
        super(firstTeam);
>>>>>>> 78f7754daf9d08f4025f9deb0a7908c73502a144
    }

    @Override
    public String toString() {
<<<<<<< HEAD
        if(COLOR == Color.WHITE) {
            return "♔︎";
        } else {
            return "♚︎";
        }
    }
=======
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
    
>>>>>>> 78f7754daf9d08f4025f9deb0a7908c73502a144
}
