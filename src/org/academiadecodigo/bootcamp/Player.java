package org.academiadecodigo.bootcamp;

class Player {

    private final ClientHandler ch;

    //CONSTRUCTOR
    public Player(ClientHandler clientHandler){
        this.ch = clientHandler;
    }


    //METHODS
    String setWordToGuess()
    {
        return ch.chooseStringToGuess(" Please enter a word. Guessing that word will be your opponent's challenge.");
    }

    String chooseChar()
    {
        return ch.chooseLetter("\r\n\r\n Type a letter to guess:");
    }

    void sendMessage(String message)
    {
        ch.sendMessageToPlayer(message);
    }

    void updatePlayerGraphics(String strSrc)
    {
        ch.updateGraphics(strSrc);
    }

}
