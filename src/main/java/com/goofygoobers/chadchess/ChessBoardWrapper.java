package com.goofygoobers.chadchess;

import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.pieces.Piece;

import java.util.Arrays;

public class ChessBoardWrapper extends ChessBoard {
    public final int ID;
    public Piece[][] board;

    public ChessBoardWrapper(int id) {
        ID = id;
        board = super.getBoard();
    }

    @Override
    public String toString() {
        return "{" +
                "\"ID\":" + ID +
                ", \"board\":" + super.toString() +
                '}';
    }
}
