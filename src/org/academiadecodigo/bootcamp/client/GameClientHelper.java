package org.academiadecodigo.bootcamp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClientHelper implements Runnable{

    private final Socket clientSocket;
    private BufferedReader terminalReader;
    private PrintWriter outPrintWriter;

    //CONSTRUCTOR
    public GameClientHelper(Socket clientSocket)
    {
        this.clientSocket = clientSocket;

        try {
            // Write terminal input to the outputStream
            this.terminalReader = new BufferedReader(new InputStreamReader(System.in));
            // Setup output stream
            this.outPrintWriter = new PrintWriter(this.clientSocket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while(true){
            try {
                outPrintWriter.println(terminalReader.readLine());

            } catch (IOException e) { e.printStackTrace(); }
        }
    }

}
