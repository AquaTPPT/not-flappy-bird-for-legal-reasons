package com.codeforall.online.Player;

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
        rectangle.draw();
        initializeKeyboard();
    }

    public void initializeKeyboard() {
        k = new Keyboard(this);
        KeyboardEvent jump = new KeyboardEvent();
        jump.setKey(KeyboardEvent.KEY_SPACE);
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(jump);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        System.out.println("spacebar pressed!");
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}
