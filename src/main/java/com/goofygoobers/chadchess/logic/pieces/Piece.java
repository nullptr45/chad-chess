package com.goofygoobers.chadchess.logic.pieces;

import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.Color;
import com.goofygoobers.chadchess.logic.SpecialMove;
import com.goofygoobers.chadchess.logic.V2;

import java.util.ArrayList;

public abstract class Piece {
    private int movesCounter = 0;

    public final com.goofygoobers.chadchess.logic.Color COLOR;

    public int getMovesCounter() {
        return movesCounter;
    }
    public void iterateMovesCounter() {
        movesCounter++;
    }

    public abstract ArrayList<V2> getValidMoves();
    public abstract ArrayList<V2> getValidAttackMoves();

    public abstract boolean goesStraight();
    public abstract boolean goesDiagonally();

    public SpecialMove validateSpecial(V2 start, V2 target, ChessBoard board) {
        return null;
    }
    public boolean doSpecial(V2 start, V2 target, ChessBoard board, SpecialMove type) {
        return false;
    }

    public Piece(com.goofygoobers.chadchess.logic.Color color) {
        this.COLOR = color;
    }

    public boolean validateMove(V2 start, V2 target, ChessBoard board) {
        V2 difference = target.subtract(start);

        if(start.equals(target) || !(target.getX() >= 0 && target.getX() < ChessBoard.SIZE && target.getY() >= 0 && target.getY() < ChessBoard.SIZE)) {
            return false;
        }

        //account for pieces being on different sides
        if(COLOR == Color.WHITE) {
            difference.setY(difference.getY() * -1);
        }

        boolean isValid = false;
        boolean isAttack = board.hasPieceAt(target);
        boolean isValidMove = getValidMoves().contains(difference);
        boolean isValidAttackMove = getValidAttackMoves().contains(difference) && board.getPieceAt(target) != null;
        boolean isFriendlyAttack = (board.getPieceAt(target) != null && board.getPieceAt(start).COLOR == board.getPieceAt(target).COLOR);
        boolean isStraight = difference.getX() == 0 || difference.getY() == 0;
        boolean isDiagonal = Math.abs(difference.getX()) == Math.abs(difference.getY());
        boolean isValidStraight = false;
        boolean isValidDiagonal = false;
        try {
            if(isStraight && goesStraight()) {
                isValidStraight = !board.hasPieceInStraightRange(start, target);
            }
            if(isDiagonal && goesDiagonally()) {
                isValidDiagonal = !board.hasPieceInDiagonalRange(start, target);
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
            if(isValidMove) {
                isValid = true;
            } else {
                isValid = isValidStraight || isValidDiagonal;
            }
        }

        //check for special move
        SpecialMove specialMove = validateSpecial(start, target, board);
        if(!isValid && specialMove != null) {
            isValid = true;
            doSpecial(start, target, board, specialMove);
        }

        return isValid;
    }

    public boolean validateAttackVector(V2 start, V2 target, ChessBoard board) {
        V2 difference = target.subtract(start);

        if(start.equals(target) || !(target.getX() >= 0 && target.getX() < ChessBoard.SIZE && target.getY() >= 0 && target.getY() < ChessBoard.SIZE)) {
            return false;
        }

        //account for pieces being on different sides
        if(COLOR == Color.WHITE) {
            difference.setY(difference.getY() * -1);
        }

        boolean isValid = false;
        boolean isValidAttackMove = getValidAttackMoves().contains(difference);
        boolean isStraight = difference.getX() == 0 || difference.getY() == 0;
        boolean isDiagonal = Math.abs(difference.getX()) == Math.abs(difference.getY());
        boolean isValidStraight = false;
        boolean isValidDiagonal = false;
        try {
            if(isStraight && goesStraight()) {
                isValidStraight = !board.hasPieceInStraightRange(start, target);
            }
            if(isDiagonal && goesDiagonally()) {
                isValidDiagonal = !board.hasPieceInDiagonalRange(start, target);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        if(isValidAttackMove) {
            isValid = true;
        } else {
            isValid = isValidStraight || isValidDiagonal;
        }

        return isValid;
    }
}
