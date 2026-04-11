package com.codeforall.online;

import com.codeforall.online.Player.*;
import com.codeforall.online.playspace.*;
import com.codeforall.online.statics.Random;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Text;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.File;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Text;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.awt.*;
import java.io.File;


public class Game implements ActionListener {
    private Playspace playSpace;
    private Menus menus;
    private MouseInteraction mouseInteraction;
    private KeyboardInteraction keyboardInteraction;
    private Timer timer = new Timer(16, this);
    private Tubes tubes1 = new Tubes();
    private Tubes tubes2 = new Tubes();
    private Tubes tubes3 = new Tubes();
    private Player player = new Player(this);
    private boolean isPlaying = true, isGrown;
    private Text text, textScore;
    private int score;
    private Music bgm;
    private Sound death;

    public Game() {
        mouseInteraction = new MouseInteraction(this);
        playSpace = new Playspace(this);
        menus = playSpace.getMenus();
        keyboardInteraction = new KeyboardInteraction(player,this);
    }

    public void init() {
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

        player.init(playSpace);

        TinySound.init();
        bgm = TinySound.loadMusic(new File(Main.PREFIX + "game_music.wav"));
        death = TinySound.loadSound(new File(Main.PREFIX + "death.wav"));

        if (bgm != null) {
            bgm.play(true);
        }

        keyboardInteraction.initializeKeyboard();
        mouseInteraction.initializeMouse();
        timer.start();
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
            keyboardInteraction.removeJumpMechanic();
            player.setDeadPicture();
            bgm.stop();
            death.play();
            timer.stop();
        }
        return isPlaying;
    }

    public MouseInteraction getMouseInteraction() {
        return mouseInteraction;
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


    public void resumeGame() { timer.start(); }

    public void stopGame() { timer.stop(); }

    public Menus getMenus() { return menus; }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
            tubes1.moveAll();
            tubes2.moveAll();
            tubes3.moveAll();
            player.move();
            collisionDetector(tubes1);
            collisionDetector(tubes2);
            collisionDetector(tubes3);
    }
}