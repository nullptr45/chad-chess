package com.goofygoobers.chadchess;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.goofygoobers.chadchess.logic.*;
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

    @RequestMapping(value = "/move", method = RequestMethod.GET)
    @ResponseBody
    public boolean move(@RequestParam("id") int id, @RequestParam("s") String startStr, @RequestParam("t") String targetStr) {
        V2 start = stringToV2(startStr);
        V2 target = stringToV2(targetStr);

        return ChadchessApplication.getBoards().get(Integer.valueOf(id)).move(start, target);
    }

    @RequestMapping(value = "/getBoard", method = RequestMethod.GET)
    @ResponseBody
    public ChessBoard getBoard(@RequestParam("id") int id) {
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

    private V2 stringToV2(String str) {
        String[] points = str.split(",");

        int x = Integer.parseInt(points[0]);
        int y = Integer.parseInt(points[1]);

        return new V2(x, y);
    }
}

