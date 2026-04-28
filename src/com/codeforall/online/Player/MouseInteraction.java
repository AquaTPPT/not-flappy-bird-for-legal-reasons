package com.codeforall.online.Player;

import com.codeforall.online.Game;
import com.codeforall.online.GameState;
import com.codeforall.online.UI.Menus;
import com.codeforall.simplegraphics.mouse.Mouse;
import com.codeforall.simplegraphics.mouse.MouseEvent;
import com.codeforall.simplegraphics.mouse.MouseEventType;
import com.codeforall.simplegraphics.mouse.MouseHandler;
import kuusisto.tinysound.*;

public class MouseInteraction implements MouseHandler {
    private Mouse mouse;
    private Game game;

    public MouseInteraction(Game game) {
        this.game = game;
    }

    public void initializeMouse() {
        mouse = new Mouse(this);
        addMouseListener();
    }

    public void addMouseListener() {
        mouse.addEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        GameState currentState = game.getState();
        Menus menus = game.getMenus();


        if (currentState == GameState.PAUSED) {
            if (mouseEvent.getY() >= menus.getButtonY() &&
                    mouseEvent.getY() <= menus.getButtonHeight() + menus.getButtonY() &&
                    mouseEvent.getX() >= menus.getButtonX() &&
                    mouseEvent.getX() <= menus.getButtonWidth() + menus.getButtonX()) {

                menus.removePauseMenu();
                game.resumeGame();
            }
        }

        // 2. GAME OVER LOGIC
        if (currentState == GameState.GAME_OVER) {
            if (mouseEvent.getY() >= menus.getButton1Y() &&
                    mouseEvent.getY() <= menus.getButton1Height() + menus.getButton1Y() &&
                    mouseEvent.getX() >= menus.getButton1X() &&
                    mouseEvent.getX() <= menus.getButton1Width() + menus.getButton1X()) {

                game.getKeyboardInteraction().addJumpMechanic();
                game.restartGame();
            }
        }

        // 3. MAIN MENU LOGIC
        if (currentState == GameState.MENU) {
            if (mouseEvent.getY() >= menus.getButton1Y() &&
                    mouseEvent.getY() <= menus.getButton1Height() + menus.getButton1Y() &&
                    mouseEvent.getX() >= menus.getButton1X() &&
                    mouseEvent.getX() <= menus.getButton1Width() + menus.getButton1X()) {

                menus.removeStartMenu();
                game.initGame();
                game.startGame();
            }
        }

        // 4. MUTE BUTTON LOGIC (Global)
        if (mouseEvent.getY() >= menus.getMuteButtonY() &&
                mouseEvent.getY() <= menus.getMuteButtonHeight() + menus.getMuteButtonY() &&
                mouseEvent.getX() >= menus.getMuteButtonX() &&
                mouseEvent.getX() <= menus.getMuteButtonWidth() + menus.getMuteButtonX()) {

            if (!game.isMuted()) {
                game.isMuted(true);
                menus.getMuteButtonOff().draw();
                menus.removeMuteButtonOn();
                TinySound.setGlobalVolume(0);
            } else {
                game.isMuted(false);
                menus.getMuteButtonOff().delete();
                menus.addMuteButtonOn();
                TinySound.setGlobalVolume(0.25);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
