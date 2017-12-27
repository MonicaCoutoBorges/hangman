package org.academiadecodigo.bootcamp;

public class Game {

    private String wordToGuess;



    public void init() {

        //init gameserver
        //createplayer

    }

    public void start() {

    }

    public void setPlayerName(Player player, String username) {
        player.setName(username);
    }



    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }
}
