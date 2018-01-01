package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private BufferedReader inBufferReader;
    private PrintWriter outPrintWriter;

    //CONSTRUCTOR
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;

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
    public void run()
    {
        try {
            String logo = Prints.welcome();
            BufferedReader reader = new BufferedReader(new StringReader(logo));
            String str;
            while ((str = reader.readLine()) != null) {
                outPrintWriter.println(str);
            }

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    void sendMessageToPlayer(String message)
    {
        outPrintWriter.println(message);
    }

    void sendMessageToPlayerInline(String message)
    {
        outPrintWriter.print(message);
    }

    void updateGraphics(String message) {
        clearConsole();
        outPrintWriter.println(message);
    }

    String chooseStringToGuess(String str) {
        String rturn = "";
        try {
            //outPrintWriter.print("\033[H\033[2J");
            outPrintWriter.println(str);
            rturn = inBufferReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rturn.toUpperCase();
    }

    String chooseLetter(String str) {
        String rturn = "";
        try {
            outPrintWriter.println(str);
            rturn = inBufferReader.readLine();
            SoundEffects.keyPressed();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rturn.toUpperCase();
    }

    void streamIt(String str)
    {
        outPrintWriter.println(str);
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
    }

}