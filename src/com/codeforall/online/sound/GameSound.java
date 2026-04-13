package com.codeforall.online.sound;

import com.codeforall.online.Main;
import kuusisto.tinysound.*;

import java.io.File;

public class GameSound {

    private Music bgm;
    private kuusisto.tinysound.Sound death, scoreSound, badSound;

    public GameSound() {
        TinySound.init();
        TinySound.setGlobalVolume(0.25);
        this.bgm = TinySound.loadMusic(new File(Main.PREFIX + "game_music.wav"));
        this.death = TinySound.loadSound(new File(Main.PREFIX + "death.wav"));
        this.scoreSound = TinySound.loadSound(new File(Main.PREFIX + "score.wav"));
        this.badSound = TinySound.loadSound(new File(Main.PREFIX + "Spongebob_Disgusting_sfx.wav"));
    }

    public void playBgm() {
        bgm.play(true);
    }

    public void pauseBgm () {
        bgm.pause();
    }

    public void resumeBgm() {
        bgm.resume();
    }

    public void stopBgm() {
        bgm.stop();
    }

    public void playDeath() {
        death.play();
    }

    public void stopDeath() {
        death.stop();
    }

    public void playScore() {
        scoreSound.play();
    }

    public void stopScore() {
        scoreSound.stop();
    }

    public void playBadSound() {
        badSound.play();
    }





}
