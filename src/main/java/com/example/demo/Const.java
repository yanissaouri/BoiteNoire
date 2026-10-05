package com.example.demo;

public final class Const {

    private static final double HEAVY = 0.05;
    private static final double MEDIUM = 0.25;
    private static final int TOTAL_USER = 5000;

    private Const(){
    }

    public static double getHEAVY() {
        return HEAVY;
    }

    public static double getMEDIUM() {
        return MEDIUM;
    }

    public static int getTotalUser() {
        return TOTAL_USER;
    }
}
