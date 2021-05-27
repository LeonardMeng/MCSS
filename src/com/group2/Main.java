package com.group2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


  public static void main(String[] args) throws IOException {
    Scanner input = new Scanner(System.in);

    // Use default setting
    int setting = 0;
    while (true) {
      System.out.println("Please select setting:");
      System.out.println("1. default setting");
      System.out.println("2. custom seeting");
      System.out.println("3. run extension model");
      setting = input.nextInt();
      if (setting > 0 && setting < 4) {
        break;
      }
      ;
      System.out.println("Invalid parameter, please try again");
    }
    int generation = 100;
    while (true) {
      System.out.println("Please input generation:");
      generation = input.nextInt();
      if (generation > 0 && generation < 1000) {
        break;
      }
      ;
      System.out.println("Invalid parameter, please try again");
    }

    double solarLuminosity = 1.0;
    int scenario = 1;
    if (setting != 1) {
      while (true) {
        System.out.println("Please select scenario:");
        System.out.println("1. ramp-up-ramp-down");
        System.out.println("2. low solar luminosity");
        System.out.println("3. our solar luminosity");
        System.out.println("4. high solar luminosity");
        scenario = input.nextInt();
        if (scenario > 0 && scenario < 5) {
          break;
        }
      }
      switch (scenario) {
        case 1:
          solarLuminosity = 0.8;
          break;
        case 2:
          solarLuminosity = 0.6;
          break;
        case 3:
          solarLuminosity = 1.0;
          break;
        default:
          solarLuminosity = 1.4;
          break;
      }

    }

    if (setting == 1) {
      World world = new World(29, 29, 1.0, 20, 20);
      simWorld(world, 3, generation);
    } else if (setting == 2) {
      int startBlackPercent = 20;
      int startWhitePercent = 20;

      while (true) {
        System.out.println("Please set start white daisy percent (0-50):");
        startWhitePercent = input.nextInt();
        if (0 <= startWhitePercent && startWhitePercent <= 50) {
          break;
        }
        System.out.println("Invalid parameter, please try again");
      }

      while (true) {
        System.out.println("Please set start black daisy percent (0-50):");
        startBlackPercent = input.nextInt();
        if (0 <= startBlackPercent && startBlackPercent <= 50) {
          break;
        }
        System.out.println("Invalid parameter, please try again");
      }

      World world = new World(29, 29, solarLuminosity, startWhitePercent, startBlackPercent);

      simWorld(world, scenario, generation);
    } else {

      double crustalChange = 0.0;
      double explosion = 0.0;
      double extinct = 0.0;
      while (true) {
        System.out.println("Please set the probability of species outbreak: "
            + ":");
        explosion = input.nextDouble();
        if (0 <= explosion && explosion <= 1) {
          break;
        }
        System.out.println("Invalid parameter, please try again");
      }

      while (true) {
        System.out.println("Please set the probability of species mass extinction:");
        extinct = input.nextDouble();
        if (0 <= extinct && extinct <= 50) {
          break;
        }
        System.out.println("Invalid parameter, please try again");
      }

      while (true) {
        System.out.println("Please set the probability of crustal change:");
        crustalChange = input.nextDouble();
        if (0 <= crustalChange && crustalChange <= 1) {
          break;
        }
        System.out.println("Invalid parameter, please try again");
      }

      double iceAge= 0.0;
      while (true) {
        System.out.println("Please set the probability of ice age:");
        iceAge = input.nextDouble();
        if (0 <= iceAge && iceAge <= 1) {
          break;
        }
        System.out.println("Invalid parameter, please try again");
      }
      World world = new World(29, 29, solarLuminosity, 20, 20);
      extensionWorld(world, scenario, explosion, extinct, generation, crustalChange, iceAge, solarLuminosity);
    }


  }

  public static void simWorld(World world, int scenario, int generation) {
    DecimalFormat df = new DecimalFormat("######0.0000");
    int ticks = 0;
    ArrayList<Double> temperature = new ArrayList<>();
    ArrayList<Integer> whitePopulation = new ArrayList<>();
    ArrayList<Integer> blackPopulation = new ArrayList<>();
    for (int i = 0; i < generation; i++) {
      if (scenario == 1) {
        if (ticks > 200 && ticks <= 400) {
          world.setSolarLuminosity(
              Double.parseDouble(df.format(world.getSolarLuminosity() + 0.005)));

        } else if (ticks > 600 && ticks <= 850) {
          world.setSolarLuminosity(
              Double.parseDouble(df.format(world.getSolarLuminosity() - 0.0025)));
        }
      }
      temperature.add(world.getGlobalTemperature());
      whitePopulation.add(world.calWhitePopulation());
      blackPopulation.add(world.calBlackPopulation());

      world.updateWorld();
      world.printWorld();

      ticks++;

    }
    System.out.println(temperature);
    System.out.println(whitePopulation);
    System.out.println(blackPopulation);
    save2CSV(whitePopulation, blackPopulation, temperature);
  }

  public static void extensionWorld(World world, int scenario, double explosion, double extinct,
      int generation, double crustalChange, double iceAge, double solarLuminosity) {
    DecimalFormat df = new DecimalFormat("######0.0000");
    int ticks = 0;
    ArrayList<Double> temperature = new ArrayList<>();
    ArrayList<Integer> whitePopulation = new ArrayList<>();
    ArrayList<Integer> blackPopulation = new ArrayList<>();
    for (int i = 0; i < generation; i++) {
      if (scenario == 1) {
        if (ticks > 200 && ticks <= 400) {
          world.setSolarLuminosity(
              Double.parseDouble(df.format(world.getSolarLuminosity() + 0.005)));

        } else if (ticks > 600 && ticks <= 850) {
          world.setSolarLuminosity(
              Double.parseDouble(df.format(world.getSolarLuminosity() - 0.0025)));
        }
      }
      world.updateWorld();
      world.printWorld();

      double randomdouble = Math.random();
      if (randomdouble < explosion) {
        world.setSolarLuminosity(solarLuminosity);
        world.explosion(explosion);
      }
      randomdouble = Math.random();
      if (randomdouble < extinct) {

        world.extinct(extinct);
      }
      randomdouble = Math.random();
      if (randomdouble < crustalChange) {
        world.crustalChange(randomdouble);
      }
      randomdouble = Math.random();
      if (randomdouble < iceAge) {
        double currSolarLuminosity = world.getSolarLuminosity();
        world.setSolarLuminosity(currSolarLuminosity > 0.1 ? currSolarLuminosity - 0.1 : 0.1);
      }
      // World recover from ice age
      randomdouble = Math.random();
      if(randomdouble > iceAge){
        world.setSolarLuminosity(solarLuminosity);
      }

      temperature.add(world.getGlobalTemperature());
      whitePopulation.add(world.calWhitePopulation());
      blackPopulation.add(world.calBlackPopulation());

      ticks++;

    }
    System.out.println(temperature);
    System.out.println(whitePopulation);
    System.out.println(blackPopulation);
    save2CSV(whitePopulation, blackPopulation, temperature);

  }

  public static void save2CSV(List<Integer> whitePopulation, List<Integer> blackPopulation,
      List<Double> temperature) {

    String header = "Generation, White population, Black population, Globale Temperature";
    File outFile = new File("out.csv");
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(outFile));

      writer.write(header);
      writer.newLine();

      for (int i = 0; i < temperature.size(); i++) {
        StringBuilder sb = new StringBuilder();
        sb.append(i).append(",").append(whitePopulation.get(i)).append(",")
            .append(blackPopulation.get(i)).append(",").append(temperature.get(i));

        writer.write(sb.toString());
        writer.newLine();
      }
      writer.close();
    } catch (IOException ex) {
      System.out.println("Output Error!");
    } finally {

    }
  }


}
