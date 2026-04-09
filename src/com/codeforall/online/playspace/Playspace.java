package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.simplegraphics.graphics.Canvas;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.pictures.Picture;


public class Playspace {
    private Rectangle rectangle;
    private final int CELLSIZE = 25;
    private int backgroundHeight;
    private int backgroundWidth;
    private Picture background;

    public void init() {
        Canvas.setMaxX(720);
        Canvas.setMaxY(1600);

        // only for testing screen res, delete this later.

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
}
