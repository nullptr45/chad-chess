package com.goofygoobers.chadchess;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.pieces.Piece;

import java.util.Arrays;

public class ChessBoardWrapper extends ChessBoard {
    public final int ID;

    public ChessBoardWrapper(int id) {
        ID = id;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + ID +
                ", \"pieces\":" + super.toString() +
                ", \"winner\":\"" + super.getWinner() + "\"" +
                '}';
    }
}
