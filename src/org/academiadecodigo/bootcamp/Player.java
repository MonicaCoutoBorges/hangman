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
        return ch.chooseStringToGuess(" Please enter the word to be guessed by your opponent:");
    }

    String chooseChar()
    {
        return ch.chooseLetter("\n Next letter to guess:");
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

}
