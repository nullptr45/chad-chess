package com.goofygoobers.chadchess;

import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.V2;
import com.goofygoobers.chadchess.logic.pieces.Pawn;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PawnTests {
	V2 start = new V2(0, 6);

	@Test
	void pieceTest() {
		ChessBoard board = new ChessBoard();
		assert(board.getPieceAt(start) instanceof Pawn);
	}

	@Test
	void rangeTest1() {
		ChessBoard board = new ChessBoard();
		assert(!board.move(start, new V2(1, 6)));
	}

	@Test
	void rangeTest2() {
		ChessBoard board = new ChessBoard();
		assert(!board.move(start, new V2(-1, 6)));
	}

	@Test
	void rangeTest3() {
		ChessBoard board = new ChessBoard();
		assert(!board.move(start, new V2(0, 7)));
	}

	@Test
	void rangeTest4() {
		ChessBoard board = new ChessBoard();
		assert(board.move(start, new V2(0, 5)));
	}
}
