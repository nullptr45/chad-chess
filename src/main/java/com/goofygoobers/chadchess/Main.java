package com.goofygoobers.chadchess;

import com.goofygoobers.chadchess.logic.ChessBoard;
import com.goofygoobers.chadchess.logic.V2;

import java.util.Scanner;

//this class is only intended for testing with an ascii gui
public class Main {
    public static void main(String[] args) {
        ChessBoard board = new ChessBoard();
        Scanner scn = new Scanner(System.in);

        while(true) {
            try {
                String[] cls = new String[] {"cmd.exe", "/c", "cls"};
                Runtime.getRuntime().exec(cls);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(board);
            int sx = scn.nextInt();
            int sy = scn.nextInt();

            int tx = scn.nextInt();
            int ty = scn.nextInt();

            V2 start = new V2(sx, sy);
            V2 target = new V2(tx, ty);

            board.move(start, target);
        }
    }
}
