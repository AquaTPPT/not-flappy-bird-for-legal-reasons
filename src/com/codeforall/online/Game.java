package com.codeforall.online;

import com.codeforall.online.Player.*;
import com.codeforall.online.playspace.*;
import com.codeforall.online.statics.Random;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
    private boolean isPlaying = true;

    public Game() {
        mouseInteraction = new MouseInteraction(this);
        playSpace = new Playspace(this);
        menus = playSpace.getMenus();
        keyboardInteraction = new KeyboardInteraction(player,this);
    }

    public void init() throws InterruptedException {
        playSpace.init();
        tubes1.spawnTubes(800, Random.randomInt(-900, 0));
        tubes2.spawnTubes(1200, Random.randomInt(-900, 0));
        tubes3.spawnTubes(1600, Random.randomInt(-900, 0));

        player.init(playSpace);
        keyboardInteraction.initializeKeyboard();
        mouseInteraction.initializeMouse();
        timer.start();
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
            keyboardInteraction.removeJumpMechanic();
            timer.stop();
        }
        return isPlaying;
    }

    public MouseInteraction getMouseInteraction() {
        return mouseInteraction;
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