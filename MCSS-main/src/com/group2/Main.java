package com.group2;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        World world = new World(10, 10, 0.8, 20, 20);
        world.printWorld();
        for(int i = 1; i < 10; i++){
           world.updateWorld();
        }
        System.out.println();
        world.printWorld();
    }




}
