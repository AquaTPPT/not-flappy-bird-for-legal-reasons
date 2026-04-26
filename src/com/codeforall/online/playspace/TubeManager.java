package com.codeforall.online.playspace;

import com.codeforall.online.statics.Random;

public class TubeManager {
    private Tubes tubes1 = new Tubes();
    private Tubes tubes2 = new Tubes();
    private Tubes tubes3 = new Tubes();

    public TubeManager() {
    }


    public void spawnTubes() {
        tubes1.spawnTubes(800, Random.randomInt(-800, -300));
        tubes2.spawnTubes(1200, Random.randomInt(-800, -300));
        tubes3.spawnTubes(1600, Random.randomInt(-800, -300));
    }

    public void restartTubes () {
        tubes1.restartPosition();
        tubes2.restartPosition();
        tubes3.restartPosition();
    }

    public void constantMoveTubes() {
        tubes1.constantMove();
        tubes2.constantMove();
        tubes3.constantMove();
    }

    public Tubes getTubes1() {
        return tubes1;
    }

    public Tubes getTubes2() {
        return tubes2;
    }

    public Tubes getTubes3() {
        return tubes3;
    }

    public void moveAllTubes() {
        tubes1.moveAll();
        tubes2.moveAll();
        tubes3.moveAll();
    }

}