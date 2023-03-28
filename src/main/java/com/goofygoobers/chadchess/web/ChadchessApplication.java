package com.goofygoobers.chadchess.web;

import com.goofygoobers.chadchess.ChessBoardWrapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Hashtable;
import java.util.Random;

@SpringBootApplication
public class ChadchessApplication {

	private static Hashtable<Integer, ChessBoardWrapper> boards = new Hashtable<>();
	private static Hashtable<String, ChessBoardWrapper> sessionBoards = new Hashtable<>();
	private static Random rand = new Random();

	public static void main(String[] args) {
		SpringApplication.run(ChadchessApplication.class, args);
	}

	public static Hashtable<Integer, ChessBoardWrapper> getBoards() {
		return boards;
	}

	public static Random getRand() {
		return rand;
	}
}
