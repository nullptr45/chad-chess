package com.goofygoobers.chadchess.logic;

import com.goofygoobers.chadchess.logic.pieces.Piece;

public class ChessBoard {
    private Piece[][] board = new Piece[8][8];

    public ChessBoard() {

    }

    public boolean move(V2 pos) {
        return false;
    }

    private Piece getPiece(V2 pos) {
        return board[pos.getX()][pos.getY()];
    }
}
