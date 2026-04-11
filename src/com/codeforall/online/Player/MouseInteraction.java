package com.codeforall.online.Player;

import com.codeforall.online.Game;
import com.codeforall.online.playspace.Menus;
import com.codeforall.simplegraphics.mouse.Mouse;
import com.codeforall.simplegraphics.mouse.MouseEvent;
import com.codeforall.simplegraphics.mouse.MouseEventType;
import com.codeforall.simplegraphics.mouse.MouseHandler;

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
        if (mouseEvent.getY() >= game.getMenus().getButtonY() &&
                mouseEvent.getY() <= game.getMenus().getButtonHeight() + game.getMenus().getButtonY() &&
                mouseEvent.getX() >= game.getMenus().getButtonX() &&
                mouseEvent.getX() <= game.getMenus().getButtonWidth() + game.getMenus().getButtonX()) {
            game.getMenus().removePauseMenu();
            removeMouseListener();

            game.resumeGame();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
