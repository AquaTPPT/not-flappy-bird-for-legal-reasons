package com.codeforall.online.Player;

import com.codeforall.online.Game;
import com.codeforall.online.playspace.Menus;
import com.codeforall.simplegraphics.mouse.Mouse;
import com.codeforall.simplegraphics.mouse.MouseEvent;
import com.codeforall.simplegraphics.mouse.MouseEventType;
import com.codeforall.simplegraphics.mouse.MouseHandler;
import kuusisto.tinysound.*;

public class MouseInteraction implements MouseHandler {    private Mouse mouse;
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

    public void removeMouseListener() {
        mouse.removeEventListener(MouseEventType.MOUSE_CLICKED);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (game.isPlaying() == false && mouseEvent.getY() >= game.getMenus().getButtonY() &&
                mouseEvent.getY() <= game.getMenus().getButtonHeight() + game.getMenus().getButtonY() &&
                mouseEvent.getX() >= game.getMenus().getButtonX() &&
                mouseEvent.getX() <= game.getMenus().getButtonWidth() + game.getMenus().getButtonX()) {
            game.getMenus().removePauseMenu();
            removeMouseListener();
            game.isPaused(false);
            game.resumeGame();
        }
        if (mouseEvent.getY() >= game.getMenus().getButton1Y() &&
                mouseEvent.getY() <= game.getMenus().getButton1Height() + game.getMenus().getButton1Y() &&
                mouseEvent.getX() >= game.getMenus().getButton1X() &&
                mouseEvent.getX() <= game.getMenus().getButton1Width() + game.getMenus().getButton1X()) {
            game.getMenus().removeStartMenu();
            game.startGame();
        }
        if (mouseEvent.getY() >= game.getMenus().getMuteButtonY() &&
                mouseEvent.getY() <= game.getMenus().getMuteButtonHeight() + game.getMenus().getMuteButtonY() &&
                mouseEvent.getX() >= game.getMenus().getMuteButtonX() &&
                mouseEvent.getX() <= game.getMenus().getMuteButtonWidth() + game.getMenus().getMuteButtonX()) {
            if (game.isMuted()) {
                game.isMuted(false);
                TinySound.setGlobalVolume(0);
            }
            else {
                game.isMuted(true);
                TinySound.setGlobalVolume(0.25);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
