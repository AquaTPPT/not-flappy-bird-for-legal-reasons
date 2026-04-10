package com.codeforall.online.Player;

import com.codeforall.online.Game;
import com.codeforall.online.playspace.PauseMenu;
import com.codeforall.online.playspace.Playspace;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.keyboard.Keyboard;
import com.codeforall.simplegraphics.keyboard.KeyboardEvent;
import com.codeforall.simplegraphics.keyboard.KeyboardEventType;
import com.codeforall.simplegraphics.keyboard.KeyboardHandler;

public class Player implements KeyboardHandler {
    private Rectangle rectangle;
    private Playspace playspace;
    private Game game;

    public Player(Game game) {
        this.game = game;
        rectangle = new Rectangle(100,300,50,50);
    }

    public void init(Playspace playspace) {
        this.playspace = playspace;
        rectangle.setColor(Color.MAGENTA);
        rectangle.fill();
    //    initializeKeyboard();
    }

 /*
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
    }

  */

    public void jump() {
        rectangle.translate(0, -60);
        velocity = -8;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

      /*  if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE) {
            rectangle.translate(0, - 60);
            velocity = -8;
        }
       */


        if(keyboardEvent.getKey() == KeyboardEvent.KEY_ESC) {
            game.isPlaying(false);
            playspace.startPauseMenu();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    private double gravity = 0.8;
    private double velocity = 0;

    public void move (){
        velocity += gravity;

        rectangle.translate(0, velocity);

    }

    public int getX() { return rectangle.getX(); }

    public int getY() { return rectangle.getY(); }

    public int getWidth() { return rectangle.getWidth(); }

    public int getHeight() { return rectangle.getHeight(); }

    public void removeJumpMechanic() { k.removeEventListener(jump);}
}


