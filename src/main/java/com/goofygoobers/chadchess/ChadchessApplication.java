package com.goofygoobers.chadchess;

import com.goofygoobers.chadchess.logic.ChessBoard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChadchessApplication {

	public static ChessBoard board = new ChessBoard();

	public static void main(String[] args) {
		SpringApplication.run(ChadchessApplication.class, args);
	}

}
