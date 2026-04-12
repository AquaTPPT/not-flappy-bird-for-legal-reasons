package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.online.Player.KeyboardInteraction;
import com.codeforall.online.Player.MouseInteraction;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.graphics.Text;
import com.codeforall.simplegraphics.pictures.Picture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public void startMainMenu(MouseInteraction mouseInteraction) {
        mainMenu = new MainMenu(mouseInteraction);
        mainMenu.startMenu();
    }

    // pause menu button
    public int getButtonX() { return pauseMenu.getButtonX(); }
    public int getButtonY() { return pauseMenu.getButtonY(); }
    public int getButtonWidth() { return pauseMenu.getButtonWidth(); }
    public int getButtonHeight() { return pauseMenu.getButtonHeight(); }

    // Main menu button
    public int getButton1X() { return mainMenu.getButtonX(); }
    public int getButton1Y() { return mainMenu.getButtonY(); }
    public int getButton1Width() { return mainMenu.getButtonWidth(); }
    public int getButton1Height() { return mainMenu.getButtonHeight(); }

    public void removeStartMenu() {
        mainMenu.removeMenuButton();
        mainMenu.removeMenuLogo();
    }

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
        private Rectangle gameLogo;
        private Rectangle startButton;
        private MouseInteraction mouseInteraction;
        private Timer startMenu = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameLogo = new Rectangle(110, 150, 300, 200);
                gameLogo.setColor(Color.ORANGE);
                gameLogo.fill();
            }
        });
        private Timer startMenuButton = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                startButton = new Rectangle(265, 800, 200, 100);
                startButton.setColor(Color.ORANGE);
                startButton.fill();
            }
        });

        public MainMenu(MouseInteraction mouseInteraction) {
            this.mouseInteraction = mouseInteraction;
        }
        public void startMenu() {
            startMenu.start();
            startMenuButton.start();
            mouseInteraction.initializeMouse();
            mouseInteraction.addMouseListener();
        }

        public int getButtonX() { return startButton.getX(); }
        public int getButtonY() { return startButton.getY(); }
        public int getButtonWidth() { return startButton.getWidth(); }
        public int getButtonHeight() { return startButton.getHeight(); }

        public void removeMenuButton() {
           // startMenu.stop();
           // gameLogo.delete();
            startMenuButton.stop();
            startButton.delete();
            mouseInteraction.removeMouseListener();
        }

        public void removeMenuLogo() {
            startMenu.stop();
        }
    }
}