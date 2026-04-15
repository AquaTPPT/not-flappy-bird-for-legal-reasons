package com.codeforall.online.Player;

import com.codeforall.online.Main;
import com.codeforall.online.playspace.Playspace;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.pictures.Picture;

public class Player  {
    private Rectangle rectangle;
    private Playspace playspace;
    private Picture birdOpen, birdClosed, birdDive;
    private double velocity = 0;
    private int spawnX = 100, spawnY = 300;

    public Player() {
        rectangle = new Rectangle(spawnX, spawnY,50,50);
        birdOpen = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_open.png");
        birdOpen.grow(10,10);
        birdClosed = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_closed1.png");
        birdClosed.grow(10,10);
        birdDive = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_dive.png");
        birdDive.grow(10,10);

    }

    public void playerMovetoSpawn() {
        rectangle.translate(0,spawnY - rectangle.getY());
        birdDive.translate(0,spawnY - birdDive.getY());
        birdOpen.translate(0,spawnY - birdOpen.getY());
        birdClosed.translate(0,spawnY - birdClosed.getY());
        velocity = 0;

        birdOpen.load(Main.PREFIX + "bird_open.png");
        birdClosed.load(Main.PREFIX + "bird_closed1.png");
        birdDive.load(Main.PREFIX + "bird_dive.png");
    }

    public void init(Playspace playspace) {
        this.playspace = playspace;
    }

    public void jump() {
        birdOpen.translate(0, -60);
        birdClosed.translate(0, -60);
        birdDive.translate(0, -60);
        rectangle.translate(0, -60);
        velocity = -8;
    }

    public void startPauseMenu() {
        playspace.startPauseMenu();
    }

    private int frames = 0;

    public void move() {
        double gravity = 0.8;
        velocity += gravity;

        if (velocity >= 0){
            frames = 0;
        }

        if (velocity > 8) {
            birdDive.draw();
            birdOpen.delete();
            birdClosed.delete();

        }

        if (velocity < 0) {
            birdOpen.draw();
            birdDive.delete();
            frames++;

            if (frames % 10 == 0) {
                birdClosed.draw();
                birdOpen.delete();

            } else {
                birdOpen.draw();
                birdClosed.delete();

            }

        }

        birdOpen.translate(0, velocity);
        birdDive.translate(0, velocity);
        birdClosed.translate(0, velocity);
        rectangle.translate(0, velocity);

    }

    public int getX() { return rectangle.getX(); }

    public int getY() { return rectangle.getY(); }

    public int getWidth() { return rectangle.getWidth(); }

    public int getHeight() { return rectangle.getHeight(); }

    public void setDeadPicture() {
        birdOpen.load(Main.PREFIX + "bird_dead1.png");
        birdDive.load(Main.PREFIX + "bird_dead1.png");
        birdClosed.load(Main.PREFIX + "bird_dead1.png");
    }

}


