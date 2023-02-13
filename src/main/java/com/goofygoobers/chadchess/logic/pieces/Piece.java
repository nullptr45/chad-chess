package com.goofygoobers.chadchess.logic.pieces;

import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.Color;
import com.goofygoobers.chadchess.logic.V2;

import java.util.ArrayList;

public abstract class Piece {

    public final com.goofygoobers.chadchess.logic.Color COLOR;

    public abstract ArrayList<V2> getValidMoves();
    public abstract ArrayList<V2> getValidAttackMoves();

    public abstract boolean goesStraigt();
    public abstract boolean goesDiagonally();

    public boolean validateSpecial() {
        return true;
    }

    public Piece(com.goofygoobers.chadchess.logic.Color color) {
        this.COLOR = color;
    }

    public boolean validateMove(V2 start, V2 end, ChessBoard board) {
        V2 difference = end.subtract(start);

        if(start.equals(end) || !(end.getX() >= 0 && end.getX() < ChessBoard.SIZE && end.getY() >= 0 && end.getY() < ChessBoard.SIZE)) {
            return false;
        }

        //account for pieces being on different sides
        if(COLOR == Color.WHITE) {
            difference.setY(difference.getY() * -1);
        }

        boolean isValid = false;
        boolean isAttack = board.hasPieceAt(end);
        boolean isValidMove = getValidMoves().contains(difference);
        boolean isValidAttackMove = getValidAttackMoves().contains(difference);
        boolean isFriendlyAttack = isValidAttackMove && board.getPieceAt(start).COLOR == board.getPieceAt(end).COLOR;
        boolean isStraight = difference.getX() == 0 || difference.getY() == 0;
        boolean isDiagonal = Math.abs(difference.getX()) == Math.abs(difference.getY());
        boolean isValidStraight = false;
        boolean isValidDiagonal = false;
        try {
            if(isStraight && goesStraigt()) {
                isValidStraight = !board.hasPieceInStraightRange(start, end);
            }
            if(isDiagonal && goesDiagonally()) {
                isValidDiagonal = !board.hasPieceInDiagonalRange(start, end);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(isAttack) {
            if(!isFriendlyAttack) {
                if(isValidAttackMove) {
                    isValid = true;
                } else {
                    isValid = isValidStraight || isValidDiagonal;
                }
            }

        } else {
            isValid = isValidMove;
            if(isValidMove) {
                isValid = true;
            } else {
                isValid = isValidStraight || isValidDiagonal;
            }
        }

        if(validateSpecial()) {
            isValid = true;
        }

        return isValid;
    }
}
