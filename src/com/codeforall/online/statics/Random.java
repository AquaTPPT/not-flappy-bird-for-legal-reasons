package com.codeforall.online.statics;

public class Random {

    public static int randomInt(int startingValue, int maxValue) {
        return (int) Math.floor(startingValue + (Math.random() * (maxValue - startingValue)));
    }
}
