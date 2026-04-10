package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.online.Player.KeyboardInteraction;
import com.codeforall.online.Player.MouseInteraction;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.graphics.Text;
import com.codeforall.simplegraphics.pictures.Picture;

public class Menus {
    private PauseMenu pauseMenu;
    private MainMenu mainMenu;
    private Playspace playspace;
    private MouseInteraction mouseInteraction;

    public Menus(Playspace playspace) {
        this.playspace = playspace;
    }

    public void removePauseMenu() { pauseMenu.removePauseMenu(); }

    public void startPauseMenu(MouseInteraction mouseInteraction) {
        this.mouseInteraction = mouseInteraction;
        pauseMenu = new PauseMenu(playspace, mouseInteraction);
        pauseMenu.init();
    }

    public void startMainMenu() { mainMenu = new MainMenu(); }

    public int getButtonX() { return pauseMenu.getButtonX(); }
    public int getButtonY() { return pauseMenu.getButtonY(); }
    public int getButtonWidth() { return pauseMenu.getButtonWidth(); }
    public int getButtonHeight() { return pauseMenu.getButtonHeight(); }


    private class PauseMenu {
        private Picture picture;
        private Playspace playspace;
        private Text text;
        private Text buttonText;
        private Rectangle button; // change this to picture later!!
        private KeyboardInteraction keyboardInteraction;
        private MouseInteraction mouseInteraction;

        public PauseMenu(Playspace playspace, MouseInteraction mouseInteraction) {
            this.mouseInteraction = mouseInteraction;
            this.playspace = playspace;
            mouseInteraction.initializeMouse();
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

        private void createButton() {
            button = new Rectangle(260, 600, 200,100);
            button.draw();
            button.setColor(Color.ORANGE);
            button.fill();
        }

        public int getButtonX() { return button.getX(); }
        public int getButtonY() { return button.getY(); }
        public int getButtonWidth() { return button.getWidth(); }
        public int getButtonHeight() { return button.getHeight(); }
    }

    private class MainMenu {


    }
}