package org.academiadecodigo.bootcamp;

import java.net.ServerSocket;
import java.util.Collections;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private final static int portNumber = 9000;
    private final List<ClientHandler> clientHandlers = Collections.synchronizedList(new ArrayList<ClientHandler>());

    void start()
    {
        ServerSocket serverSocket = null;
        Game game = new Game();
        int clientsCount = 1;
        ExecutorService fixedPool = Executors.newFixedThreadPool(2);

        try {
            // Bind to local port and block while waiting for client connections
            serverSocket = new ServerSocket(portNumber);
            System.out.println("GameServer ON.");

            while (!serverSocket.isClosed()) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection established with: " + clientSocket.getRemoteSocketAddress());

                // create clientHandler and add it to the List
                    ClientHandler clientHandler = new ClientHandler(clientSocket);
                    clientHandlers.add(clientHandler);
                // add to thread pool
                    fixedPool.submit(clientHandler);
                // create player
                    game.createPlayer(clientsCount, clientHandler);
                // increment clients count
                    clientsCount++;
                // Start game
                if (clientsCount > 2){ game.start(); }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close socket
                serverSocket.close();
                System.out.println("GameServer OFF.");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeServer()
    {

    }

}