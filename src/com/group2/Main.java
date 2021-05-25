package com.group2;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {


  public static void main(String[] args) throws IOException {
    World world = new World(29, 29, 0.8, 20, 20);
    System.out.println("Please select scenario:");
    System.out.println("1. ramp-up-ramp-down");
    System.out.println("2. low solar luminosity");
    System.out.println("3. our solar luminosity");
    System.out.println("4. high solar luminosity");
    Scanner input = new Scanner(System.in);
    int scenario = input.nextInt();
    switch (scenario) {
      case 1:
        world.setSolarLuminosity(0.8);
        break;
      case 2:
        world.setSolarLuminosity(0.6);
        break;
      case 3:
        world.setSolarLuminosity(1.0);
        break;
      default:
        world.setSolarLuminosity(1.4);
        break;
    }
//    world.printWorld();

    DecimalFormat df = new DecimalFormat("######0.0000");
    int ticks = 0;
    ArrayList<Double> list = new ArrayList<>();
    for (int i = 0; i < 300; i++) {
      if (scenario == 1) {
        if (ticks > 200 && ticks <= 400) {
          world.setSolarLuminosity(
              Double.parseDouble(df.format(world.getSolarLuminosity() + 0.005)));

        } else if (ticks > 600 && ticks <= 850) {
          world.setSolarLuminosity(
              Double.parseDouble(df.format(world.getSolarLuminosity() - 0.0025)));
        }
        ticks++;
      }
//      int n = input.nextInt();
//      if (n == 1) {
        world.updateWorld();
//        world.printWorld();
        list.add(world.getGlobalTemperature());
//      }
      ticks++;

//
//      world.updateWorld();
//      world.printWorld();
    }
    System.out.println(list);
  }


}
