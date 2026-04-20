package com.codeforall.online;

import com.codeforall.online.Player.*;
import com.codeforall.online.playspace.*;
import com.codeforall.online.sound.GameSound;
import com.codeforall.online.statics.Random;
import kuusisto.tinysound.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Game implements ActionListener {

    // game area
    private Playspace playSpace;
    private Tubes tubes1 = new Tubes();
    private Tubes tubes2 = new Tubes();
    private Tubes tubes3 = new Tubes();

    // Menus
    private Menus menus;

    // Interactions
    private MouseInteraction mouseInteraction;
    private KeyboardInteraction keyboardInteraction;

    // Game loop and obstacles (note: the tubes can be set elsewhere, doesn't need to be here)
    private Timer timer = new Timer(16, this);

    // Player
    private Player player = new Player();
    private boolean isPlaying = true, isPaused = false, isDead = false, isStarted = false;

    //Score
    private Score score;

    // Sound Effects
    private boolean isMuted = false;
    private GameSound gameSound = new GameSound();

    public Game() {
        mouseInteraction = new MouseInteraction(this);
        playSpace = new Playspace(this);
        menus = playSpace.getMenus();
        keyboardInteraction = new KeyboardInteraction(player,this);
        init();
    }

    public void init() {
        playSpace.init();
        menus.startMainMenu();
        keyboardInteraction.initializeKeyboard();
    }

    public void initGame() {
        tubes1.spawnTubes(800, Random.randomInt(-800, -300));
        tubes2.spawnTubes(1200, Random.randomInt(-800, -300));
        tubes3.spawnTubes(1600, Random.randomInt(-800, -300));

        score = new Score();
        score.init();

        player.init(playSpace);
    }

    public void restartGame() {
        player.playerMovetoSpawn();
        tubes1.resetPosition();
        tubes2.resetPosition();
        tubes3.resetPosition();
        startGame();
        menus.closeGameOverScreen();
        score.restartScore();
    }

    public void startGame() {
        gameSound.playBgm();
        timer.start();
    }

    public void collisionDetector(Tubes tubes) {

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
            isPlaying = false;
            isDead = true;

            gameSound.stopBgm();
            gameSound.playDeath();
            timer.stop();
            menus.startGameOverScreen();
        }
    }

    public MouseInteraction getMouseInteraction() {
        return mouseInteraction;
    }

    public void sumScore() {

        if (player.getX() > tubes1.getUpperX() + tubes1.getUpperWidth() && !tubes1.isPassed()) {

            score.increment();
            gameSound.playScore();
            tubes1.setPassed();
        }

        if (player.getX() > tubes2.getUpperX() + tubes2.getUpperWidth() && !tubes2.isPassed()) {

            score.increment();
            gameSound.playScore();
            tubes2.setPassed();
        }

        if (player.getX() > tubes3.getUpperX() + tubes3.getUpperWidth() && !tubes3.isPassed()) {

            score.increment();
            gameSound.playScore();
            tubes3.setPassed();
        }
    }

    public void resumeGame() {
        isPlaying = true;
        timer.start();
        gameSound.resumeBgm();
    }

    public void stopGame() {
        isPlaying = false;
        timer.stop();
        gameSound.pauseBgm();
        gameSound.stopDeath();
        gameSound.stopScore();
    }

    public Menus getMenus() { return menus; }

    public boolean isMuted() {
        return isMuted;
    }
    public boolean isMuted(boolean set) {
        return isMuted = set;
    }

    public boolean isStarted(boolean val) { return isStarted = val; }
    public boolean isStarted() { return isStarted; }

    public boolean isDead(boolean set) { return isDead = set; }
    public boolean isDead() { return isDead; }

    public boolean isPlaying(boolean set) { return isPlaying = set; }
    public boolean isPlaying() { return isPlaying; }

    public boolean isPaused(boolean val) { return isPaused = val; }
    public boolean isPaused() { return isPaused; }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
            tubes1.moveAll();
            tubes2.moveAll();
            tubes3.moveAll();
            player.move();
            collisionDetector(tubes1);
            collisionDetector(tubes2);
            collisionDetector(tubes3);
            sumScore();
    }

    public KeyboardInteraction getKeyboardInteraction() {
        return keyboardInteraction;
    }
}