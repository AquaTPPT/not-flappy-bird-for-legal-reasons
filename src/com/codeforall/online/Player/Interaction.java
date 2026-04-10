package com.codeforall.online.Player;

import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;
import com.codeforall.simplegraphics.mouse.Mouse;
import com.codeforall.simplegraphics.mouse.MouseEvent;
import com.codeforall.simplegraphics.mouse.MouseEventType;
import com.codeforall.simplegraphics.mouse.MouseHandler;

public class Interaction implements KeyboardHandler, MouseHandler {
    private Keyboard k;
    private Mouse mouse;
    private KeyboardEvent jump;
    private KeyboardEvent pause;
    private Player player;
    private

    public void initializeKeyboard() {
        k = new Keyboard(this);
        jump = new KeyboardEvent();
        jump.setKey(KeyboardEvent.KEY_SPACE);
        jump.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(jump);

        pause = new KeyboardEvent();
        pause.setKey(KeyboardEvent.KEY_ESC);
        pause.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pause);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void initializeMouse() {
        mouse = new Mouse(this);
    }

    public void addMouseListener() {
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    public void removeMouseListener() {
        mouse.removeEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public KeyboardEvent getJump() {
        return jump;
    }

    public KeyboardEvent getPause() {
        return pause;
    }
}
