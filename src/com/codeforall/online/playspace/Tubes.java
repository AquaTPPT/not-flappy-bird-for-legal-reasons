package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.online.statics.Random;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.pictures.Picture;

public class Tubes {
    private boolean passed, goingUp;
    private int spawnTubesX, spawnTubesY;
    private Rectangle upperHitbox, lowerHitbox;
    private Picture upperTube, lowerTube;

    public void spawnTubes(int startingX, int startingY) {
        spawnTubesX = startingX;
        spawnTubesY = startingY;

        upperTube = new Picture(startingX - 20, startingY, Main.PREFIX + "tube_upper.png");
        upperTube.draw();

        upperHitbox = new Rectangle(startingX,startingY, 100,1000);
        upperHitbox.setColor(Color.ORANGE);
        upperHitbox.draw();

        lowerTube = new Picture(startingX - 20, startingY + 1200, Main.PREFIX + "tube_lower.png");
        lowerTube.draw();

        lowerHitbox = new Rectangle(startingX, startingY + 1200, 100,1000);
        lowerHitbox.setColor(Color.ORANGE);
        lowerHitbox.draw();
    }

    public void restartPosition() {
        resetPosition(spawnTubesX ,spawnTubesY);
    }

    public void move() {
        upperHitbox.translate(-3 , 0);
        lowerHitbox.translate(-3 , 0);
        upperTube.translate(-3, 0);
        lowerTube.translate(-3, 0);
    }

    public void resetPosition(int x, int y) {
        int dxUpper = x - upperHitbox.getX();
        int dyUpper = y - upperHitbox.getY();
        int dxLower = x - lowerHitbox.getX();
        int dyLower = y - lowerHitbox.getY();

        upperHitbox.translate(dxUpper, dyUpper);
        upperTube.translate(dxUpper, dyUpper);
        lowerHitbox.translate(dxLower, dyLower + 1200);
        lowerTube.translate(dxLower, dyLower + 1200);

        passed = false;
    }

    public void moveAll() {

        move();

        if (upperHitbox.getX() + upperHitbox.getWidth() + 40 < 0) {
            int newX = 1060;
            int newUpperY = Random.randomInt(-800, -300);
            int newLowerY = newUpperY + 1200;

            resetPosition(newX, newUpperY);
        }

    }

    public void constantMove() {
        if (getUpperY() >= -300) {
            goingUp = false;
        }
        else if (getUpperY() <= -800) {
            goingUp = true;
        }
        if (!goingUp) {
            upperHitbox.translate(0, -1);
            upperTube.translate(0, -1);
            lowerHitbox.translate(0, -1);
            lowerTube.translate(0, -1);
        }
        else {
            upperHitbox.translate(0, 1);
            upperTube.translate(0, 1);
            lowerHitbox.translate(0, 1);
            lowerTube.translate(0, 1);
        }

    }

    public int getUpperWidth() {
        return upperHitbox.getWidth();
    }
    public int getUpperHeight() { return upperHitbox.getHeight(); }
    public int getUpperX() {
        return upperHitbox.getX();
    }
    public int getUpperY() {
        return upperHitbox.getY();
    }


    public int getLowerWidth() {
        return lowerHitbox.getWidth();
    }
    public int getLowerHeight() { return lowerHitbox.getHeight(); }
    public int getLowerX() {
        return lowerHitbox.getX();
    }
    public int getLowerY() {
        return lowerHitbox.getY();
    }

    public void setPassed() {
        passed = true;
    }
    public boolean isPassed() {
        return passed;
    }

}