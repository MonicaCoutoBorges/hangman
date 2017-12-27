package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        int port = 5000;
        String hostName = "localhost";
        try {

            Socket clientSocket = new Socket(hostName, port);
            System.out.println("Connection established with " + hostName + " on port " + port + "\n");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            while (clientSocket.isBound()) {

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}