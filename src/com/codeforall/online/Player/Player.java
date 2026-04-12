package com.codeforall.online.Player;

import com.codeforall.online.Main;
import com.codeforall.online.Game;
import com.codeforall.online.playspace.Menus;
import com.codeforall.online.playspace.Playspace;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;
import com.codeforall.simplegraphics.pictures.Picture;

import java.util.Timer;
import java.util.TimerTask;

public class Player  {
    private Rectangle rectangle;
    private Playspace playspace;
    private Game game;
    private Picture birdOpen, birdClosed, birdDive;
    private double gravity = 0.8;
    private double velocity = 0;

    public Player(Game game) {
        this.game = game;
        rectangle = new Rectangle(100,300,50,50);
        birdOpen = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_open.png");
        birdOpen.grow(10,10);
        birdClosed = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_closed1.png");
        birdClosed.grow(10,10);
        birdDive = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_dive.png");
        birdDive.grow(10,10);

    }

    public void init(Playspace playspace) {
        this.playspace = playspace;
        //birdDive.draw();
        //birdClosed.draw();
        //rectangle.setColor(Color.MAGENTA);
        //rectangle.draw();
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
                birdClosed.draw();;
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


