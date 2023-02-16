package com.goofygoobers.chadchess.logic.pieces;

import com.goofygoobers.chadchess.logic.Color;
import com.goofygoobers.chadchess.logic.V2;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(Color color) {
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

    public boolean goesStraight() {
        return true;
    }

    public boolean goesDiagonally() {
        return false;
    }

    @Override
    public String toString() {
        return COLOR == Color.WHITE ? "white/" + "rook" : "black/" + "rook";
    }
}
