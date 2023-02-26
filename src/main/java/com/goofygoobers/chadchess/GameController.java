package com.goofygoobers.chadchess;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import com.goofygoobers.chadchess.logic.*;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * GameController
 * The GameController class is in charge of handle HTTP requests from the client
 */
@Controller
public class GameController {

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
    public boolean move(@RequestParam("id") int id, @RequestParam("s") String startStr, @RequestParam("t") String targetStr) {
        V2 start = stringToV2(startStr);
        V2 target = stringToV2(targetStr);

        return ChadchessApplication.getBoards().get(Integer.valueOf(id)).move(start, target);
    }

    @RequestMapping(value = "/getboard", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getBoard(@RequestParam("id") int id, HttpServletResponse response) {
        ChessBoardWrapper board;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type", "application/json");

        //create new chess board
        if(id == -1) {
            id = ChadchessApplication.getRand().nextInt(Integer.MAX_VALUE);
            board = new ChessBoardWrapper(id);
            ChadchessApplication.getBoards().put(Integer.valueOf(id), board);
        } else {
            board = ChadchessApplication.getBoards().get(Integer.valueOf(id));
        }

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(board.toString());
    }

    private V2 stringToV2(String str) {
        String[] points = str.split(",");

        int x = Integer.parseInt(points[0]);
        int y = Integer.parseInt(points[1]);

        return new V2(x, y);
    }
}

