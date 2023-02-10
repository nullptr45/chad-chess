package com.goofygoobers.chadchess.logic.pieces;

import com.goofygoobers.chadchess.logic.Color;
import com.goofygoobers.chadchess.logic.V2;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
    }

    public ArrayList<V2> getValidMoves() {
        ArrayList<V2> moves = new ArrayList<>();

        return moves;
    }

    public ArrayList<V2> getValidAttackMoves() {
        ArrayList<V2> moves = new ArrayList<>();

        return moves;
    }

    public boolean goesStraigt() {
        return true;
    }

    public boolean goesDiagonally() {
        return true;
    }

    @Override
    public String toString() {
        if(COLOR == Color.WHITE) {
            return "♕";
        } else {
            return "♛︎";
        }
    }
}
