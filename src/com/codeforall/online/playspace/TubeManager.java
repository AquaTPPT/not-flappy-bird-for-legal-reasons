package com.codeforall.online.playspace;

import com.codeforall.online.statics.Random;
import java.util.ArrayList;
import java.util.List;

public class TubeManager {
    private List<Tubes> tubesList;

    public TubeManager() {
        this.tubesList = new ArrayList<>();
    }


    public void spawnTubes() {
        tubesList.clear();

        for (int i = 0; i < 3; i++) {
            Tubes t = new Tubes();

            t.spawnTubes(800 + (i * 400), Random.randomInt(-800, -300));
            tubesList.add(t);
        }
    }

    public void restartTubes () {
        for (Tubes t: tubesList) {
            t.restartPosition();
        }
    }

    public void moveAllTubes() {
        for (Tubes t : tubesList) {
            t.moveAll();
        }
    }

    public List<Tubes> getTubes() {
        return tubesList;
    }

}
