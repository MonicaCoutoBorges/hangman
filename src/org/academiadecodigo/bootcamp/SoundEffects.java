package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.kuusisto.tinysound.Sound;
import org.academiadecodigo.bootcamp.kuusisto.tinysound.TinySound;

class SoundEffects {

    static void correctAttempt() {

        TinySound.init();
        Sound key = TinySound.loadSound("key.wav");
        for (int i = 0; i < 1; i++) {
            key.play();

        }
    }

    static void wrongAttempt() {

        TinySound.init();
        Sound key = TinySound.loadSound("wrong.wav");
        for (int i = 0; i < 1; i++) {
            key.play();

        }
    }

    static void hang() {

        TinySound.init();
        Sound choke = TinySound.loadSound("choke.wav");
        for (int i = 0; i < 1; i++) {
            choke.play();

        }
    }

    static void clap() {

        TinySound.init();
        Sound clap = TinySound.loadSound("win.wav");
        for (int i = 0; i < 1; i++) {
            clap.play();

        }
    }

    static void theme(){
        TinySound.init();
        Sound theme = TinySound.loadSound("initial.wav");
        for (int i = 0; i < 1; i++) {
            theme.play();
        }
    }

    static void winningTheme(){
        TinySound.init();
        Sound theme = TinySound.loadSound("winningTheme.wav");
        for (int i = 0; i < 1; i++) {
            theme.play();
        }
    }
}
