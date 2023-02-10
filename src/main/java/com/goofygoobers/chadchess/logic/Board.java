package com.goofygoobers.chadchess.logic;

import com.goofygoobers.chadchess.logic.pieces.King;

public class Board {
    Tile[][] tiles;

    public Board() {
        tiles = new Tile[8][8];
        reset();
    }

    public Tile getTileAt(int x, int y) {

        assert x < 0 || x > 7 || y < 0 || y > 7 : "Index out of bounds";

        return tiles[x][y];
    }

    public void reset() {
        // init white team
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }

        for (int i = 0; i < 8; i++) {
            tiles[0][i].setPiece(new King(true));
        }
        for (int i = 0; i < 8; i++) {
            tiles[1][i].setPiece(new King(true));
        }

        // init black team
        for (int i = 0; i < 8; i++) {
            tiles[7][i].setPiece(new King(false));
        }
        for (int i = 0; i < 8; i++) {
            tiles[6][i].setPiece(new King(false));
        }
    }
}
