package com.goofygoobers.chadchess;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.pieces.Piece;
import org.springframework.ui.context.support.DelegatingThemeSource;

import java.util.Arrays;

public class ChessBoardWrapper extends ChessBoard {
    @JsonProperty("id")
    public final int ID;

    public ChessBoardWrapper(int id) {
        ID = id;
    }

    @JsonProperty("pieces")
    public String[][] getPieces() {
        String[][] strPieces = new String[SIZE][SIZE];
        Piece[][] pieces = super.getBoard();

        for(int x = 0; x < SIZE; x++) {
            for(int y = 0; y < SIZE; y++) {
                if(pieces[x][y] != null) {
                    strPieces[x][y] = pieces[x][y].toString();
                } else {
                    strPieces[x][y] = null;
                }

            }
        }

        return strPieces;
    }

    @JsonProperty("winner")
    @Override
    public String getWinner() {
        return super.getWinner();
    }

    @JsonIgnore
    @Override
    public Piece[][] getBoard() {
        return super.getBoard();
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + ID +
                ", \"pieces\":" + super.toString() +
                ", \"winner\":\"" + super.getWinner() + "\"" +
                "}";
    }
}
