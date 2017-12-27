package org.academiadecodigo.bootcamp;

public class Game {

    private String wordToGuess;
    private String hiddenWord;
    private GameServer server;
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
            char character = player2.play();

            if (!compareWords(character)){
                numberOfGuesses--;
            }

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

        System.out.println(hiddenWord);
    }


    private boolean compareWords(char character) {

        boolean attempt = false;

        for (int i = 0; i < wordToGuess.length(); i++) {

            if (wordToGuess.charAt(i) == character) {

                hiddenWord = hiddenWord.replace(hiddenWord.charAt(i * 2), character);
                System.out.println(hiddenWord);

                String finalWord = hiddenWord.replace(" ", "");

                if (finalWord.equals(wordToGuess)) {
                    hasWon = true;
                    System.out.println("Good job. You won the game.");
                }

                attempt = true;
            }
        }

        return attempt;
    }
}
