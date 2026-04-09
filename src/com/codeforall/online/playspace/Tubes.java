package com.codeforall.online.playspace;

import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.pictures.Picture;

public class Tubes {
    int baseSpawnModifier = 800;
    int lowerTubeSpawnModifier = 950;
    private UpperTube upperTube;
    private LowerTube lowerTube;

    public void spawnTubes(int startingX, int startingY) {
        upperTube = new UpperTube(startingX, startingY); // min y: -800
        lowerTube = new LowerTube(startingX, startingY + 1200); // Max y - 1060
    }

    class UpperTube {
        private Rectangle hitbox;
        private Picture image;

        public UpperTube(int startingX, int startingY) {
            hitbox = new Rectangle(startingX,startingY, 100,1000);
            hitbox.draw();

            image = new Picture(startingX, startingY, "assets/tube_upper.png");
            image.draw();
        }

        public void move() {
            hitbox.translate(-10 , 0);
            image.translate(-10, 0);
        }

    }

    class LowerTube {
        private Rectangle hitbox;
        private Picture image;

        public LowerTube(int startingX, int startingY) {
            hitbox = new Rectangle(startingX, startingY, 100,1000);
            hitbox.draw();

            image = new Picture(startingX, startingY, "assets/tube_lower.png");
            image.draw();
        }

        public void move() {
            hitbox.translate(-10 , 0);
            image.translate(-10, 0);
        }

    }

    public void moveAll() {
        if (upperTube != null && lowerTube != null) {
            upperTube.move();
            lowerTube.move();
        }
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
