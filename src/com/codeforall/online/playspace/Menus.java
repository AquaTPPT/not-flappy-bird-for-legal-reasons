package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.online.Player.MouseInteraction;
import com.codeforall.simplegraphics.pictures.Picture;
import kuusisto.tinysound.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menus {
    private PauseMenu pauseMenu;
    private MainMenu mainMenu;
    private GameOver gameOver;
    private Playspace playspace;


    public Menus(Playspace playspace, MouseInteraction mouseInteraction) {
        pauseMenu = new PauseMenu(playspace, playspace.getMouseInteraction());
        mainMenu = new MainMenu(mouseInteraction);
        gameOver = new GameOver();
        this.playspace = playspace;
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

    public void moveClouds() {
        mainMenu.moveClouds();
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
        private Picture muteButtonOn, muteButtonOff, gameLogo, startButton, cloud1, cloud3;
        private MouseInteraction mouseInteraction;
        private boolean hasInstanciated = false, hasInstanciated1 = false;
        private Timer startMenu = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!hasInstanciated) {
                    hasInstanciated = true;
                    cloud1.draw();
                    cloud3.draw();
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
                    mainMenu.moveClouds();
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

        private Timer cloudTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                moveClouds();
                resetCloudPosition();
            }
        });

        public void startMenu() {
            startMenu.start();
            cloud1 = new Picture(90, 70 , Main.PREFIX + "Cloud_1.png");
            cloud3 = new Picture(530, 150 , Main.PREFIX + "Cloud_3.png");
            startMenuButtons.start();
            cloudTimer.start();
            mouseInteraction.initializeMouse();
            muteButtonOn.draw();

        }

        public void moveClouds(){
            cloud1.translate(-1,0);
            cloud3.translate(-1,0);
        }

        public void resetCloudPosition() {
            if (cloud1.getX() < -200) {
                cloud1.translate(920, 0);
            }
            if (cloud3.getX() < -200) {
                cloud3.translate(920, 0);
            }
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
            cloud1.delete();
            cloud3.delete();
            startMenuButtons.stop();
            startButton.delete();
            muteButtonOn.delete();
            muteButtonOff.delete();
        }
    }

    private class PauseMenu {
        private Picture picture;
        private Playspace playspace;
        private Picture gamePaused;
        private Picture button;
        private MouseInteraction mouseInteraction;

        public PauseMenu(Playspace playspace, MouseInteraction mouseInteraction) {
            this.mouseInteraction = mouseInteraction;
            this.playspace = playspace;
        }

        public void init() {
            picture = new Picture(5,0, Main.PREFIX + "pausemenubackground.png");
            picture.grow(5,0);
            picture.draw();

            gamePaused = new Picture(280, 200, Main.PREFIX + "Game_Paused.png");
            gamePaused.draw();
            gamePaused.grow(175,100);

            createButton();
        }

        public void removePauseMenu() {
            picture.delete();
            gamePaused.delete();
            button.delete();
        }

        private void createButton() {
            button = new Picture(260, 600, Main.PREFIX + "Resume_Game_1.png");
            button.draw();
        }

        public int getButtonX() { return button.getX(); }
        public int getButtonY() { return button.getY(); }
        public int getButtonWidth() { return button.getWidth(); }
        public int getButtonHeight() { return button.getHeight(); }
    }

    private class GameOver {
        private Picture playAgain;
        private Picture gameOver;

        private Timer gameOverScreen = new Timer(1700, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            }
        });
        public GameOver() {
            playAgain = new Picture(260, 600, Main.PREFIX + "Play_Again_1.png");
            gameOver = new Picture(160, 250, Main.PREFIX + "Game_Over.png");
        }

        public void startGameOverScreen() {
            gameOverScreen.start();
            gameOver.draw();
            playAgain.draw();

            if(playspace.getGame().isMuted()){
                removeMuteButtonOn();
                getMuteButtonOff().draw();
            } else {
                getMuteButtonOff().delete();
                addMuteButtonOn();
            }

        }

        public void closeGameOverScreen() {
            gameOverScreen.stop();
            playAgain.delete();
            gameOver.delete();
            removeMuteButtonOn();
            getMuteButtonOff().delete();
        }
    }

}