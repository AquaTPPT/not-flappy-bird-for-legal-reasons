package com.codeforall.online.Player;

import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;

public class Player implements KeyboardHandler {
    private Rectangle rectangle;
    private Keyboard k;

    public Player() {
        rectangle = new Rectangle(100,300,75,75);
    }

    public void init() {
        rectangle.setColor(Color.MAGENTA);
        rectangle.fill();
        initializeKeyboard();
    }

    public void initializeKeyboard() {
        k = new Keyboard(this);
        KeyboardEvent jump = new KeyboardEvent();
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
            rectangle.translate(0, -100);
            speed = 0.5;

            System.out.println("spacebar pressed!");
        }


        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ESC) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private double speed = 0.5;

    public void move (){

        rectangle.translate(0, speed * 1.1);
        speed = speed * 1.1;

    }
}
