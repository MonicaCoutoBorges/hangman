package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {


    public static void main(String[] args) {


        int portNumber = 5000;


        ServerSocket serverSocket = null;
        System.out.println("Waiting for a connection on port " + portNumber + "...");
        try {
            serverSocket = new ServerSocket(portNumber);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Connected");
            while (clientSocket.isBound()) {

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String messageReceived = in.readLine();

                System.out.println(messageReceived);
                out.println("Your message " + "'" + messageReceived + "' was delivered.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
