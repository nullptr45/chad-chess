package com.goofygoobers.chadchess;
import org.springframework.web.bind.annotation.*;

import com.goofygoobers.chadchess.logic.*;

/**
 * GameController
 * The GameController class is in charge of handle HTTP requests from the client
 */
@RestController
public class GameController {
    private Board board;

    GameController() {
        board = new Board();
    }

    @GetMapping("/test")
    public @ResponseBody String test(@RequestParam("x") int x, @RequestParam("y") int y) {
        return board.getTileAt(x, y).toString();
    }
}
