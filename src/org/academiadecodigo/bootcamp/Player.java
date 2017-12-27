package org.academiadecodigo.bootcamp;

import java.util.Scanner;

public class Player {

    private String name;




    public String setWordToGuess() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the word to be guessed:");
        String wordToGuess = scanner.nextLine();
        return wordToGuess;
    }

    public char play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a letter:");
        char characterEntered = scanner.next().charAt(0);
        return characterEntered;
    }

    public void setName(String name) {
        this.name = name;
    }
}
