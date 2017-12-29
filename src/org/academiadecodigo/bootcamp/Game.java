package org.academiadecodigo.bootcamp;

class Game {

    private String wordToGuess;
    private String hiddenWord;
    private Player players[] = new Player[2];
    private int activePlayerIndex = 0;
    private int numberOfGuesses = 6;
    private int numberOfRounds = 0;
    private boolean hasWonRound;


    void init()
    {
        numberOfGuesses = 6;
        if(numberOfRounds != 4){
            start();
            numberOfRounds++;
        }
    }

    private void start() {

        Player player1 = players[Math.abs(activePlayerIndex)];
        Player player2 = players[Math.abs(activePlayerIndex-1)];

        player2.sendMessage("Your opponent is choosing word for you to guess...");
        wordToGuess = player1.setWordToGuess();
        sendToAllPlayers("Word has been set. Guessing begins...");
        substituteWordCharacters();

        while (numberOfGuesses > 0 && !hasWonRound) {
            String strEntered = "";

            strEntered = player2.chooseChar();
            /*
            while ((strEntered = player2.chooseChar()).length() != 1) {
                player2.sendMessage("Only one character allowed!");
            }
            */

            char character = strEntered.charAt(0);
            if (!compareWords(character)) {
                numberOfGuesses--;
            }
            sendToAllPlayers(hiddenWord + "   [" + numberOfGuesses + " chances left]");
        }

        if (numberOfGuesses == 0) {
            player2.sendMessage("Fuck you, you lose.");
            player1.sendMessage("Yeeaahh! Your opponent failed miserably.");
        }

        sendToAllPlayers("\r\n..... Changing roles .....");
        player1.sendMessage("You'll be guessing your opponent chosen word.\r\n");
        player2.sendMessage("You'll be setting a word for your opponent to guess.\r\n");

        activePlayerIndex = Math.abs(activePlayerIndex-1);
        init();
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
            hasWonRound = true;
            players[Math.abs(activePlayerIndex-1)].sendMessage("Good job. You won the game.");
            players[Math.abs(activePlayerIndex)].sendMessage("\r\nSadly, your opponent won the game :(");
        }

        return correctAttempt;
    }


    private void sendToAllPlayers(String str)
    {
        for (Player player: players) {
            player.sendMessage(str);
        }
    }


    void createPlayer(int playerID, ClientHandler clientHandler)
    {
        switch (playerID){
            case 1:
                    players[0] = new Player(0, clientHandler);
                    break;
            case 2:
                    players[1] = new Player(1, clientHandler);
                    break;
            default:
                    System.out.println("Something went wrong. Invalid player instantiation!");
                    break;
        }
    }

}
