package org.academiadecodigo.bootcamp;

public class Game {


    public void init(){

        GameServer gameServer = new GameServer();
        gameServer.start();
    }

    public void start() {

        init();
    }

}
