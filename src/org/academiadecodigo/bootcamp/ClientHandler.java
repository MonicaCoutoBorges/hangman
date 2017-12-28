package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final GameServer gameServer;
    private final Socket clientSocket;
    private final int clientID;
    private String name;
    private BufferedReader inBufferReader;
    private PrintWriter outPrintWriter;

    //CONSTRUCTOR
    public ClientHandler(Socket clientSocket, GameServer gameServer, int clientID) {
        this.gameServer = gameServer;
        this.clientSocket = clientSocket;
        this.clientID = clientID;
        this.name = "p" + clientID;

        try {
            // Setup input stream
            this.inBufferReader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            // Setup output stream
            this.outPrintWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // METHODS

    @Override
    public void run() {
        try {
            // Welcome message
            outPrintWriter.println("******************************");
            outPrintWriter.println("***** Welcome to HANGMAN *****");
            outPrintWriter.println("******************************");
            // Ask for player name
            outPrintWriter.println("\nEnter your name, " + name + " :");
            // Wait for player name and set name
            String receivePlayerName = inBufferReader.readLine();

            outPrintWriter.println(name + " name set to " + receivePlayerName.toUpperCase());
            this.name = receivePlayerName.toUpperCase();
            outPrintWriter.println("\nLet's play!");

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (clientSocket.isConnected()) {
            /*
            try {
                String gameReceivedMessage = inBufferReader.readLine();
                gameServer.sendAll(gameReceivedMessage);

           } catch (IOException e) { e.printStackTrace(); }
            */
        }
    }

    public void updateConsole(String message) {
        clearConsole();
        outPrintWriter.println(message);
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
    }

    public String inputStringToGuess() {
        String ret = "";
        try {
            outPrintWriter.println("\nInput string for the other player to guess:");
            ret = inBufferReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret.toUpperCase();
    }

    public String inputLetterToGuess() {
        String ret = "";
        try {
            outPrintWriter.println("\nNext letter to guess:");
            ret = inBufferReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret.toUpperCase();
    }

    //GETTERS
    public int getClientID() {
        return this.clientID;
    }

}