package com.goofygoobers.chadchess.logic;

import com.goofygoobers.chadchess.logic.pieces.*;

public class ChessBoard {
    public static final int SIZE = 8;
    private Piece[][] board = new Piece[SIZE][SIZE];
    public int turn = 0;
    private Color[] colors = {Color.WHITE, Color.BLACK};

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

    public boolean move(V2 start, V2 end) {
        if(!hasPieceAt(start) || getPieceAt(start).COLOR != colors[turn % 2]) {
            return false;
        }
        if(!getPieceAt(start).validateMove(start, end, this)) {
            return false;
        }

        //move and maybe take piece
        setPiece(end, getPieceAt(start));
        setPiece(start, null);

        turn++;
        return true;
    }

    private void setPiece(V2 pos, Piece piece) {
        board[pos.getX()][pos.getY()] = piece;
    }

    public Piece getPieceAt(V2 pos) {
        return board[pos.getX()][pos.getY()];
    }

    public boolean hasPieceAt(V2 pos) {
        return board[pos.getX()][pos.getY()] != null;
    }

    public boolean hasPieceInStraightRange(V2 start, V2 target) throws Exception {
        V2 difference = target.subtract(start);
        int dx = difference.getX();
        int dy = difference.getY();

        if((dx != 0 && dy != 0) || (start.equals(target))) {
            throw new Exception("Not a straight path!");
        }
        V2 dirrection = new V2(0, (dx + dy)/Math.abs(dx + dy));

        for(int i = 1; i < Math.abs(dx+dy); i++) {
            V2 pos = start.add(dirrection.multiply(i));

            if(hasPieceAt(pos)) {
                return true;
            }
        }

        return false;
    }

    public boolean hasPieceInDiagonalRange(V2 start, V2 target) throws Exception {
        V2 difference = target.subtract(start);
        int dx = difference.getX();
        int dy = difference.getY();

        if((Math.abs(dx) != Math.abs(dy)) || (start.equals(target))) {
            throw new Exception("Not a diagonal path!");
        }
        V2 dirrection = new V2((dx)/Math.abs(dx), (dy)/Math.abs(dy));

        for(int i = 1; i < dx; i++) {
            V2 pos = start.add(dirrection.multiply(i));

            if(hasPieceAt(pos)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String string = "";

        for(int y = 0; y < SIZE; y++) {
            string += y + " [";
            for(int x = 0; x < SIZE; x++) {
                if(board[x][y] == null) {
                    string += "          ";
                } else {
                    string += board[x][y].toString() + "   ";
                }
            }
            string += "]\n";
        }

        for(int x = -1; x < SIZE; x++) {
            string += x + "  ";
        }

        return string;
    }
}
