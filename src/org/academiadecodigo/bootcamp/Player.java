package org.academiadecodigo.bootcamp;

import java.util.Scanner;

public class Player {

    private String name;
    public int playerID;


    public String setWordToGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word to be guessed:");
        String wordToGuess = scanner.nextLine();
        return wordToGuess;
    }

    public String chooseChar() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayerID(){
        return playerID;
    }

}
