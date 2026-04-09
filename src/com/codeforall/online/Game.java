package com.codeforall.online;

import com.codeforall.online.Player.Player;
import com.codeforall.online.playspace.Playspace;
import com.codeforall.online.playspace.Tubes;
import com.codeforall.online.statics.Random;

public class Game {
    private Playspace playSpace = new Playspace();
    private Tubes tubes = new Tubes();
    private Player player = new Player();

    public void init() {
        playSpace.init();
        tubes.spawnTubes(800, Random.randomInt(-1000, -150));
        player.init();
    }
}
