package com.goofygoobers.chadchess.web;
import com.goofygoobers.chadchess.ChessBoardWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import com.goofygoobers.chadchess.logic.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * GameController
 * The GameController class is in charge of handle HTTP requests from the client
 */
@Controller
public class GameController {

    @Autowired
    public SimpMessagingTemplate simpMessagingTemplate;



    @RequestMapping(value = "/validatemove", method = RequestMethod.GET)
    @ResponseBody
    public boolean validateMove(@RequestParam("id") int id, @RequestParam("s") String startStr, @RequestParam("t") String targetStr) {
        V2 start = stringToV2(startStr);
        V2 target = stringToV2(targetStr);

        return ChadchessApplication.getBoards().get(Integer.valueOf(id)).validateMove(start, target);
    }

    @RequestMapping(value = "/getvalidmoves", method = RequestMethod.GET)
    @ResponseBody
    public V2[] getValidMoves(@RequestParam("id") int id, @RequestParam("s") String startStr) {
        V2 start = stringToV2(startStr);
        LinkedList<V2> validMovedList = ChadchessApplication.getBoards().get(Integer.valueOf(id)).getValidMoves(start);
        V2[] validMovesArr = new V2[validMovedList.size()];
        Iterator<V2> validMovesListIterator = validMovedList.iterator();

        for(int i = 0; i < validMovesArr.length; i++) {
            validMovesArr[i] = validMovesListIterator.next();
        }

        return validMovesArr;
    }

    @RequestMapping(value = "/move", method = RequestMethod.GET)
    @ResponseBody
    public void move(@RequestParam("id") int id, @RequestParam("s") String startStr, @RequestParam("t") String targetStr) {
        V2 start = stringToV2(startStr);
        V2 target = stringToV2(targetStr);

        //move piece
        ChadchessApplication.getBoards().get(Integer.valueOf(id)).move(start, target);

        //send updated board to players
        ChessBoardWrapper board = ChadchessApplication.getBoards().get(Integer.valueOf(id));
        simpMessagingTemplate.convertAndSend("/board/" + id, board);
    }

    @RequestMapping(value = "/getboard", method = RequestMethod.GET)
    @ResponseBody
    public ChessBoardWrapper getBoard(@RequestParam("id") int id, HttpServletResponse response) {
        ChessBoardWrapper board;

        //create new chess board
        if(id == -1) {
            id = ChadchessApplication.getRand().nextInt(Integer.MAX_VALUE);
            board = new ChessBoardWrapper(id);
            ChadchessApplication.getBoards().put(Integer.valueOf(id), board);
        } else {
            board = ChadchessApplication.getBoards().get(Integer.valueOf(id));
        }

        return board;
    }

    @RequestMapping(value = "/getboards", method = RequestMethod.GET)
    @ResponseBody
    public int[] getBoards() {
        Iterator<Integer> idIterator = ChadchessApplication.getBoards().keys().asIterator();
        int[] boardsArr = new int[ChadchessApplication.getBoards().size()];

        for(int i = 0; idIterator.hasNext(); i++) {
            boardsArr[i] = idIterator.next().intValue();
        }

        return boardsArr;
    }

    private V2 stringToV2(String str) {
        String[] points = str.split(",");

        int x = Integer.parseInt(points[0]);
        int y = Integer.parseInt(points[1]);

        return new V2(x, y);
    }
}

