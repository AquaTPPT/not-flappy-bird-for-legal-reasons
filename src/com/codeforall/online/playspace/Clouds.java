package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.simplegraphics.pictures.Picture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clouds {
    private Picture cloud;
    private Picture cloud1;

    private Timer cloudTimer = new Timer(50, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            moveClouds();
            resetCloudPosition();
        }
    });

    public void createClouds() {
        cloud = new Picture(90, 300 , Main.PREFIX + "Cloud_1.png");
        cloud1 = new Picture(530, 400 , Main.PREFIX + "Cloud_3.png");
    }

    public void drawClouds() {
        cloud.draw();
        cloud1.draw();
    }

    public void moveClouds(){
        cloud.translate(-1,0);
        cloud1.translate(-1,0);
    }

    public void resetCloudPosition() {
        if (cloud.getX() < -300) {
            cloud.translate(1020, 0);
        }
        if (cloud1.getX() < -300) {
            cloud1.translate(1020, 0);
        }
    }

    public void startCloudTimer() {
        cloudTimer.start();
    }
}
