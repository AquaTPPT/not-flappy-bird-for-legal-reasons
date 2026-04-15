package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.online.Player.MouseInteraction;
import com.codeforall.online.sound.GameSound;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Rectangle;
import com.codeforall.simplegraphics.graphics.Text;
import com.codeforall.simplegraphics.pictures.Picture;
import kuusisto.tinysound.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menus {
    private PauseMenu pauseMenu;
    private MainMenu mainMenu;
    private GameOver gameOver;


    public Menus(Playspace playspace, MouseInteraction mouseInteraction) {
        pauseMenu = new PauseMenu(playspace, playspace.getMouseInteraction());
        mainMenu = new MainMenu(mouseInteraction);
        gameOver = new GameOver();
    }

    public void removePauseMenu() { pauseMenu.removePauseMenu(); }

    public void startPauseMenu(MouseInteraction mouseInteraction) {
        pauseMenu.init();
    }

    public void startMainMenu() {
        mainMenu.startMenu();
    }

    public void startGameOverScreen() {
        gameOver.startGameOverScreen();
    }

    public void closeGameOverScreen() {
        gameOver.closeGameOverScreen();
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

    // Mute button
    public int getMuteButtonX() { return mainMenu.getMuteButtonX(); }
    public int getMuteButtonY() { return mainMenu.getMuteButtonY(); }
    public int getMuteButtonWidth() { return mainMenu.getMuteButtonWidth(); }
    public int getMuteButtonHeight() { return mainMenu.getMuteButtonHeight(); }

    public Picture getMuteButtonOff() { return mainMenu.getMuteButtonOff(); }
    public void removeMuteButtonOn() { mainMenu.removeMuteButtonOn(); }
    public void addMuteButtonOn() { mainMenu.addMuteButtonOn(); }

    public void removeStartMenu() {
        mainMenu.removeMenuButtons();
    }

    private class MainMenu {
        private Picture muteButtonOn, muteButtonOff, gameLogo, startButton;
        private MouseInteraction mouseInteraction;
        private boolean hasInstanciated = false, hasInstanciated1 = false;
        private Timer startMenu = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!hasInstanciated) {
                    hasInstanciated = true;
                    gameLogo.draw();
                }
            }
        });
        private Timer startMenuButtons = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!hasInstanciated1) {
                    hasInstanciated1 = true;
                    startButton.draw();
                }
            }
        });

        public MainMenu(MouseInteraction mouseInteraction) {
            this.mouseInteraction = mouseInteraction;
            startButton = new Picture(275, 600, Main.PREFIX + "Play_Button.png");
            gameLogo = new Picture(140, 150, Main.PREFIX + "Logo_1.png");
            muteButtonOn = new Picture(680, 0, Main.PREFIX + "sound_on.png");
            muteButtonOff = new Picture(680,0,Main.PREFIX + "sound_off.png");
            TinySound.init();
            TinySound.setGlobalVolume(0.25);
        }

        public void startMenu() {
            startMenu.start();
            startMenuButtons.start();
            mouseInteraction.initializeMouse();
            muteButtonOn.draw();
        }

        public int getButtonX() { return startButton.getX(); }
        public int getButtonY() { return startButton.getY(); }
        public int getButtonWidth() { return startButton.getWidth(); }
        public int getButtonHeight() { return startButton.getHeight(); }

        public int getMuteButtonX() { return muteButtonOn.getX(); }
        public int getMuteButtonY() { return muteButtonOn.getY(); }
        public int getMuteButtonWidth() { return muteButtonOn.getWidth(); }
        public int getMuteButtonHeight() { return muteButtonOn.getHeight(); }

        public Picture getMuteButtonOff() { return muteButtonOff; }

        public void removeMuteButtonOn() {
            muteButtonOn.delete();
        }
        public void addMuteButtonOn() { muteButtonOn.draw(); }

        public void removeMenuButtons() {
            startMenu.stop();
            gameLogo.delete();
            startMenuButtons.stop();
            startButton.delete();
            muteButtonOn.delete();
            muteButtonOff.delete();
        }
    }

    private class PauseMenu {
        private Picture picture;
        private Playspace playspace;
        private Text text; // Change to Picture later
        private Rectangle button; // change this to picture later!!
        private MouseInteraction mouseInteraction;

        public PauseMenu(Playspace playspace, MouseInteraction mouseInteraction) {
            this.mouseInteraction = mouseInteraction;
            this.playspace = playspace;
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

    private class GameOver {
        private Rectangle playAgain;
        private Rectangle gameOver;

        private Timer gameOverScreen = new Timer(1700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameOver.setColor(Color.ORANGE);
                gameOver.fill();
                playAgain.setColor(Color.ORANGE);
                playAgain.fill();
            }
        });
        public GameOver() {
            playAgain = new Rectangle(275, 600, 200, 104);
            gameOver = new Rectangle(110, 150, 300, 200);
        }

        public void startGameOverScreen() {
            gameOverScreen.start();
        }

        public void closeGameOverScreen() {
            gameOverScreen.stop();
            playAgain.delete();
            gameOver.delete();
        }
    }

}