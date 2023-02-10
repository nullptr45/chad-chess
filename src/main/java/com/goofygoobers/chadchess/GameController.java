package com.goofygoobers.chadchess;
import org.springframework.web.bind.annotation.*;

import com.goofygoobers.chadchess.logic.*;

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
