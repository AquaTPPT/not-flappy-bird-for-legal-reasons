package com.codeforall.online.playspace;

import com.codeforall.simplegraphics.graphics.Rectangle;

public class Tubes {
    int baseSpawnModifier = 800;
    int lowerTubeSpawnModifier = 950;

    public void spawnTubes(int startingX, int startingY) {
        UpperTube upperTube = new UpperTube(startingX, startingY); // min y: -800
        LowerTube lowerTube = new LowerTube(startingX, startingY + 1200); // Max y - 1060
    }

    class UpperTube {
        private Rectangle hitbox;

        public UpperTube(int startingX, int startingY) {
            hitbox = new Rectangle(startingX,startingY, 100,1000);
            hitbox.draw();
        }
    }

    class LowerTube {
        private Rectangle hitbox;

        public LowerTube(int startingX, int startingY) {
            hitbox = new Rectangle(startingX, startingY, 100,1000);
            hitbox.draw();
        }

    }
}
