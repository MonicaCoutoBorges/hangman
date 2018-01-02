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
        updateGraphics(Prints.welcome());
    }

    void sendMessageToPlayer(String message)
    {
        outPrintWriter.println(message);
    }

    void updateGraphics(String strSrc)
    {
        try {
            BufferedReader reader = new BufferedReader(new StringReader(strSrc));
            String str;
            while ((str = reader.readLine()) != null) {
                outPrintWriter.println(str);
            }
        } catch(IOException e) { e.printStackTrace(); }
    }

    String chooseStringToGuess(String str) {
        String rturn = "";
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rturn.toUpperCase();
    }

}