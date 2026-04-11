package com.codeforall.online.Player;

import com.codeforall.online.Game;
import com.codeforall.online.playspace.Menus;
import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;
import com.codeforall.simplegraphics.mouse.Mouse;
import com.codeforall.simplegraphics.mouse.MouseHandler;

public class KeyboardInteraction implements KeyboardHandler {
    private Keyboard k;
    private KeyboardEvent jump;
    private KeyboardEvent pause;
    private Player player;
    private Game game;

    public KeyboardInteraction(Player player, Game game) {
        this.player = player;
        this.game = game;
    }

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
        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            player.jump();
        }
        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ESC) {
            game.stopGame();
            k.removeEventListener(pause);
            player.startPauseMenu();

        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }


    public KeyboardEvent getJump() {
        return jump;
    }

    public KeyboardEvent getPause() {
        return pause;
    }

    public void removeESCButton() { k.removeEventListener(pause); }

    public void addESCButton() { k.addEventListener(pause); }

    public void removeJumpMechanic() { k.removeEventListener(jump); }

    public void addJumpMechanic() { k.addEventListener(jump); }
}
