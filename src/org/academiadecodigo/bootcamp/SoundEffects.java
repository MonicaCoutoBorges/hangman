package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.kuusisto.tinysound.Sound;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.TinySound;

public class SoundEffects {

    public static void keyPressed() {

        TinySound.init();
        Sound key = TinySound.loadSound("key.wav");
        for (int i = 0; i < 1; i++) {
            key.play();

        }
    }

    public static void hang() {

        TinySound.init();
        Sound choke = TinySound.loadSound("choke.wav");
        for (int i = 0; i < 1; i++) {
            choke.play();

        }
    }

    public static void clap() {

        TinySound.init();
        Sound clap = TinySound.loadSound("win.wav");
        for (int i = 0; i < 1; i++) {
            clap.play();

        }
    }

    public static void theme(){
        TinySound.init();
        Sound theme = TinySound.loadSound("initial.wav");
        for (int i = 0; i < 1; i++) {
            theme.play();
        }
    }

    public static void winningTheme(){
        TinySound.init();
        Sound theme = TinySound.loadSound("winningTheme.wav");
        for (int i = 0; i < 1; i++) {
            theme.play();
        }
    }
}
