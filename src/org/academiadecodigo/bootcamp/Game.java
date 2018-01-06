package org.academiadecodigo.bootcamp;

import java.util.ArrayList;

class Game {

    private final Player players[] = new Player[2];
    private final int victories[] = new int[2];
    private int activePlayerIndex = 1;
    private String wordToGuess;
    private String hiddenWord;
    private int numberOfMissesLeft = 6;
    private int numberOfRounds = 0;
    private ArrayList<Character> charsUsed = new ArrayList<>();
    private boolean hasWonRound;


    void init()
    {
        SoundEffects.theme();

        while (numberOfRounds != 4) {
            start();
            numberOfRounds++;
            resetForNextRound();
        }

        showResults();

        SoundEffects.winningTheme();
    }



    private void start()
    {
        Player player1 = players[Math.abs(activePlayerIndex - 1)];
        Player player2 = players[activePlayerIndex];

        player2.sendMessage(" Your opponent is choosing word for you to guess...");
        wordToGuess = player1.setWordToGuess();
        sendToAllPlayers(" Word has been set. Guessing begins...\r\n\r\n");
        substituteWordCharacters();

        while (numberOfMissesLeft > 0 && !hasWonRound) {
            char character = getUsedChars(player2);
            charsUsed.add(character);

            if (!compareWords(character)) {
                numberOfMissesLeft--;
                SoundEffects.wrongAttempt();
            }
            else{
                SoundEffects.correctAttempt();
            }

            updateHanger();
            sendToAllPlayers("\r\n " + hiddenWord + "   [" + numberOfMissesLeft + " chances left]\r\n");
        }

        if (hasWonRound){
            victories[activePlayerIndex]++;
            player2.sendMessage("\r\n ::: Good job. You won this round! :::");
            player1.sendMessage("\r\n ::: Sadly, your opponent won this round. :::");
        } else {
            victories[Math.abs(activePlayerIndex - 1)]++;
            player2.sendMessage("\r\n ::: Fuck. You lost this round! :::");
            player1.sendMessage("\r\n ::: Yeeaahh! Your opponent failed miserably on this round. :::");
            player2.sendMessage("\r\n ::: The word was \u001B[33m" + wordToGuess + " \u001B[30m:::");
            SoundEffects.hang();
        }

        if (numberOfRounds != 3) {
            sendToAllPlayers("\r\n\r\n\r\n------------------------------------------------------");
            sendToAllPlayers(" ................. CHANGING ROLES .................");
            player1.sendMessage(" You'll be guessing your opponent's chosen word.");
            player2.sendMessage(" You'll be setting a word for your opponent to guess.");
            sendToAllPlayers("------------------------------------------------------\r\n");
        }
    }


    private void substituteWordCharacters()
    {
        hiddenWord = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            hiddenWord += "_ ";
        }
        sendToAllPlayers(" " + hiddenWord + "   [" + numberOfMissesLeft + " chances left]\r\n");
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


    private void updateHanger()
    {
        String hangerTop = "";
        switch (numberOfMissesLeft){
            case 0:
                hangerTop = Prints.hangerTop0();
                break;
            case 1:
                hangerTop = Prints.hangerTop1();
                break;
            case 2:
                hangerTop = Prints.hangerTop2();
                break;
            case 3:
                hangerTop = Prints.hangerTop3();
                break;
            case 4:
                hangerTop = Prints.hangerTop4();
                break;
            case 5:
                hangerTop = Prints.hangerTop5();
                break;
            case 6:
                hangerTop = Prints.hangerTop6();
                break;
        }

        String hangerBottom = Prints.hangBox();
        for (Character letter : charsUsed) {
            // Put letters already chosen on hanger bottom
            hangerBottom = hangerBottom.replaceFirst("(\\*\\*)", letter + " ");
            // Put correspondent right / wrong guess of that letter on hanger bottom
            hangerBottom = hangerBottom.replaceFirst("(==)", wordToGuess.contains(letter.toString()) ? "v " : "x ");
        }

        updateGraphics("\r\n\r\n\r\n" + hangerTop + hangerBottom);
    }


    private void updateGraphics(String strSrc)
    {
        for (Player player : players) {
            player.updatePlayerGraphics(strSrc);
        }
    }


    private void sendToAllPlayers(String str)
    {
        for (Player player : players) {
            player.sendMessage(str);
        }
    }


    private void resetForNextRound()
    {
        /* *** Reset *** */
        hasWonRound = false;
        numberOfMissesLeft = 6;
        charsUsed.clear();
        /* *** Change roles *** */
        activePlayerIndex = Math.abs(activePlayerIndex - 1);
    }


    private void showResults()
    {
        updateGraphics(Prints.gameOver());

        players[0].sendMessage("\r\n\r\n You: " + victories[0] + " , Opponent: " + victories[1]);
        players[1].sendMessage("\r\n\r\n You: " + victories[1] + " , Opponent: " + victories[0]);

        if (victories[0] > victories[1]){
            players[0].sendMessage(Prints.gameWinner());
            players[1].sendMessage(Prints.gameLoser());
        } else if (victories[0] < victories[1]) {
            players[0].sendMessage(Prints.gameLoser());
            players[1].sendMessage(Prints.gameWinner());
        } else {
            sendToAllPlayers(Prints.gameDraw());
        }
        sendToAllPlayers("         \u001B[37m.-\"-.\r\n       .'=^=^='.\r\n      /=^=^=^=^=\\\r\n     :^= HAPPY =^;\r\n     |^ EASTER! ^|\r\n     :^=^=^=^=^=^:\r\n      \\=^=^=^=^=/\r\n       `.=^=^=.'\r\n         `~~~`\u001B[37m");
    }


    void createPlayer(int playerId, ClientHandler clientHandler)
    {
        if (playerId <= 2){
            players[playerId - 1] = new Player(clientHandler);
        } else {
            System.out.println(" Something went wrong. Invalid player instantiation!");
        }
    }

}