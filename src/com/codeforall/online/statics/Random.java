package com.codeforall.online.statics;

public class Random {
    public static int randomInt(int val) {
        return (int) Math.floor(Math.random() * val);
    }

    public static int randomInt(int startingValue, int maxValue) {
        return (int) Math.floor(startingValue + (Math.random() * (maxValue - startingValue)));
    }
}
