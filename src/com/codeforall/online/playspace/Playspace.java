package com.codeforall.online.playspace;

import com.codeforall.online.Game;
import com.codeforall.online.Main;
import com.codeforall.online.Player.MouseInteraction;
import com.codeforall.simplegraphics.graphics.Canvas;
import com.codeforall.simplegraphics.pictures.Picture;


public class Playspace {
    private int backgroundHeight;
    private int backgroundWidth;
    private Picture background;
    private Menus menus;
    private MouseInteraction mouseInteraction;

    public Playspace(Game game) {
        menus = new Menus(this);
        mouseInteraction = game.getMouseInteraction();
    }
    public void init() {
        Canvas.setMaxX(720);
        Canvas.setMaxY(1600);

        background = new Picture(0, 300, Main.PREFIX + "bg_5.png");
        background.draw();
        background.grow(370,800);
        backgroundHeight = background.getHeight();
        backgroundWidth = background.getWidth();
    }

    public int getBackgroundHeight() {
        return backgroundHeight;
    }

    public int getBackgroundWidth() {
        return backgroundWidth;
    }

    public void startPauseMenu() { menus.startPauseMenu(mouseInteraction); }

    public Menus getMenus() {
        return menus;
    }

    public int getCenterWidth() { return backgroundWidth / 2; }

    public int getCenterHeight() { return backgroundHeight / 2; }
}
