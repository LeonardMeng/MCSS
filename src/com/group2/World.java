package com.group2;

import java.text.DecimalFormat;
import java.util.Random;

public class World {


  private Patch[][] world;
  private Patch[][] copyWorld;
  private Double[][] temp;

  private int startWhitePercent;
  private int row;
  private int col;
  private int startBlackPercent;

  private double solarLuminosity = 0.8;
  private double globalTemperature;
  private double seedThreshold;
  private double albedoOfSurface = 0.4;
  private double absorbLuminosity;

  public World(int row, int col, double solarLuminosity, int startWhitePercent,
      int startBlackPercent) {
    this.world = new Patch[row][col];
    this.copyWorld = new Patch[row][col];
    this.temp = new Double[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        world[i][j] = new Patch();
        copyWorld[i][j] = new Patch();
      }
    }
    this.row = row;
    this.col = col;
    this.startWhitePercent = startWhitePercent;
    this.startBlackPercent = startBlackPercent;
    this.startBlackRandomly();
    this.startWhiteRandomly();
    this.setDaisyAge();
    this.meanTemperature();

  }

  public void patchCalTemperature() {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        world[i][j].calTemperature(albedoOfSurface, solarLuminosity);
      }
    }

  }

  public void diffuseTemperature() {
    copyTheWorld();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        world[i][j].setTemperature(world[i][j].getTemperature() / 2);
        temp[i][j] = world[i][j].getTemperature() / 2;
      }
    }
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        double sumTmp = 0.0;
        if (i == 0 && j == 0) {
          sumTmp += temp[0][1] / 8;
          sumTmp += temp[1][1] / 8;
          sumTmp += temp[1][0] / 8;
        } else if (i == row - 1 && j == col - 1) {
          sumTmp += temp[row - 1][col - 2] / 8;
          sumTmp += temp[row - 2][col - 2] / 8;
          sumTmp += temp[row - 2][col - 1] / 8;
        } else if (i == row - 1 && j == 0) {
          sumTmp += temp[row - 1][1] / 8;
          sumTmp += temp[row - 2][1] / 8;
          sumTmp += temp[row - 2][0] / 8;
        } else if (i == 0 && j == col - 1) {
          sumTmp += temp[0][col - 2] / 8;
          sumTmp += temp[1][col - 2] / 8;
          sumTmp += temp[1][col - 1] / 8;
        } else if (i == 0) {
          sumTmp += temp[0][j - 1] / 8;
          sumTmp += temp[0][j + 1] / 8;
          sumTmp += temp[1][j - 1] / 8;
          sumTmp += temp[1][j + 1] / 8;
          sumTmp += temp[1][j] / 8;
        } else if (i == row - 1) {
          sumTmp += temp[i][j - 1] / 8;
          sumTmp += temp[i][j + 1] / 8;
          sumTmp += temp[i - 1][j - 1] / 8;
          sumTmp += temp[i - 1][j + 1] / 8;
          sumTmp += temp[i - 1][j] / 8;
        } else if (j == 0) {
          sumTmp += temp[i - 1][j] / 8;
          sumTmp += temp[i + 1][j] / 8;
          sumTmp += temp[i - 1][j + 1] / 8;
          sumTmp += temp[i][j + 1] / 8;
          sumTmp += temp[i + 1][j + 1] / 8;
        } else if (j == col - 1) {
          sumTmp += temp[i - 1][j] / 8;
          sumTmp += temp[i + 1][j] / 8;
          sumTmp += temp[i - 1][j - 1] / 8;
          sumTmp += temp[i][j - 1] / 8;
          sumTmp += temp[i + 1][j - 1] / 8;
        } else {
          sumTmp += temp[i - 1][j - 1] / 8;
          sumTmp += temp[i - 1][j] / 8;
          sumTmp += temp[i - 1][j + 1] / 8;
          sumTmp += temp[i][j - 1] / 8;
          sumTmp += temp[i][j + 1] / 8;
          sumTmp += temp[i + 1][j - 1] / 8;
          sumTmp += temp[i + 1][j] / 8;
          sumTmp += temp[i + 1][j + 1] / 8;
        }
        world[i][j].setTemperature(temp[i][j] + sumTmp);
      }
    }
  }

  public void meanTemperature() {
    double total = 0.0;
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        Patch patch = world[i][j];
        total += patch.getTemperature();
      }
    }
    globalTemperature = total / (row * col);
  }

  public void startWhiteRandomly() {
    int num = (int) (((double) startWhitePercent / 100.0) * ((double) row * (double) col) + 0.5);
    Random random = new Random();
    while (num > 0) {
      int i = random.nextInt(row);
      int j = random.nextInt(col);
      if (world[i][j].getDaisy() == null) {
        world[i][j].setDaisy(new Daisy(DaisyType.WHITE));
        num--;
      }
    }
  }

  public void startBlackRandomly() {
    int num = (int) (((double) startBlackPercent / 100.0) * ((double) row * (double) col) + 0.5);
    Random random = new Random();
    while (num > 0) {
      int i = random.nextInt(row);
      int j = random.nextInt(col);
      if (world[i][j].getDaisy() == null) {
        world[i][j].setDaisy(new Daisy(DaisyType.BLACK));
        num--;
      }
    }
  }

  public void setDaisyAge() {
    Random random = new Random();
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (world[i][j].getDaisy() != null) {
          world[i][j].getDaisy().setAge(random.nextInt(Parameters.MAX_AGE));
        }
      }
    }
  }

  public void printWorld() {
    DecimalFormat df = new DecimalFormat("######0.00");
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        System.out.print("|");
        if (world[i][j].getDaisy() == null) {
          System.out
              .print(String.format("%16s", df.format(world[i][j].getTemperature()) + "          "));
        } else {
          System.out.print(String.format("%16s", df.format(world[i][j].getTemperature()) +
              String.format("%3s", world[i][j].getDaisy().getAge()) + "  " +
              world[i][j].getDaisy().getDaisyType().toString()));
        }
        if (j == col - 1) {
          System.out.print("|");
        }
      }
      System.out.println();
    }
  }


  public Patch[][] getWorld() {
    return world;
  }

  public void setWorld(Patch[][] world) {
    this.world = world;
  }

  public double getSolarLuminosity() {
    return solarLuminosity;
  }

  public void setSolarLuminosity(double solarLuminosity) {
    this.solarLuminosity = solarLuminosity;
  }

  public double getGlobalTemperature() {
    return globalTemperature;
  }

  public void setGlobalTemperature(double globalTemperature) {
    this.globalTemperature = globalTemperature;
  }

  public int getRow() {
    return row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  public int getCol() {
    return col;
  }

  public void setCol(int col) {
    this.col = col;
  }

  public int getStartBlackPercent() {
    return startBlackPercent;
  }

  public void setStartBlackPercent(int startBlackPercent) {
    this.startBlackPercent = startBlackPercent;
  }

  public int getStartWhitePercent() {
    return startWhitePercent;
  }

  public void setStartWhitePercent(int startWhitePercent) {
    this.startWhitePercent = startWhitePercent;
  }

  //update the age of each daisy, when the age is 25, then it dies immediately
  public void updateDaisyAge() {
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (copyWorld[i][j].getDaisy() != null) {
          int age = world[i][j].getDaisy().getAge() + 1;
          world[i][j].getDaisy().setAge(age);
          if (world[i][j].getDaisy().getAge() == 25) {
            world[i][j].goDieDaisy();
          }
        }
      }
    }
  }

  public void bornNewDaisy() {

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        double sumTmp = 0.0;
        double seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
            (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature()))
            - 0.6443);
        double randomdouble = Math.random();
        if (randomdouble < seedThreshold && world[i][j].getDaisy() != null) {
          produce(i, j,  world[i][j].getDaisy());
        }
      }
    }
  }
  public void produce(int i, int j, Daisy daisy){
    double sumTmp;
    if (i == 0 && j == 0) {
      if(world[0][1].getDaisy() == null) world[0][1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[1][1].getDaisy() == null) world[1][1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[1][0].getDaisy() == null) world[1][0].setDaisy(new Daisy(0, daisy.getDaisyType()));
    } else if (i == row - 1 && j == col - 1) {

      if(world[row - 1][col - 2].getDaisy() == null) world[row - 1][col - 2].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[row - 2][col - 2].getDaisy() == null) world[row - 2][col - 2].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[row - 2][col - 1].getDaisy() == null) world[row - 2][col - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
    } else if (i == row - 1 && j == 0) {
      if(world[row - 1][1].getDaisy() == null) world[row - 1][1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[row - 2][1].getDaisy() == null) world[row - 2][1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[row - 2][0].getDaisy() == null) world[row - 2][0].setDaisy(new Daisy(0, daisy.getDaisyType()));
    } else if (i == 0 && j == col - 1) {

      if(world[0][col - 2].getDaisy() == null) world[0][col - 2].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[1][col - 2].getDaisy() == null) world[1][col - 2].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[1][col - 1].getDaisy() == null) world[1][col - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
    } else if (i == 0) {

      if(world[0][j - 1].getDaisy() == null) world[0][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[0][j + 1].getDaisy() == null) world[0][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[1][j - 1].getDaisy() == null) world[1][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[1][j + 1].getDaisy() == null) world[1][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[1][j].getDaisy() == null) world[1][j].setDaisy(new Daisy(0, daisy.getDaisyType()));
    } else if (i == row - 1) {

      if(world[i][j - 1].getDaisy() == null) world[i][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i][j + 1].getDaisy() == null) world[i][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i - 1][j - 1].getDaisy() == null) world[i - 1][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i - 1][j + 1].getDaisy() == null) world[i - 1][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i - 1][j].getDaisy() == null) world[i - 1][j].setDaisy(new Daisy(0, daisy.getDaisyType()));
    } else if (j == 0) {


      if(world[i - 1][j].getDaisy() == null) world[i - 1][j].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i + 1][j].getDaisy() == null) world[i + 1][j].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i - 1][j + 1].getDaisy() == null) world[i - 1][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i][j + 1].getDaisy() == null) world[i][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i + 1][j + 1].getDaisy() == null) world[i + 1][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));

    } else if (j == col - 1) {

      if(world[i - 1][j].getDaisy() == null) world[i - 1][j].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i + 1][j].getDaisy() == null) world[i + 1][j].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i - 1][j - 1].getDaisy() == null) world[i - 1][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i][j - 1].getDaisy() == null) world[i][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i + 1][j - 1].getDaisy() == null) world[i + 1][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
    } else {

      if(world[i - 1][j - 1].getDaisy() == null) world[i - 1][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i - 1][j].getDaisy() == null) world[i - 1][j].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i - 1][j + 1].getDaisy() == null) world[i - 1][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i][j - 1].getDaisy() == null) world[i][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i][j + 1].getDaisy() == null) world[i][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i + 1][j - 1].getDaisy() == null) world[i + 1][j - 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i + 1][j].getDaisy() == null) world[i + 1][j].setDaisy(new Daisy(0, daisy.getDaisyType()));
      if(world[i + 1][j + 1].getDaisy() == null) world[i + 1][j + 1].setDaisy(new Daisy(0, daisy.getDaisyType()));
    }
  }


    public void updateGlobalTemperature () {
      double sum = 0;

      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          sum += world[i][j].getTemperature();

        }
        globalTemperature = sum / (row * col);
      }
    }

    public void copyTheWorld () {
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
//        Patch patch = world[i][j];
//        copyWorld[i][j] = patch;
          copyWorld[i][j].setDaisy(world[i][j].getDaisy());
          if (world[i][j].getDaisy() == null) {
            copyWorld[i][j].setDaisy(world[i][j].getDaisy());
          } else {
            copyWorld[i][j].getDaisy().setAge(world[i][j].getDaisy().getAge());
            copyWorld[i][j].getDaisy().setDaisyType(world[i][j].getDaisy().getDaisyType());
          }
          copyWorld[i][j].setTemperature(world[i][j].getTemperature());
          copyWorld[i][j].setColor(world[i][j].getColor());

        }
      }
    }

    public void justifTem () {
      for (int i = 0; i < row; i++) {
        for (int j = 0; j < col; j++) {
          world[i][j].setTemperature(20);
        }
      }
    }

    public void updateWorld () {
      copyTheWorld();

      patchCalTemperature();
      diffuseTemperature();
      updateGlobalTemperature();
      updateDaisyAge();
      //justifTem();
      bornNewDaisy();

//    System.out.println(globalTemperature);

//    System.out.println(world[0][0].getTemperature());
//    System.out.println(seedThreshold);
      double randomdouble = Math.random();
//    System.out.println(randomdouble);
    }
  }
