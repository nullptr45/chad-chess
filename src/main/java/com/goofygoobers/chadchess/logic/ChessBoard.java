package com.goofygoobers.chadchess.logic;

import com.goofygoobers.chadchess.logic.pieces.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Arrays;
import java.util.LinkedList;

public class ChessBoard {
    public static final int SIZE = 8;
    private Piece[][] board = new Piece[SIZE][SIZE];
    private int turn = 0;
    private Color[] colors = {Color.WHITE, Color.BLACK};
    private String winner;

    public ChessBoard() {
        //add white pawns
        for(int i = 0; i < SIZE; i++) {
            board[i][6] = new Pawn(Color.WHITE);
        }

        //add bishops
        board[2][7] = new Bishop(Color.WHITE);
        board[5][7] = new Bishop(Color.WHITE);

        board[2][0] = new Bishop(Color.BLACK);
        board[5][0] = new Bishop(Color.BLACK);

        //add knights
        board[1][7] = new Knight(Color.WHITE);
        board[6][7] = new Knight(Color.WHITE);

        board[1][0] = new Knight(Color.BLACK);
        board[6][0] = new Knight(Color.BLACK);

        //add Rooks
        board[0][7] = new Rook(Color.WHITE);
        board[7][7] = new Rook(Color.WHITE);

        board[0][0] = new Rook(Color.BLACK);
        board[7][0] = new Rook(Color.BLACK);

        //add Queens
        board[3][7] = new Queen(Color.WHITE);
        board[3][0] = new Queen(Color.BLACK);

        //add Queens
        board[4][7] = new King(Color.WHITE);
        board[4][0] = new King(Color.BLACK);

        //add black pawns
        for(int i = 0; i < SIZE; i++) {
            board[i][1] = new Pawn(Color.BLACK);
        }
    }

    public Piece[][] getBoard() {
        return board;
    }

    public boolean move(V2 start, V2 target) {
        Piece piece = getPieceAt(start);
        if(piece == null || getPieceAt(start).COLOR != colors[turn % 2] || !validateMove(start, target)) {
            return false;
        }

        if(getPieceAt(target) instanceof King) {
            winner = getPieceAt(start).COLOR == Color.WHITE ? "White" : "Black";
        }

        getPieceAt(start).doSpecial(start, target, this, getPieceAt(start).validateSpecial(start, target, this));

        //move and maybe take piece
        setPiece(target, getPieceAt(start));
        setPiece(start, null);

        piece.iterateMovesCounter();
        turn++;
        return true;
    }

    public boolean validateMove(V2 start, V2 target) {
        if(getPieceAt(start) == null) {
            return false;
        }
        return getPieceAt(start).validateMove(start, target, this);
    }

    public LinkedList<V2> getValidMoves(V2 start) {
        LinkedList<V2> validMoves = new LinkedList<>();
        Piece piece = getPieceAt(start);

        for(int x = 0; x < SIZE; x++) {
            for(int y = 0; y < SIZE; y++) {
                V2 target = new V2(x, y);

                if(target != null && piece.validateMove(start, target, this)) {
                    validMoves.add(target);
                }
            }
        }

        return validMoves;
    }

    public void setPiece(V2 pos, Piece piece) {
        board[pos.getX()][pos.getY()] = piece;
    }

    public Piece getPieceAt(V2 pos) {
        return board[pos.getX()][pos.getY()];
    }

    public String getWinner() {
        return winner;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean hasPieceAt(V2 pos) {
        return getPieceAt(pos) != null;
    }

    public boolean hasPieceInStraightRange(V2 start, V2 target) throws Exception {
        V2 difference = target.subtract(start);
        int dx = difference.getX();
        int dy = difference.getY();

        V2 direction = dx == 0 ? new V2(0, dy/Math.abs(dy)) : new V2(dx/Math.abs(dx), 0);

        //check if the path is straight
        if(direction.getX() != 0 && direction.getY() != 0) {
            throw new Exception("Not a straight path!");
        }

        //check if the path contains a piece
        for(int i = 1; i < Math.abs(dx+dy); i++) {
            V2 pos = start.add(direction.multiply(i));

            if(pos.getX() >= SIZE || pos.getX() < 0 || pos.getY() >= SIZE || pos.getY() < 0) {
                break;
            } else if(hasPieceAt(pos)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasPieceInDiagonalRange(V2 start, V2 target) throws Exception {
        V2 difference = target.subtract(start);
        int dx = difference.getX();
        int dy = difference.getY();

        //check if the path is diagonal
        if((Math.abs(dx) != Math.abs(dy)) || (start.equals(target))) {
            throw new Exception("Not a diagonal path!");
        }
        V2 direction = new V2((dx)/Math.abs(dx), (dy)/Math.abs(dy));

        //check if the path contains a piece
        for(int i = 1; i < Math.abs(dx); i++) {
            V2 pos = start.add(direction.multiply(i));

            if(hasPieceAt(pos)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasAttackInStraightRange(Color color, V2 start, V2 target) {
        V2 difference = target.subtract(start);
        int dx = difference.getX();
        int dy = difference.getY();
        boolean[][] field = findAttackableSquares(color);

        V2 direction = dx == 0 ? new V2(0, dy/Math.abs(dy)) : new V2(dx/Math.abs(dx), 0);

        for(int i = 1; i < Math.abs(dx+dy); i++) {
            V2 pos = start.add(direction.multiply(i));

            if(field[pos.getX()][pos.getY()]) {
                return true;
            }
        }

        return false;
    }

    public boolean[][] findAttackableSquares(Color color) {
        boolean[][] field = new boolean[SIZE][SIZE];

        //loop through fields
        fieldLoop:
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {

                //loop through pieces
                for(int x = 0; x < SIZE; x++) {
                    for(int y = 0; y < SIZE; y++) {

                        //check if piece can attack field
                        Piece piece = getPieceAt(new V2(x, y));

                        if(piece != null && piece.COLOR == color) {
                            V2 start = new V2(x, y);
                            V2 target = new V2(i, j);

                            if(piece.validateAttackVector(start, target, this)) {
                                field[i][j] = true;
                                continue fieldLoop;
                            }
                        }
                    }
                }
            }
        }

        return field;
    }

    @Override
    public String toString() {
        String string = "[";
        for(int i = 0; i < SIZE; i++) {
            Piece[] x = board[i];
            string += "[";
            for(int j = 0; j < SIZE; j++) {
                Piece p = x[j];

                if(p != null) {
                    string += "\"" + p.toString() + "\"";
                } else {
                    string += "null";
                }
                string += j < SIZE - 1 ? "," : "";
            }
            string += i < SIZE - 1 ? "]," : "]";
        }

        return string + "]";
    }
}
