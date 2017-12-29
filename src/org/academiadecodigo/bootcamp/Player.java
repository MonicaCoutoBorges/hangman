package org.academiadecodigo.bootcamp;

class Player {

    private final int playerID;
    private final ClientHandler ch;

    //CONSTRUCTOR
    public Player(int playerID, ClientHandler clientHandler){
        this.playerID = playerID;
        this.ch = clientHandler;
    }

    //METHODS
    String setWordToGuess()
    {
        return ch.chooseStringToGuess("Please enter the word to be guessed:");
    }

    String chooseChar()
    {
        return ch.chooseLetter("Enter a single character:");
    }

    void sendMessage(String message)
    {
        ch.sendMessageToPlayer(message);
    }
    void sendMessageInline(String message)
    {
        ch.sendMessageToPlayerInline(message);
    }

    void updatePlayerTerminal(String str)
    {
        // Work In Progress...
        ch.updateGraphics(str);
    }


    //GETTERS
    int getPlayerID(){
        return playerID;
    }

}
