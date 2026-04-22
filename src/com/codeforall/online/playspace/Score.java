package com.codeforall.online.playspace;

import com.codeforall.simplegraphics.graphics.Color;
import com.codeforall.simplegraphics.graphics.Text;

public class Score {

    Text textScore;
    private int score;
    private boolean isGrown;

    public void init() {

        Text text = new Text(70, 50, "SCORE:");
        text.setColor(Color.WHITE);
        text.draw();
        text.grow(50, 50);

        textScore = new Text(185, 50, Integer.toString(score));
        textScore.setColor(Color.ORANGE);
        textScore.draw();
        textScore.grow(12, 50);
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

    }

    public void restartScore() {
        score = 0;
        textScore.setText(Integer.toString(score));
    }

    public int getScore() {
        return score;
    }

}
