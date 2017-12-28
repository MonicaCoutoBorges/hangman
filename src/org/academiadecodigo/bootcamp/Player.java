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

    public char chooseChar(){
        char characterEntered = '\u0000';
        String strEntered = "";
        while(strEntered.length() != 1){
            Scanner scanner = new Scanner(System.in);
            strEntered = scanner.next();

            if(strEntered.length() != 1){
                System.out.println("Enter one character only:");
            }
            else{
                System.out.println("Enter a letter:");
            }
            characterEntered = strEntered.charAt(0);
        }

        return characterEntered;
    }

    public void setName(String name) {
        this.name = name;
    }
}
