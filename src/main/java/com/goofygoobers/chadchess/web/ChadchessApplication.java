package com.goofygoobers.chadchess.web;

import com.goofygoobers.chadchess.ChessBoardWrapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class ChadchessApplication {

    private static Firestore db;

    private static Hashtable<Integer, ChessBoardWrapper> boards = new Hashtable<>();
    private static Hashtable<String, ChessBoardWrapper> sessionBoards = new Hashtable<>();
    private static Random rand = new Random();

    public ChadchessApplication() throws Exception {
        FirestoreOptions firestoreOptions =
                FirestoreOptions.getDefaultInstance().toBuilder()
                        .setProjectId("chad-chess")
                        .setCredentials(GoogleCredentials.getApplicationDefault())
                        .build();
        db = firestoreOptions.getService();
    }

    public static Firestore getDb() {
        return db;
    }

    public static int addUser(String username, String password) throws Exception {
        DocumentReference docRef = db.collection("users").document(username);
        Map<String, Object> data = new HashMap<>();
        int id = rand.nextInt(Integer.MAX_VALUE);
        data.put("username", username);
        data.put("password", password);
        data.put("id", id);
        ApiFuture<WriteResult> result = docRef.set(data);
        return id;
    }

    public static int addMatch(int hostID) {
        int id = rand.nextInt(Integer.MAX_VALUE);
        DocumentReference docRef = db.collection("active-games").document(String.valueOf(id));
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        data.put("player1", hostID);
        data.put("player2", -1);
        data.put("state", new ChessBoardWrapper(id).toString());
        ApiFuture<WriteResult> result = docRef.set(data);
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("Delete this bozo");
        SpringApplication.run(ChadchessApplication.class, args);
    }

    public static Hashtable<Integer, ChessBoardWrapper> getBoards() {
        return boards;
    }

    public static Random getRand() {
        return rand;
    }
}
