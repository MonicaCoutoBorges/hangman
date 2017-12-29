package org.academiadecodigo.bootcamp;

import java.util.ArrayList;

class Game {

    private String wordToGuess;
    private String hiddenWord;
    private Player players[] = new Player[2];
    private int activePlayerIndex = 0;
    private int numberOfGuesses = 6;
    private int numberOfRounds = 0;
    private ArrayList<Character> charsUsed = new ArrayList<>();
    private boolean hasWonRound;


    void init() {
        SoundEffects.theme();

        while (numberOfRounds != 4) {
            numberOfGuesses = 6;
            start();
            numberOfRounds++;
        }
        sendToAllPlayers("\r\nGAME OVER");
        SoundEffects.winningTheme();
    }

    private void start() {

        Player player1 = players[Math.abs(activePlayerIndex)];
        Player player2 = players[Math.abs(activePlayerIndex - 1)];

        player2.sendMessage("Your opponent is choosing word for you to guess...");
        wordToGuess = player1.setWordToGuess();
        sendToAllPlayers("Word has been set. Guessing begins...");
        substituteWordCharacters();

        while (numberOfGuesses > 0 && !hasWonRound) {
            String strEntered = "";


            //strEntered = player2.chooseChar();
            //char character = strEntered.charAt(0);
            char character = getUsedChars(player2);

            charsUsed.add(character);
            printUsedChars();


            if (!compareWords(character)) {
                numberOfGuesses--;
            }
            sendToAllPlayers("\r\n" + hiddenWord + "   [" + numberOfGuesses + " chances left]");
        }

        if (numberOfGuesses == 0) {
            player2.sendMessage("Fuck you, you lose.");
            player1.sendMessage("Yeeaahh! Your opponent failed miserably.");
            SoundEffects.hang();
        }

        if (numberOfRounds != 3) {
            sendToAllPlayers("\r\n..... Changing roles .....");
            player1.sendMessage("You'll be guessing your opponent chosen word.\r\n");
            player2.sendMessage("You'll be setting a word for your opponent to guess.\r\n");
        }

        activePlayerIndex = Math.abs(activePlayerIndex - 1);
        hasWonRound = false;
        charsUsed.clear();
    }


    private void substituteWordCharacters() {
        hiddenWord = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            hiddenWord += "_ ";
        }
        sendToAllPlayers(hiddenWord + "   [" + numberOfGuesses + " chances left]");
    }


    private boolean compareWords(char character) {
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
            players[Math.abs(activePlayerIndex - 1)].sendMessage("Good job. You won the game.");
            players[Math.abs(activePlayerIndex)].sendMessage("\r\nSadly, your opponent won the game :(");
            SoundEffects.clap();
        }

        return correctAttempt;
    }

    private char getUsedChars(Player player){
        String strEntered = player.chooseChar();
        char character = strEntered.charAt(0);

        for(Character letter : charsUsed) {
            if (letter.compareTo(character) == 0) {
                sendToAllPlayers("\r\nThis letter has already been used.");
                character = getUsedChars(player);
            }
        }
        return character;
    }

    private void printUsedChars(){
        for (Character letter : charsUsed) {
            sendToAllPlayersInline(" " + letter + " ");
        }
    }


    private void sendToAllPlayers(String str) {
        for (Player player : players) {
            player.sendMessage(str);
        }
    }

    private void sendToAllPlayersInline(String str) {
        for (Player player : players) {
            player.sendMessageInline(str);
        }
    }


    void createPlayer(int playerID, ClientHandler clientHandler) {
        switch (playerID) {
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
