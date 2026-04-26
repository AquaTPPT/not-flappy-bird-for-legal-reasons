package com.codeforall.online.playspace;

import com.codeforall.online.Game;
import com.codeforall.online.Main;
import com.codeforall.online.Player.MouseInteraction;
import com.codeforall.online.UI.Menus;
import com.codeforall.online.statics.Random;
import com.codeforall.simplegraphics.graphics.Canvas;
import com.codeforall.simplegraphics.pictures.Picture;


public class Playspace implements Runnable {
    private int backgroundHeight;
    private Menus menus;
    private MouseInteraction mouseInteraction;
    private Game game;
    private Clouds clouds;
    private Thread playspaceThread;


    public Playspace(Game game) {
        mouseInteraction = game.getMouseInteraction();
        menus = new Menus(this, mouseInteraction);
        this.game = game;
        clouds = new Clouds();
        playspaceThread = new Thread(this, "Thread-Playspace");
    }
    public void init() {
        Canvas.setMaxX(720);
        Canvas.setMaxY(1080);

        Picture background = new Picture(0, 300, Main.PREFIX + "bg_5.png");
        background.draw();
        background.grow(370,800);
        backgroundHeight = background.getHeight();

        clouds.createClouds();
        clouds.drawClouds();
        startThread();
    }

    // this will have the clouds and the tubes inside as well.

    public Game getGame() {
        return game;
    }

    public int getBackgroundHeight() {
        return backgroundHeight;
    }

    public void startPauseMenu() { menus.startPauseMenu(mouseInteraction); }

    public Menus getMenus() {
        return menus;
    }

    public MouseInteraction getMouseInteraction() {
        return mouseInteraction;
    }


    public void startThread() {
        playspaceThread.start();
    }

    @Override
    public void run() {
        while (true) {
            clouds.move();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}