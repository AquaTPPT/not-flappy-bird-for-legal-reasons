package com.codeforall.online.Player;

import com.codeforall.online.Game;
import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;

public class KeyboardInteraction implements KeyboardHandler {
    private Keyboard k;
    private KeyboardEvent jump;
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

        KeyboardEvent pause = new KeyboardEvent();
        pause.setKey(KeyboardEvent.KEY_ESC);
        pause.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        k.addEventListener(pause);

        KeyboardEvent quit = new KeyboardEvent();
        quit.setKey(KeyboardEvent.KEY_Q);
        quit.setKeyboardEventType((KeyboardEventType.KEY_PRESSED));
        k.addEventListener(quit);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_Q) {
            System.exit(0);
        }


        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE && !game.isPaused()) {
            player.jump();
        }

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_ESC && !game.isPaused() && !game.isDead()) {
            game.stopGame();
            game.isPaused(true);
            player.startPauseMenu();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }

    public void removeJumpMechanic() { k.removeEventListener(jump); }

    public void addJumpMechanic() { k.addEventListener(jump); }
}
