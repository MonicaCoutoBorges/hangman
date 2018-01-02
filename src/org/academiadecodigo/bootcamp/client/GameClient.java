package org.academiadecodigo.bootcamp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GameClient {

    public static void main(String[] args) {

        String hostName = "localhost";
        int portNumber = 9444;
        Socket clientSocket = null;
        BufferedReader inBufferReader = null;

        try {
            // Open a client socket, blocking while connecting to the server
            clientSocket = new Socket(hostName, portNumber);

            GameClientHelper clientHelper = new GameClientHelper(clientSocket);

            Thread thread = new Thread(clientHelper);
            thread.start();

            // Setup input stream from server
            inBufferReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            while(true){
                String message = inBufferReader.readLine();
                // Write server feedback
                System.out.println(message);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close streams
                inBufferReader.close();

                // Close socket
                clientSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}