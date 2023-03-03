package com.goofygoobers.chadchess.logic.pieces;

import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.Color;
import com.goofygoobers.chadchess.logic.SpecialMove;
import com.goofygoobers.chadchess.logic.V2;

import java.util.ArrayList;

public class King extends Piece{

    public King(Color color) {
        super(color);
    }

    public ArrayList<V2> getValidMoves() {
        ArrayList<V2> moves = new ArrayList<>();
        moves.add( new V2(1, 1) );
        moves.add( new V2(1, 0) );
        moves.add( new V2(1, -1) );
        moves.add( new V2(0, 1) );
        moves.add( new V2(0, -1) );
        moves.add( new V2(-1, 1) );
        moves.add( new V2(-1, 1) );
        moves.add( new V2(-1, 1) );

        return moves;
    }

    public ArrayList<V2> getValidAttackMoves() {
        ArrayList<V2> moves = new ArrayList<>();
        moves.add( new V2(1, 1) );
        moves.add( new V2(1, 0) );
        moves.add( new V2(1, -1) );
        moves.add( new V2(0, 1) );
        moves.add( new V2(0, -1) );
        moves.add( new V2(-1, 1) );
        moves.add( new V2(-1, 0) );
        moves.add( new V2(-1, -1) );

        return moves;
    }

    @Override
    public boolean validateMove(V2 start, V2 target, ChessBoard board) {
        Color oppositeColor = COLOR == Color.WHITE ? Color.BLACK : Color.WHITE;
        boolean isInCheck = board.findAttackableSquares(oppositeColor)[target.getX()][target.getY()];

        return super.validateMove(start, target, board) && !isInCheck;
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

        if(board.getPieceAt(target) instanceof Rook) {
            if(getMovesCounter() == 0 && board.getPieceAt(target).getMovesCounter() == 0) {
                try {
                    if(!board.hasPieceInStraightRange(start, target)) {
                        if(!board.hasAttackInStraightRange(COLOR == Color.WHITE ? Color.BLACK : Color.WHITE ,start, target)) {
                            type = SpecialMove.CASTLING;
                        }
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return type;
    }

    @Override
    public boolean doSpecial(V2 start, V2 target, ChessBoard board, SpecialMove type) {
        if(type != SpecialMove.CASTLING) {
            return false;
        }

        int xDir = (target.getX()-start.getX())/Math.abs(target.getX()-start.getX());
        V2 kingEnd = new V2(start.getX() + xDir*2, start.getY());
        V2 rookEnd = new V2(kingEnd.getX() - xDir, start.getY());

        //move pieces
        board.setPiece(kingEnd, this);
        board.setPiece(rookEnd, board.getPieceAt(target));

        //remove old pieces
        board.setPiece(start, null);
        board.setPiece(target, null);

        return true;
    }

    @Override
    public String toString() {
        return COLOR == Color.WHITE ? "white/" +  "king" : "black/" +  "king";
    }
}
