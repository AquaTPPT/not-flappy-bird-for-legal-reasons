package com.codeforall.online.playspace;

import com.codeforall.online.Main;
import com.codeforall.online.interfaces.Movable;
import com.codeforall.simplegraphics.pictures.Picture;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clouds implements Movable {
    private Picture cloud;
    private Picture cloud1;

    public void createClouds() {
        cloud = new Picture(90, 300 , Main.PREFIX + "Cloud_1.png");
        cloud1 = new Picture(530, 400 , Main.PREFIX + "Cloud_3.png");
    }

    public void drawClouds() {
        cloud.draw();
        cloud1.draw();
    }

    @Override
    public void move() {
        cloud.translate(-0.2,0);
        cloud1.translate(-0.2,0);
        if (cloud.getX() < -300) {
            cloud.translate(1020, 0);
        }
        if (cloud1.getX() < -300) {
            cloud1.translate(1020, 0);
        }
    }
}
