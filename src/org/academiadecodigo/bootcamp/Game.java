package org.academiadecodigo.bootcamp;

import java.util.ArrayList;

class Game {

    private Player players[] = new Player[2];
    private int playerVictories[] = new int[2];
    private int activePlayerIndex = 1;
    private String wordToGuess;
    private String hiddenWord;
    private int numberOfGuesses = 6;
    private int numberOfRounds = 0;
    private ArrayList<Character> charsUsed = new ArrayList<>();
    private boolean hasWonRound;


    void init()
    {
        SoundEffects.theme();

        while (numberOfRounds != 4) {
            start();    //start new round
            numberOfRounds++;
            resetForNextRound();
        }

        showResults();

        SoundEffects.winningTheme();
    }

    private void showResults()
    {
        sendToAllPlayers(Prints.gameOver());

        if (playerVictories[0] > playerVictories[1]){
            players[0].sendMessage(Prints.gameWinner());
            players[1].sendMessage(Prints.gameLoser());
        } else if (playerVictories[0] < playerVictories[1]) {
            players[0].sendMessage(Prints.gameLoser());
            players[1].sendMessage(Prints.gameWinner());
        } else {
            sendToAllPlayers(Prints.gameDraw());
        }
    }

    private void start()
    {
        Player player1 = players[Math.abs(activePlayerIndex - 1)];
        Player player2 = players[activePlayerIndex];

        player2.sendMessage(" Your opponent is choosing word for you to guess...");
        wordToGuess = player1.setWordToGuess();
        sendToAllPlayers(" Word has been set. Guessing begins...\r\n\r\n");
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
            sendToAllPlayers("\r\n " + hiddenWord + "   [" + numberOfGuesses + " chances left]");
        }

        if (hasWonRound){
            playerVictories[activePlayerIndex]++;
            player2.sendMessage("\r\n ::: Good job. You won this round! :::");
            player1.sendMessage("\r\n ::: Sadly, your opponent won this round. :::");
        } else {
            playerVictories[Math.abs(activePlayerIndex - 1)]++;
            player2.sendMessage("\r\n ::: Fuck you, you lost this round. :::");
            player1.sendMessage("\r\n ::: Yeeaahh! Your opponent failed miserably on this round. :::");
            SoundEffects.hang();
        }

        if (numberOfRounds != 3) {
            sendToAllPlayers("\r\n\r\n------------------------------------------------------");
            sendToAllPlayers(" ................. CHANGING ROLES .................");
            player1.sendMessage(" You'll be guessing your opponent's chosen word.");
            player2.sendMessage(" You'll be setting a word for your opponent to guess.");
            sendToAllPlayers("------------------------------------------------------\r\n");
        }
    }


    private void substituteWordCharacters() {
        hiddenWord = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            hiddenWord += "_ ";
        }
        sendToAllPlayers(" " + hiddenWord + "   [" + numberOfGuesses + " chances leftT]");
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
            //players[Math.abs(activePlayerIndex - 1)].sendMessage(" Good job. You won this round!");
            //players[activePlayerIndex].sendMessage("\r\n Sadly, your opponent won this round :(");
            SoundEffects.clap();
        }

        return correctAttempt;
    }

    private char getUsedChars(Player player){
        String strEntered = player.chooseChar();
        char character = strEntered.charAt(0);

        for(Character letter : charsUsed) {
            if (letter.compareTo(character) == 0) {
                sendToAllPlayers("\r\n " + character + " has already been used.");
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

    private void resetForNextRound()
    {
        /* *** Reset *** */
        hasWonRound = false;
        numberOfGuesses = 6;
        charsUsed.clear();
        /* *** Change roles *** */
        activePlayerIndex = Math.abs(activePlayerIndex - 1);
    }

    void createPlayer(int playerId, ClientHandler clientHandler) {
        switch (playerId) {
            case 1:
                players[0] = new Player(clientHandler);
                break;
            case 2:
                players[1] = new Player(clientHandler);
                break;
            default:
                System.out.println(" Something went wrong. Invalid player instantiation!");
                break;
        }
    }

}
