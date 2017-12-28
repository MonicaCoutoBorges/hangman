package org.academiadecodigo.bootcamp;

public class Game {

    private String wordToGuess;
    private String hiddenWord;
    private Player player1 = new Player();
    private Player player2 = new Player();
    private int numberOfGuesses = 6;
    private boolean hasWon;


    public void init() {

        //init gameserver
        //createplayer

    }

    public void start() {

        init();

        wordToGuess = player1.setWordToGuess();
        substituteWordCharacters();

        while (numberOfGuesses > 0 && !hasWon) {
            System.out.println("Enter a single character:");
            String strEntered = "";

            while ((strEntered = player2.chooseChar()).length() != 1) {
                System.out.println("Enter one character only:");
            }

            char character = strEntered.charAt(0);
            if (!compareWords(character)) {
                numberOfGuesses--;
            }
            System.out.println(hiddenWord + numberOfGuesses + " left");
        }

        if (numberOfGuesses == 0) {
            System.out.println("Fuck you, you lose.");
        }
    }

    private void substituteWordCharacters() {

        hiddenWord = "";
        for (int i = 0; i < wordToGuess.length(); i++) {
            hiddenWord += "_ ";
        }
        System.out.println(hiddenWord + numberOfGuesses + " left");
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
            hasWon = true;
            System.out.println("Good job. You won the game.");
        }

        return correctAttempt;
    }
}
