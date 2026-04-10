package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.graphics.Text;
import com.codeforall.simplegraphics.mouse.*;
import com.codeforall.simplegraphics.pictures.Picture;

public class PauseMenu implements MouseHandler {
    private Picture picture;
    private Playspace playspace;
    private Text text;
    private Text buttonText;
    private Rectangle button; // change this to picture later!!
    private Mouse mouse;

    public PauseMenu(Playspace playspace) {
        this.playspace = playspace;
        mouse = new Mouse(this);
    }

    public void init() {
        picture = new Picture(5,0, Main.PREFIX + "pausemenubackground.png");
        picture.grow(5,0);
        picture.draw();
        text = new Text(360, 200, "Pause");
        text.draw();
        text.grow(175,100);
        createButton();
    }

    public void removePauseMenu() {
        picture.delete();
        text.delete();
        button.delete();
    }

    public void createButton() {
        button = new Rectangle(260, 600, 200,100);
        button.draw();
        button.setColor(Color.ORANGE);
        button.fill();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getY() >= button.getY() &&
                mouseEvent.getY() <= button.getHeight() + button.getY() &&
                mouseEvent.getX() >= button.getX() &&
                mouseEvent.getX() <= button.getWidth() + button.getX())   {
                removePauseMenu();
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
