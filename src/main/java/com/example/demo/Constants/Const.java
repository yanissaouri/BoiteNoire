package com.example.demo.Constants;

public final class Const {

        //Constants for DataGenerator
    private static final double HEAVY = 0.05;
    private static final double MEDIUM = 0.25;
    private static final int TOTAL_USER = 5000;

        //Constants for EventGenerator
    private static final int TOTAL_EVENTS = 100000;

    private static final double API = 0.50;
    private static final double LOGIN = 0.70;
    private static final double NOTIFICATION = 0.85;
    private static final double SUBSCRIPTION = 0.92;

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

    public static int getTotalEvents() {
        return TOTAL_EVENTS;
    }


    public static double getAPI() {
        return API;
    }

    public static double getLOGIN() {
        return LOGIN;
    }

    public static double getNOTIFICATION() {
        return NOTIFICATION;
    }

    public static double getSUBSCRIPTION() {
        return SUBSCRIPTION;
    }
}
