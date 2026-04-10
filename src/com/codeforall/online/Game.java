package com.codeforall.online;

import com.codeforall.online.Player.Player;
import com.codeforall.online.playspace.Playspace;
import com.codeforall.online.playspace.Tubes;
import com.codeforall.online.statics.Random;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;
import com.codeforall.simplegraphics.pictures.Picture;

public class Game {
    private Playspace playSpace = new Playspace();
    private Tubes tubes1 = new Tubes();
    private Tubes tubes2 = new Tubes();
    private Tubes tubes3 = new Tubes();
    private Player player = new Player(this);
    private boolean isPlaying = true;

    public void init() throws InterruptedException {
        playSpace.init();
        tubes1.spawnTubes(800, Random.randomInt(-900, 0));
        tubes2.spawnTubes(1200, Random.randomInt(-900, 0));
        tubes3.spawnTubes(1600, Random.randomInt(-900, 0));

        player.init(playSpace);

        while(isPlaying){
            Thread.sleep(16);
            tubes1.moveAll();
            tubes2.moveAll();
            tubes3.moveAll();
            player.move();
            collisionDetector(tubes1);
            collisionDetector(tubes2);
            collisionDetector(tubes3);
        }
    }

    public boolean collisionDetector(Tubes tubes) {

        if (player.getX() + player.getWidth() >= tubes.getUpperX() &&
                player.getY() + player.getHeight() >= tubes.getUpperY() &&
                tubes.getUpperX() + tubes.getUpperWidth() >= player.getX() &&
                tubes.getUpperY() + tubes.getUpperHeight() >= player.getY() ||

                player.getX() + player.getWidth() >= tubes.getLowerX() &&
                        player.getY() + player.getHeight() >= tubes.getLowerY() &&
                        tubes.getLowerX() + tubes.getLowerWidth() >= player.getX() &&
                        tubes.getLowerY() + tubes.getLowerHeight() >= player.getY() ) {
            isPlaying = false;
            player.removeJumpMechanic();
        }
        return isPlaying;
    }

    public boolean isPlaying(boolean val) {
        return isPlaying = val;
    }
}
