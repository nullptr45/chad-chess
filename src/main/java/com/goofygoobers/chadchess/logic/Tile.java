package com.goofygoobers.chadchess.logic;

/**
 * Tile
 * The Tile class represents one square on a chess board
 */
public class Tile {
    private int x, y;
    private Piece piece;

    /**
     * Constructor
     * @param x The X position on the board
     * @param y The X position on the board
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")" + ": " + (piece != null ? piece.toString() : "Empty");
    }

    /**
     * Gets the piece on the tile
     * @return Piece
     */
    public Piece getPiece() {
        return this.piece;
    }

    /**
     * Sets the piece on the tile
     * @param p the piece you being set
     */
    public void setPiece(Piece p) {
        this.piece = p;
    }

    /**
     * Gets the tile's x-coordinate
     * @return int
     */
    public int getX() {
        return this.x;
    }

    /**
     * Sets the tile's x-coordinate
     * @param x the x-coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the tile's y-coordinate
     * @return int
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the tile's y-coordinate
     * @param y the y-coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
}
