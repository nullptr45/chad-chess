package com.goofygoobers.chadchess.web;

import com.goofygoobers.chadchess.ChessBoardWrapper;
import com.goofygoobers.chadchess.User;
import com.goofygoobers.chadchess.logic.V2;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * GameController
 * The GameController class is in charge of handle HTTP requests from the client
 */
@Controller
public class GameController {

    // TODO: Add more attributes for users (date created, etc)
    @Autowired
    public SimpMessagingTemplate simpMessagingTemplate;

    // NOTE: curl "http://localhost:8080/register-user?username=demo&password=password"
    @RequestMapping(value = "/register-user", method = RequestMethod.GET)
    @ResponseBody
    public int registerUser(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        return ChadchessApplication.addUser(username, password);
    }

    // NOTE: Returns an object, not a string
    // TODO: Make a demo working in terminal. Need a solution to show functionality without Taras.
    @RequestMapping(value = "/get-user", method = RequestMethod.GET)
    @ResponseBody
    public User getUser(@RequestParam("id") int id) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query =
                ChadchessApplication.getDb().collection("users").whereEqualTo("id", id).get();
        QuerySnapshot querySnapshot = query.get();

        QueryDocumentSnapshot userData = querySnapshot.getDocuments().get(0);
        User result = new User();

        result.setBio(userData.getString("bio"));
        result.setPfp(userData.getString("pfp"));
        result.setName(userData.getString("username"));

        return result;
    }

    @RequestMapping(value = "/list-users", method = RequestMethod.GET)
    @ResponseBody
    public String listUsers() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> query =
                ChadchessApplication.getDb().collection("users").whereGreaterThan("id", -1).get();
        QuerySnapshot querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

        StringBuilder result = new StringBuilder("{\n");

        for (QueryDocumentSnapshot document : documents) {
            result.append(document.getString("username")).append(",\n");
        }

        return result + "\n}";
    }

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

        for (int i = 0; i < validMovesArr.length; i++) {
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
    public ChessBoardWrapper getBoard(@RequestParam("id") int id, @RequestParam("player") int player, HttpServletResponse response) {
        ChessBoardWrapper board;

        //create new chess board
        if (id == -1) {
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

        for (int i = 0; idIterator.hasNext(); i++) {
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

