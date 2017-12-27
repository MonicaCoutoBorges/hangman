package org.academiadecodigo.bootcamp;

public class Game {

    private String wordToGuess;
    private String hiddenWord;
    private GameServer server;
    private Player player1;
    private Player player2;
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
            compare(character);

        }

        if (numberOfGuesses == 0) {
            System.out.println("Fuck you, you lose.");
        }
    }

    private void substituteWordCharacters() {

        for (int i = 0; i < wordToGuess.length(); i++) {
            hiddenWord += "_ ";
        }

        System.out.println(hiddenWord);
    }


    private void compare(char character) {

        for (int i = 0; i < wordToGuess.length(); i++) {

            if (wordToGuess.charAt(i) == character) {
                hiddenWord.replace(hiddenWord.charAt(i * 2), character);
                String visibleWord = hiddenWord.replace(" ", "");

                if (visibleWord.equals(wordToGuess)) {
                    hasWon = true;
                }

            } else {
                numberOfGuesses--;
            }
        }
    }
}
