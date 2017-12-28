package org.academiadecodigo.bootcamp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {

        Server server = new Server();
        server.start();

    }

    private int port = 8080;
    private List<Socket> clientList = new ArrayList<Socket>();
    //ExecutorService fixedPool = Executors.newFixedThreadPool(2);

    public void start() {

        Game game = new Game();
        int clientIdCount = 1;

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Server is now connected and waiting for requests at port number: " + port);
                clientList.add(clientSocket);

                game.createPlayer(clientIdCount, clientSocket);

                clientIdCount++;

            } catch (IOException e) {
                System.out.println("Missing port number");
            }

        }
    }


}
