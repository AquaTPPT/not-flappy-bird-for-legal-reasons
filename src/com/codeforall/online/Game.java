package com.codeforall.online;

import com.codeforall.online.Player.Player;
import com.codeforall.online.playspace.Playspace;
import com.codeforall.online.playspace.Tubes;
import com.codeforall.online.statics.Random;
import com.codeforall.simplegraphics.pictures.Picture;

public class Game {
    private Playspace playSpace = new Playspace();
    private Tubes tubes1 = new Tubes();
    private Tubes tubes2 = new Tubes();
    private Tubes tubes3 = new Tubes();
    private Player player = new Player();

    public void init() throws InterruptedException {
        playSpace.init();
        tubes1.spawnTubes(800, Random.randomInt(-900, 0));
        tubes2.spawnTubes(1200, Random.randomInt(-900, 0));
        tubes3.spawnTubes(1600, Random.randomInt(-900, 0));

        player.init();


        while(true){
            Thread.sleep(16);
            tubes1.moveAll();
            tubes2.moveAll();
            tubes3.moveAll();
            player.move();
        }



    }
}
