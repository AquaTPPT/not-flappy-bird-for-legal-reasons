package com.codeforall.online;

import com.codeforall.online.Player.Player;
import com.codeforall.online.playspace.Playspace;
import com.codeforall.online.playspace.Tubes;
import com.codeforall.online.statics.Random;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Text;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.awt.*;
import java.io.File;

public class Game {
    private Playspace playSpace = new Playspace();
    private Tubes tubes1 = new Tubes();
    private Tubes tubes2 = new Tubes();
    private Tubes tubes3 = new Tubes();
    private Player player = new Player();
    private boolean isPlaying = true, isGrown;
    private Text text, textScore;
    private int score;
    private Music bgm;
    private Sound death;


    public void init() throws InterruptedException {
        playSpace.init();
        tubes1.spawnTubes(800, Random.randomInt(-900, 0));
        tubes2.spawnTubes(1200, Random.randomInt(-900, 0));
        tubes3.spawnTubes(1600, Random.randomInt(-900, 0));
        text = new Text( 70, 50, "SCORE:");
        text.setColor(Color.WHITE);
        text.draw();
        text.grow(50, 50);
        textScore = new Text(185, 50, Integer.toString(score));
        textScore.draw();
        textScore.grow(12, 50);

        player.init();

        TinySound.init();

        bgm = TinySound.loadMusic(new File(Main.PREFIX + "game_music.wav"));
        death = TinySound.loadSound(new File(Main.PREFIX + "death.wav"));


        if (bgm != null) {
            bgm.play(true);
        }

        while(isPlaying){

            Thread.sleep(16);
            tubes1.moveAll();
            tubes2.moveAll();
            tubes3.moveAll();
            player.move();
            collisionDetector(tubes1);
            collisionDetector(tubes2);
            collisionDetector(tubes3);
            sumScore();

        }
    }

    public boolean collisionDetector(Tubes tubes) {

        if (player.getY() + player.getHeight() >= playSpace.getBackgroundHeight() ||

            player.getX() + player.getWidth() >= tubes.getUpperX() &&
            player.getY() + player.getHeight() >= tubes.getUpperY() &&
            tubes.getUpperX() + tubes.getUpperWidth() >= player.getX() &&
            tubes.getUpperY() + tubes.getUpperHeight() >= player.getY() ||

            player.getX() + player.getWidth() >= tubes.getLowerX() &&
            player.getY() + player.getHeight() >= tubes.getLowerY() &&
            tubes.getLowerX() + tubes.getLowerWidth() >= player.getX() &&
            tubes.getLowerY() + tubes.getLowerHeight() >= player.getY() ) {

                isPlaying = false;
                player.removeJumpMechanic();
                player.setDeadPicture();
                bgm.stop();
                death.play();
        }

        return isPlaying;
    }

    public void sumScore() {

        if (player.getX() > tubes1.getUpperX() + tubes1.getUpperWidth() && !tubes1.isPassed()) {
            score ++;
            tubes1.setPassed();
            textScore.setText(Integer.toString(score));
        }

        if (player.getX() > tubes2.getUpperX() + tubes2.getUpperWidth() && !tubes2.isPassed()) {
            score ++;
            tubes2.setPassed();
            textScore.setText(Integer.toString(score));
        }

        if (player.getX() > tubes3.getUpperX() + tubes3.getUpperWidth() && !tubes3.isPassed()) {
            score ++;
            tubes3.setPassed();
            textScore.setText(Integer.toString(score));
        }

        if (score == 10 && !isGrown) {
            textScore.grow(12, 0);
            isGrown = true;
        }

        if (score == 100 && isGrown) {
            textScore.grow(12, 0);
            isGrown = false;
        }
    }
}
