package com.goofygoobers.chadchess.logic.pieces;

import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.Color;
import com.goofygoobers.chadchess.logic.SpecialMove;
import com.goofygoobers.chadchess.logic.V2;

import java.util.ArrayList;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }

    public ArrayList<V2> getValidMoves() {
        ArrayList<V2> moves = new ArrayList<>();
        moves.add( new V2(0, 1) );

        return moves;
    }
    public ArrayList<V2> getValidAttackMoves() {
        ArrayList<V2> moves = new ArrayList<>();
        moves.add( new V2(1, 1) );
        moves.add( new V2(-1, 1) );

        return moves;
    }

    public boolean goesStraight() {
        return false;
    }

    public boolean goesDiagonally() {
        return false;
    }

    @Override
    public SpecialMove validateSpecial(V2 start, V2 target, ChessBoard board) {
        SpecialMove type = null;
        V2 difference = target.subtract(start);

        if(COLOR == Color.WHITE) {
            difference.setY(difference.getY() * -1);
        }

        //double
        try {
            if(COLOR == Color.WHITE ? start.getY() == 6 : start.getY() == 1) {
                if(COLOR == Color.WHITE ? target.getY() == 4 : target.getY() == 3) {
                    if(difference.getX() == 0) {
                        if(!board.hasPieceAt(target) && !board.hasPieceInStraightRange(start, target)) {
                            type = SpecialMove.DOUBLE;
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


        //en passant
        //check if difference is a valid attack
        if(getValidAttackMoves().contains(difference)) {
            V2 victimV2 = new V2(start.getX() + difference.getX(), start.getY());

            //check if victim is a pawn
            if(board.getPieceAt(victimV2) instanceof Pawn) {
                Pawn victim = (Pawn) board.getPieceAt(victimV2);

                //check if target moved double
                if(victim.getMovesCounter() == 1) {

                    //check if piece advanced 3
                    if(COLOR == Color.WHITE ? start.getY() == 3 : start.getY() == 4) {
                        type = SpecialMove.EN_PASSANT;
                    }
                }
            }
        }

        //pawn promotion
        if(COLOR == Color.WHITE ? start.getY() == 1 && target.getY() == 0 : start.getY() == 6 && target.getY() == 7) {
            type = SpecialMove.PAWN_PROMOTION;
        }

        return type;
    }

    @Override
    public boolean doSpecial(V2 start, V2 target, ChessBoard board, SpecialMove type) {
        if(type == null) {
            return false;
        }

        switch (type) {
            case DOUBLE:
                break;
            case EN_PASSANT:
                V2 victim = new V2(target.getX(), start.getY());
                board.setPiece(victim, null);
                break;
            case PAWN_PROMOTION:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public String toString() {
        return COLOR == Color.WHITE ? "white/" +  "pawn" : "black/" +  "pawn";
    }
}
