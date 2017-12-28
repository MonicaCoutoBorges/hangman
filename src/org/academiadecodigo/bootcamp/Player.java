package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Player {

    private final Socket playerSocket;
    private String name;
    private int playerID;
    private static PrintWriter out;
    private static BufferedReader in;

    public Player (int playerID, Socket playerSocket){
        this.playerID = playerID;
        this.playerSocket = playerSocket;
        try {
            this.in = new BufferedReader(new InputStreamReader(this.playerSocket.getInputStream()));
            this.out = new PrintWriter(this.playerSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String setWordToGuess() {
        out.println("Please enter the word to guess:");
        String wordToGuess = null;
        try {
            wordToGuess = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wordToGuess;
    }

    public String chooseChar() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }



    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerID() {
        return playerID;
    }

}
