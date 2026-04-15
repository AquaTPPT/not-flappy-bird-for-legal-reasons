package com.codeforall.online.playspace;

import com.codeforall.online.Game;
import com.codeforall.online.Main;
import com.codeforall.online.Player.MouseInteraction;
import com.codeforall.simplegraphics.graphics.Canvas;
import com.codeforall.simplegraphics.pictures.Picture;


public class Playspace {
    private int backgroundHeight;
    private Menus menus;
    private MouseInteraction mouseInteraction;

    public Playspace(Game game) {
        mouseInteraction = game.getMouseInteraction();
        menus = new Menus(this, mouseInteraction);
    }
    public void init() {
        Canvas.setMaxX(720);
        Canvas.setMaxY(1080);

        Picture background = new Picture(0, 300, Main.PREFIX + "bg_5.png");
        background.draw();
        background.grow(370,800);
        backgroundHeight = background.getHeight();
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
}
