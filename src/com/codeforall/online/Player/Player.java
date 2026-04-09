package com.codeforall.online.Player;

import com.codeforall.online.Main;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;
import com.codeforall.simplegraphics.pictures.Picture;

import java.util.Timer;
import java.util.TimerTask;

public class Player implements KeyboardHandler {
    private Rectangle rectangle;
    private Keyboard k;
    private KeyboardEvent jump;
    private Picture birdOpen, birdClosed, birdDive;
    private double gravity = 0.8;
    private double velocity = 0;

    public Player() {
        rectangle = new Rectangle(100,300,50,50);
        birdOpen = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_open.png");
        birdOpen.grow(10,10);
        birdClosed = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_closed1.png");
        birdClosed.grow(10,10);
        birdDive = new Picture(rectangle.getX(),rectangle.getY(), Main.PREFIX + "bird_dive.png");
        birdDive.grow(10,10);

    }

    public void init() {
        birdOpen.draw();
        //birdDive.draw();
        //birdClosed.draw();
        //rectangle.setColor(Color.MAGENTA);
        //rectangle.draw();
        initializeKeyboard();
    }

    public void initializeKeyboard() {
        k = new Keyboard(this);
        jump = new KeyboardEvent();
        jump.setKey(KeyboardEvent.KEY_SPACE);
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(jump);

        KeyboardEvent quit = new KeyboardEvent();
        quit.setKey(KeyboardEvent.KEY_ESC);
        quit.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        k.addEventListener(quit);


    }




    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {

            birdOpen.translate(0, -60);
            birdClosed.translate(0, -60);
            birdDive.translate(0, -60);
            rectangle.translate(0, -60);
            velocity = -8;
        }


        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ESC) {
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private int frames = 0;

    public void move (){


        velocity += gravity;

        if (velocity >= 0){
            frames = 0;
        }

        if (velocity > 8) {
            birdOpen.delete();
            birdClosed.delete();
            birdDive.draw();
        }

        if (velocity < 0) {
            birdDive.delete();
            birdOpen.draw();
            frames++;

            if (frames % 10 == 0) {
                birdOpen.delete();
                birdClosed.draw();;
            } else {
                birdClosed.delete();
                birdOpen.draw();
            }

        }

        birdOpen.translate(0, velocity);
        birdDive.translate(0, velocity);
        birdClosed.translate(0, velocity);
        rectangle.translate(0, velocity);

        System.out.println(frames);


    }

    public int getX() { return rectangle.getX(); }

    public int getY() { return rectangle.getY(); }

    public int getWidth() { return rectangle.getWidth(); }

    public int getHeight() { return rectangle.getHeight(); }

    public void removeJumpMechanic() { k.removeEventListener(jump);}

    public void setDeadPicture() {
        birdOpen.load("assets/bird_dead1.png");
        birdDive.load("assets/bird_dead1.png");
        birdClosed.delete();
    }

}


