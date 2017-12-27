package org.academiadecodigo.bootcamp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private final static int portNumber = 9000;
    private final List<ClientHandler> clientHandlers = Collections.synchronizedList(new ArrayList<ClientHandler>());

    public void start()
    {
        ServerSocket serverSocket = null;
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        int clientIdCount = 1;

        try {
            // Bind to local port and block while waiting for client connections
            serverSocket = new ServerSocket(portNumber);
            System.out.println("GameServer ON.");

            while (clientIdCount < 3){

                Socket clientSocket = serverSocket.accept();

                System.out.println("Connection established with: " + clientSocket.getRemoteSocketAddress());

                ClientHandler clientHandler = new ClientHandler(clientSocket, this, clientIdCount);
                clientHandlers.add(clientHandler);
                clientIdCount++;

                cachedPool.submit(clientHandler);
            }

            while (!serverSocket.isClosed())
            {
                // What the hell do i do here
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

    public void sendAll(String consoleOutput)
    {
        for (ClientHandler ch: clientHandlers) {
            ch.updateConsole(consoleOutput);
        }
    }

    public String chooseLetterToGuess(Player player)
    {
        String ret = "";
        for (ClientHandler ch: clientHandlers) {
            if (ch.getClientID() == player.getPlayerID){
                ret = ch.inputLetterToGuess();
                break;
            }
        }
        return ret;
    }

    public String chooseStringToGuess(Player player)
    {
        String ret = "";
        for (ClientHandler ch: clientHandlers) {
            if (ch.getClientID() == player.getPlayerID){
                ret = ch.inputStringToGuess();
                break;
            }
        }
        return ret;
    }

    public void closeServer()
    {

    }

}
