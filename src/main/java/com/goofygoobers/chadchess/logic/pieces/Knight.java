package com.goofygoobers.chadchess.logic.pieces;

import com.goofygoobers.chadchess.logic.Color;
import com.goofygoobers.chadchess.logic.V2;

import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
    }

    public ArrayList<V2> getValidMoves() {
        ArrayList<V2> moves = new ArrayList<>();
        moves.add( new V2(2, 1) );
        moves.add( new V2(2, -1) );
        moves.add( new V2(-2, 1) );
        moves.add( new V2(-2, -1) );
        moves.add( new V2(1, 2) );
        moves.add( new V2(1, -2) );
        moves.add( new V2(-1, 2) );
        moves.add( new V2(-1, -2) );

        return moves;
    }

    public ArrayList<V2> getValidAttackMoves() {
        ArrayList<V2> moves = new ArrayList<>();
        moves.add( new V2(2, 1) );
        moves.add( new V2(2, -1) );
        moves.add( new V2(-2, 1) );
        moves.add( new V2(-2, -1) );
        moves.add( new V2(1, 2) );
        moves.add( new V2(1, -2) );
        moves.add( new V2(-1, 2) );
        moves.add( new V2(-1, -2) );

        return moves;
    }

    public boolean goesStraigt() {
        return false;
    }

    public boolean goesDiagonally() {
        return false;
    }

    @Override
    public String toString() {
        return "knight";
    }
}
