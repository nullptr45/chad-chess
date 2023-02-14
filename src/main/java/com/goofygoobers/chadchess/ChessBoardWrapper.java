package com.goofygoobers.chadchess;

import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.pieces.Piece;

import java.util.Random;

public class ChessBoardWrapper extends ChessBoard {
    public final int ID;
    public final String JSON_BOARD;

    public ChessBoardWrapper(int id) {
        ID = id;
        JSON_BOARD = super.toString();
    }
}
