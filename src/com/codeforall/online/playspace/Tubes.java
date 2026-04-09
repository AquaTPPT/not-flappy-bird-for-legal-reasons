package com.codeforall.online.playspace;

import com.codeforall.simplegraphics.graphics.Rectangle;

public class Tubes {
    int baseSpawnModifier = 750;
    int lowerTubeSpawnModifier = 50;

    public void spawnTubes(int startingX, int startingY) {
        UpperTube upperTube = new UpperTube(startingX,startingY - (baseSpawnModifier));
        LowerTube lowerTube = new LowerTube(startingX, startingY + 1000);
    }

    class UpperTube {
        private Rectangle hitbox;

        public UpperTube(int startingX, int startingY) {
            hitbox = new Rectangle(startingX,startingY, 100,800);
            hitbox.draw();
        }
    }

    class LowerTube {
        private Rectangle hitbox;

        public LowerTube(int startingX, int startingY) {
            hitbox = new Rectangle(startingX, startingY, 100,800);
            hitbox.draw();
        }

    }
}
