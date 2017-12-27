package org.academiadecodigo.bootcamp;

import java.util.Scanner;

public class Game {

    private String wordToGuess;
    private GameServer server;
    private Player player1;
    private Player player2;
    private int numberOfGuesses = 5;


    public void init() {

        //init gameserver
        //createplayer

    }

    public void start() {

        init();
        wordToGuess = player1.setWordToGuess();
        player2.play();
        compare();

    }


    private void compare() {

        for (int i = 0; i < wordToGuess.length() ; i++) {

            if(wordToGuess.charAt(i) == player2.play()){

                
            }
        }
    }

    private String askUsername() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter your name:");
        String username = scanner.nextLine();
        return username;
    }

    public void setPlayerName(Player player, String username) {
        player.setName(username);
    }




}
