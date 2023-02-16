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
        moves.add( new V2(-1, 1) );
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

        if(board.getPieceAt(target) instanceof Rook) {
            if(getMovesCounter() == 0 && board.getPieceAt(target).getMovesCounter() == 0) {
                if(!board.hasPieceInStraightRange(start, target)) {
                    if(!board.hasAttackInStraightRange(COLOR == Color.WHITE ? Color.BLACK : Color.WHITE ,start, target)) {
                        type = SpecialMove.CASTLING;
                    }
                }
            }
        }

        return type;
    }

    @Override
    public String toString() {
        return COLOR == Color.WHITE ? "white/" +  "king" : "black/" +  "king";
    }
}
