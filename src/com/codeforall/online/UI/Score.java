package com.codeforall.online.UI;

import com.codeforall.online.Main;
import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Text;

import java.io.*;

public class Score {

    Text textScore, textHighScore;
    private int score, highScore;
    private boolean isGrown;
    File file = new File(Main.PREFIX + "assets/score.txt");
    byte[] buffer = new byte[1024];

    public void initScore() {

        Text text = new Text(70, 50, "SCORE:");
        text.setColor(Color.WHITE);
        text.draw();
        text.grow(50, 50);

        textScore = new Text(185, 50, Integer.toString(score));
        textScore.setColor(Color.ORANGE);
        textScore.draw();
        textScore.grow(12, 50);
    }

    public void initHighScore() {
        try {
            highScoreFileChecker();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Text text = new Text(116, 125, "HIGH SCORE:");
        text.setColor(Color.WHITE);
        text.draw();
        text.grow(100, 50);

        textHighScore = new Text(280, 125, Integer.toString(highScore));
        textHighScore.setColor(Color.ORANGE);
        textHighScore.draw();
        textHighScore.grow(12, 50);
    }


    public void increment(){

        score++;
        textScore.setText(Integer.toString(score));

        if (score == 10 && !isGrown) {
            textScore.grow(12, 0);
            isGrown = true;
        }

        if (score == 100 && isGrown) {
            textScore.grow(12, 0);
            isGrown = false;
        }
        if (score > highScore) {
            highScore = score;
            textHighScore.setText(Integer.toString(highScore));
        }
        if (highScore == 10) {
            textHighScore.grow(12, 0);
        }
        if (highScore == 100) {
            textScore.grow(12, 0);
        }
    }

    public void restartScore() {
        score = 0;
        textScore.setText(Integer.toString(score));
    }

    public int getScore() {
        return score;
    }

    public void highScoreChecker() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        int bytes = fileInputStream.read(buffer);
        fileInputStream.close();

        String string = new String(buffer, 0, bytes);
        int scoreOnFIle = Integer.parseInt(string);

        if (scoreOnFIle < highScore) {
            FileWriter fileWriter = new FileWriter(file);
            string = Integer.toString(highScore);
            fileWriter.write(string);
            fileWriter.close();
        }
    }

    public void highScoreFileChecker() throws IOException {
        boolean fileExists = file.createNewFile();
        String string;

        if (!fileExists) {
            FileInputStream fileInputStream = new FileInputStream(file);
            int bytes = fileInputStream.read(buffer);
            string = new String(buffer, 0, bytes);
            fileInputStream.close();
        }
        else {
            FileWriter fileWriter = new FileWriter(file);
            string = "0";
            fileWriter.write("0");
            fileWriter.close();
        }
        highScore = Integer.parseInt(string);
    }
}