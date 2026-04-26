package com.codeforall.online;

import com.codeforall.online.Player.*;
import com.codeforall.online.UI.Menus;
import com.codeforall.online.UI.Score;
import com.codeforall.online.playspace.*;
import com.codeforall.online.sound.GameSound;
import com.codeforall.online.statics.Random;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Game implements ActionListener {

    // game area
    private Playspace playSpace;
    private TubeManager tubeManager;
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
        tubeManager = new TubeManager();
        init();
    }

    public void init() {
        playSpace.init();
        menus.startMainMenu();
        keyboardInteraction.initializeKeyboard();
    }

    public void initGame() {
        tubeManager.spawnTubes();

        score = new Score();
        score.initScore();
        score.initHighScore();
        try {
            score.highScoreFileChecker();
            score.highScoreChecker();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        player.init(playSpace);

    }

    public void restartGame() {
        player.playerMovetoSpawn();
        tubeManager.restartTubes();
        startGame();
        menus.closeGameOverScreen();
        score.restartScore();
    }

    public void startGame() {
        gameSound.playBgm();
        timer.start();
    }

    public void collisionDetector(Tubes tubes) throws IOException {

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
            score.highScoreChecker();
        }
    }

    public MouseInteraction getMouseInteraction() {
        return mouseInteraction;
    }

    public void sumScore() {

        if (player.getX() > tubeManager.getTubes1().getUpperX() + tubeManager.getTubes1().getUpperWidth() && !tubeManager.getTubes1().isPassed()) {

            score.increment();
            gameSound.playScore();
            tubeManager.getTubes1().setPassed();
        }

        if (player.getX() > tubeManager.getTubes2().getUpperX() + tubeManager.getTubes2().getUpperWidth() && !tubeManager.getTubes2().isPassed()) {

            score.increment();
            gameSound.playScore();
            tubeManager.getTubes2().setPassed();
        }

        if (player.getX() > tubeManager.getTubes3().getUpperX() + tubeManager.getTubes3().getUpperWidth() && !tubeManager.getTubes3().isPassed()) {

            score.increment();
            gameSound.playScore();
            tubeManager.getTubes3().setPassed();
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

        tubeManager.moveAllTubes();
        player.move();
        try {
            collisionDetector(tubeManager.getTubes1());
            collisionDetector(tubeManager.getTubes2());
            collisionDetector(tubeManager.getTubes3());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            sumScore();
            if (score.getScore() >= 10) {
                tubeManager.constantMoveTubes();
            }
    }

    public KeyboardInteraction getKeyboardInteraction() {
        return keyboardInteraction;
    }
}