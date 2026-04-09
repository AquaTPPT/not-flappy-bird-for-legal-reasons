package com.codeforall.online;

import com.codeforall.online.playspace.Playspace;
import com.codeforall.online.playspace.Tubes;
import com.codeforall.online.statics.Random;

public class Game {
    private Playspace playSpace = new Playspace();
    private Tubes tubes = new Tubes();

    public void init() {
        playSpace.init();
        tubes.spawnTubes(100, 0);
    }
}
