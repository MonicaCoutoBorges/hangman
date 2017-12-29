package org.academiadecodigo.bootcamp;

class Game {

    private String wordToGuess;
    private String hiddenWord;
    private Player player1;
    private Player player2;
    private int numberOfGuesses = 6;
    private boolean hasWon;


    private void init()
    {
        //Does nothing so far...
    }

    void start() {

        init();

        player2.sendMessage("Player 1 is choosing word for you to guess...");
        wordToGuess = player1.setWordToGuess();
        sendToAllPlayers("Word has been set. Guessing begins...");
        substituteWordCharacters();

        while (numberOfGuesses > 0 && !hasWon) {
            String strEntered = "";

            while ((strEntered = player2.chooseChar()).length() != 1) {
                player2.sendMessage("Only one character allowed!");
            }

            char character = strEntered.charAt(0);
            if (!compareWords(character)) {
                numberOfGuesses--;
            }
            sendToAllPlayers(hiddenWord + "   [" + numberOfGuesses + " chances left]");
        }

        if (numberOfGuesses == 0) {
            player2.sendMessage("Fuck you, you lose.");
            player1.sendMessage("Yeeaahh! Player 2 failed miserably.");
        }
    }


    private void substituteWordCharacters()
    {
        hiddenWord = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            hiddenWord += "_ ";
        }
        sendToAllPlayers(hiddenWord + "   [" + numberOfGuesses + " chances left]");
    }


    private boolean compareWords(char character)
    {
        boolean correctAttempt = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == character) {
                hiddenWord = hiddenWord.substring(0, i * 2) + character + hiddenWord.substring(i * 2 + 1);
                correctAttempt = true;
            }
        }

        String finalWord = hiddenWord.replace(" ", "");
        if (finalWord.equals(wordToGuess)) {
            hasWon = true;
            player2.sendMessage("Good job. You won the game.");
            player1.sendMessage("\r\nSadly, Player 2 won the game :(");
        }

        return correctAttempt;
    }


    private void sendToAllPlayers(String str)
    {
        player1.sendMessage(str);
        player2.sendMessage(str);
    }


    void createPlayer(int playerID, ClientHandler clientHandler)
    {
        switch (playerID){
            case 1:
                    player1 = new Player(1, clientHandler);
                    break;
            case 2:
                    player2 = new Player(2, clientHandler);
                    break;
            default:
                    System.out.println("Something went wrong. Invalid player instantiation!");
                    break;
        }
    }

}
