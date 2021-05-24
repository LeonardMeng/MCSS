package com.group2;

import java.util.Random;

public class World {


  private Patch[][] world;

  private int startWhitePercent;
  private int row;
  private int col;
  private int startBlackPercent;

  private double solarLuminosity;
  private double globalTemperature;

  public World(int row, int col, double solarLuminosity, int startWhitePercent, int startBlackPercent) {
    this.world = new Patch[row][col];
    for(int i = 0; i < row; i++){
      for(int j = 0; j < col; j++){
        world[i][j] = new Patch();
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

}
