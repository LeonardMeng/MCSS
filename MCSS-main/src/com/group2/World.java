package com.group2;

import java.util.Random;

public class World {


  private Patch[][] world;
  private Patch[][] copyWorld;

  private int startWhitePercent;
  private int row;
  private int col;
  private int startBlackPercent;

  private double solarLuminosity = 0.8;
  private double globalTemperature;
  private double seedThreshold;
  private double albedoOfSurface = 0.39;
  private double absorbLuminosity;

  public World(int row, int col, double solarLuminosity, int startWhitePercent, int startBlackPercent) {
    this.world = new Patch[row][col];
    this.copyWorld = new Patch[row][col];
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
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

  public void patchCalTemperature(){

    for(int i = 0; i < row; i++){
      for(int j = 0; j< col; j++){
        //lose 50% for distribution
        copyWorld[i][j].setTemperature(copyWorld[i][j].getTemperature()/2);
      }
    }
    double localHeating = 0;
    for(int i = 0; i < row; i++){
      for(int j = 0; j< col; j++){
        //store the sum of current patch's temperature
        double sumTemperature = 0;
        //Calculate the left-top patch's temperature
        if(i == 0 && j == 0){
          //sum surronding distribution
          for(int k = 0; k < i + 1; k ++){
            for(int l = 0; l < j + 1; l++){
              sumTemperature += copyWorld[k][l].getTemperature()/16;
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/16;
          //absorb energy from sun
//          if(copyWorld[i][j].getDaisy() != null){
//            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
//          }
//          else{
//            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
//          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);


        }
        //Calculate the middle-top patches' temperature
        if(i == 0 && j > 0 && j < col){
          //lose 50% for distribution
          sumTemperature -= copyWorld[i][j].getTemperature()/2;
          //sum surronding distribution
          for(int k = 0; k < i + 1; k ++){
            for(int l = j - 1; l < j + 1; l++){
              sumTemperature += copyWorld[k][l].getTemperature()/8;
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/8;
          //absorb energy from sun
          if(copyWorld[i][j].getDaisy() != null){
            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
          }
          else{
            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);


        }

        //Calculate the right-top patch's temperature
        if(i == 0 && j == col){
          //lose 50% for distribution
          sumTemperature -= copyWorld[i][j].getTemperature()/2;
          //sum surronding distribution
          for(int k = 0; k < i + 1; k ++){
            for(int l = j - 1; l < j ; l++){
              sumTemperature += copyWorld[k][l].getTemperature()/8;
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/8;
          //absorb energy from sun
          if(copyWorld[i][j].getDaisy() != null){
            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
          }
          else{
            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);

        }

        //Calculate the left-bottom patch's temperature
        if(i == row && j == 0){
          //lose 50% for distribution
          sumTemperature -= copyWorld[i][j].getTemperature()/2;
          //sum surronding distribution
          for(int k = i - 1; k < i; k ++){
            for(int l = 0; l < j + 1; l++){
              sumTemperature += copyWorld[k][l].getTemperature()/8;
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/8;
          //absorb energy from sun
          if(copyWorld[i][j].getDaisy() != null){
            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
          }
          else{
            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);

        }
        //Calculate the middle-bottom patches' temperature
        if(i == row && j > 0 && j < col){
          //lose 50% for distribution
          sumTemperature -= copyWorld[i][j].getTemperature()/2;
          //sum surronding distribution
          for(int k = row - 1; k < i; k ++){
            for(int l = j - 1; l < j + 1; l++){
              sumTemperature += copyWorld[k][l].getTemperature()/8;
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/8;
          //absorb energy from sun
          if(copyWorld[i][j].getDaisy() != null){
            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
          }
          else{
            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);

        }

        //Calculate the right-bottom patch's temperature
        if(i == row && j == col){
          //lose 50% for distribution
          sumTemperature -= copyWorld[i][j].getTemperature()/2;
          //sum surronding distribution
          for(int k = i - 1; k < i; k ++){
            for(int l = j - 1; l < j; l++){
              sumTemperature += copyWorld[k][l].getTemperature()/8;
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/8;
          //absorb energy from sun
          if(copyWorld[i][j].getDaisy() != null){
            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
          }
          else{
            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);

        }

        //Calculate the left-middle patch's temperature
        if(i > 0 && i < row && j == 0){
          //lose 50% for distribution
          sumTemperature -= copyWorld[i][j].getTemperature()/2;
          //sum surronding distribution
          for(int k = i - 1; k < i + 1; k ++){
            for(int l = j; l < j + 1; l++){
              sumTemperature += copyWorld[k][l].getTemperature()/8;
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/8;
          //absorb energy from sun
          if(copyWorld[i][j].getDaisy() != null){
            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
          }
          else{
            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);

        }
        //Calculate the right-middle patch's temperature
        if(i > 0 && i < row && j == col){
        //lose 50% for distribution
          sumTemperature -= copyWorld[i][j].getTemperature()/2;
          //sum surronding distribution
          for(int k = i - 1; k < i + 1; k ++){
            for(int l = j - 1; l < j; l++){
              sumTemperature += copyWorld[k][l].getTemperature();
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/8;
          //absorb energy from sun
          if(copyWorld[i][j].getDaisy() != null){
            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
          }
          else{
            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);
        }

        //Calculate all middle patches' temperature
        if(i > 0 && i < row && j > 0 && j < col){
          //lose 50% for distribution
          sumTemperature -= copyWorld[i][j].getTemperature()/2;
          //sum surronding distribution
          for(int k = i - 1; k < i + 1; k ++){
            for(int l = j - 1; l < j + 1; l++){
              sumTemperature += copyWorld[k][l].getTemperature()/8;
            }
          }
          sumTemperature -= copyWorld[i][j].getTemperature()/8;
          //absorb energy from sun
          if(copyWorld[i][j].getDaisy() != null){
            sumTemperature += solarLuminosity * (1 - copyWorld[i][j].getDaisy().getAlbedoRate());
          }
          else{
            sumTemperature += ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(world[i][j].getDaisy() == null){
            absorbLuminosity = 0;
          }else{
            absorbLuminosity = ((1 - albedoOfSurface) * solarLuminosity);
          }
          if(absorbLuminosity > 0){
            localHeating = 72 * absorbLuminosity + 80;
          }else{
            localHeating = 80;
          }

          //return the temperature to the world
          world[i][j].setTemperature((sumTemperature + localHeating)/2);

        }

      }
    }

  }

  public void meanTemperature(){
    double total = 0.0;
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        Patch patch = world[i][j];
        total += patch.getTemperature();
      }
    }
    globalTemperature = total / (row * col);
  }

  public void startWhiteRandomly(){
    int num = (int) (((double) startWhitePercent / 100.0) * ((double)row * (double)col) + 0.5);
    Random random = new Random();
    while(num > 0){
      int i = random.nextInt(row);
      int j = random.nextInt(col);
      if(world[i][j].getDaisy() == null){
        world[i][j].setDaisy(new Daisy(DaisyType.WHITE));
        num--;
      }
    }
  }

  public void startBlackRandomly(){
    int num = (int) (((double) startBlackPercent / 100.0) * ((double)row * (double)col) + 0.5);
    Random random = new Random();
    while(num > 0){
      int i = random.nextInt(row);
      int j = random.nextInt(col);
      if(world[i][j].getDaisy() == null){
        world[i][j].setDaisy(new Daisy(DaisyType.BLACK));
        num--;
      }
    }
  }

  public void setDaisyAge(){
    Random random = new Random();
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        if(world[i][j].getDaisy() != null){
          world[i][j].getDaisy().setAge(random.nextInt(Parameters.MAX_AGE));
        }
      }
    }
  }

  public void printWorld(){
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        System.out.print("|");
        if(world[i][j].getDaisy() == null){
          System.out.print("          ");
        } else
          System.out.print(
              String.format("%3s",  world[i][j].getDaisy().getAge()) + "  " +
                  world[i][j].getDaisy().getDaisyType().toString());
        if(j == col - 1){
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
  public void updateDaisyAge(){
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        if(copyWorld[i][j].getDaisy() != null){
          world[i][j].getDaisy().setAge((world[i][j].getDaisy().getAge() + 1));
          if(world[i][j].getDaisy().getAge() == 25){
            world[i][j].goDieDaisy();
          }
        }
      }
    }
  }

  public void bornNewDaisy(){
    for(int i = 0; i < row; i++){
      for(int j = 0; j< col; j++){
        //random-born right-top daisy
        if(i == 0 && j == 0){
          for(int k = 0; k < i + 1; k ++){
            for(int l = 0; l < j + 1; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                  world[k][l].setDaisy(new Daisy(world[i][j].getDaisy().getDaisyType()));
                  world[k][l].getDaisy().setAge(0);
                }
              }
            }
          }
        }
        //random-born the middle-top patches' daisy
        if(i == 0 && j > 0 && j < col){
          for(int k = 0; k < i + 1; k ++){
            for(int l = j - 1; l < j + 1; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                }
              }
            }
          }
        }

        //random-born the right-top patch's daisy
        if(i == 0 && j == col){
          for(int k = 0; k < i + 1; k ++){
            for(int l = j - 1; l < j ; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                }
              }
            }
          }
        }

        //random-born the left-bottom patch's daisy
        if(i == row && j == 0){
          for(int k = i - 1; k < i; k ++){
            for(int l = 0; l < j + 1; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                }
              }
            }
          }
        }
        //random-born the middle-bottom patches' daisy
        if(i == row && j > 0 && j < col){
          for(int k = row - 1; k < i; k ++){
            for(int l = j - 1; l < j + 1; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                }
              }
            }
          }
        }

        //random-born the right-bottom patch's daisy
        if(i == row && j == col){
          for(int k = i - 1; k < i; k ++){
            for(int l = j - 1; l < j; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                }
              }
            }
          }
        }

        //random-born the left-middle patch's daisy
        if(i > 0 && i < row && j == 0){
          for(int k = i - 1; k < i + 1; k ++){
            for(int l = j; l < j + 1; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                }
              }
            }
          }
        }
        //random-born the right-middle patch's daisy
        if(i > 0 && i < row && j == col){
          for(int k = i - 1; k < i + 1; k ++){
            for(int l = j - 1; l < j; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                }
              }
            }
          }
        }

        //random-born all middle patches' daisy
        if(i > 0 && i < row && j > 0 && j < col){
          for(int k = i - 1; k < i + 1; k ++){
            for(int l = j - 1; l < j + 1; l++){
              if(world[k][l].getDaisy() == null && copyWorld[i][j].getDaisy() != null){
                seedThreshold = ((0.1457 * world[i][j].getTemperature()) -
                        (0.0032 * (world[i][j].getTemperature() * world[i][j].getTemperature())) - 0.6443);
                double randomdouble = Math.random();
                if(randomdouble < seedThreshold && world[i][j].getDaisy() != null){
                  world[k][l].generateDaisy(world[i][j].getDaisy().getDaisyType(), world[i][j].getDaisy());
                }
              }
            }
          }
        }
      }
    }

  }

  public void updateGlobalTemperature(){
    double sum = 0;

    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        sum += world[i][j].getTemperature();

      }
      globalTemperature = sum/(row * col);
    }
  }

  public void copyTheWorld(){
    for(int i = 0; i < row; i ++){
      for(int j = 0; j < col; j++){
        copyWorld[i][j].setDaisy(world[i][j].getDaisy());
        if(world[i][j].getDaisy() == null){
          copyWorld[i][j].setDaisy(world[i][j].getDaisy());
        }else{
          copyWorld[i][j].getDaisy().setAge(world[i][j].getDaisy().getAge());
          copyWorld[i][j].getDaisy().setDaisyType(world[i][j].getDaisy().getDaisyType());
        }
        copyWorld[i][j].setTemperature(world[i][j].getTemperature());
        copyWorld[i][j].setColor(world[i][j].getColor());

      }
    }
  }

  public void justifTem(){
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        world[i][j].setTemperature(20);
      }
    }
  }

  public void updateWorld(){
    copyTheWorld();


    patchCalTemperature();
    updateGlobalTemperature();
    updateDaisyAge();
    //justifTem();
    bornNewDaisy();

    System.out.println(globalTemperature);

    System.out.println(world[0][0].getTemperature());
    System.out.println(seedThreshold);
    double randomdouble = Math.random();
    System.out.println(randomdouble);
  }
}
