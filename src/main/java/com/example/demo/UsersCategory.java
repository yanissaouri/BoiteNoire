package com.example.demo;

public enum UsersCategory {
    HEAVY(10), MEDIUM(3), LIGHT(1);

    private final int weight;

    UsersCategory(int weight){
        this.weight = weight;
    }

        public int getWeight(){
            return weight;
    }
}
