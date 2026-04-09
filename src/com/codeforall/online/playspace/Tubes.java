package com.codeforall.online.playspace;

import com.codeforall.online.statics.Random;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.pictures.Picture;

public class Tubes {
    int baseSpawnModifier = 800;
    int lowerTubeSpawnModifier = 950;
    private UpperTube upperTube;
    private LowerTube lowerTube;

    public void spawnTubes(int startingX, int startingY) {
        upperTube = new UpperTube(startingX, startingY); // min y: -800
        lowerTube = new LowerTube(startingX, startingY + 1200);// Max y - 1060
    }

    class UpperTube {
        private Rectangle hitbox;
        private Picture image;

        public UpperTube(int startingX, int startingY) {


            image = new Picture(startingX - 20, startingY, "assets/tube_upper.png");
            image.draw();

            hitbox = new Rectangle(startingX,startingY, 100,1000);
            hitbox.setColor(Color.ORANGE);
            hitbox.draw();
        }

        public void move() {
            hitbox.translate(-3 , 0);
            image.translate(-3, 0);
        }

        public void resetPosition(int x, int y) {
            int dx = x - hitbox.getX();
            int dy = y - hitbox.getY();

            hitbox.translate(dx, dy);
            image.translate(dx, dy);
        }

    }

    class LowerTube {
        private Rectangle hitbox;
        private Picture image;

        public LowerTube(int startingX, int startingY) {

            image = new Picture(startingX - 20, startingY, "assets/tube_lower.png");
            image.draw();

            hitbox = new Rectangle(startingX, startingY, 100,1000);
            hitbox.setColor(Color.ORANGE);
            hitbox.draw();
        }

        public void move() {
            hitbox.translate(-3 , 0);
            image.translate(-3, 0);
        }

        public void resetPosition(int x, int y) {
            int dx = x - hitbox.getX();
            int dy = y - hitbox.getY();

            hitbox.translate(dx, dy);
            image.translate(dx, dy);
        }

    }

    public void moveAll() {
        if (upperTube != null && lowerTube != null) {
            upperTube.move();
            lowerTube.move();
        }

        if (upperTube.hitbox.getX() + upperTube.hitbox.getWidth() + 40 < 0) {
            int newX = 1100;
            int newUpperY = Random.randomInt(-900, 0);
            int newLowerY = newUpperY + 1200;

            upperTube.resetPosition(newX, newUpperY);
            lowerTube.resetPosition(newX, newLowerY);
        }

    }


    public int getWidth() {
        return upperTube.hitbox.getWidth();
    }


    public int getUpperX() {
        return upperTube.hitbox.getX();
    }

    public int getUpperY() {
        return upperTube.hitbox.getY();
    }

    public int getLowerX() {
        return lowerTube.hitbox.getX();
    }

    public int getLowerY() {
        return lowerTube.hitbox.getY();
    }

}
