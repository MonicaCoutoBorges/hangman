package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        int port;
        String hostName;
        try {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Hostname: ");
            hostName = scanner.nextLine();
            System.out.print("Port number: ");
            port = Integer.parseInt(scanner.nextLine());


            Socket clientSocket = new Socket(hostName, port);
            System.out.println("Connection established with " + hostName + " on port " + port + "\n");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


            while (clientSocket.isBound()) {
                System.out.print("Your message: ");
                String message = scanner.nextLine();
                out.println(message);
                System.out.println(in.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}