package com.group2;

public class Patch {
  private String color;
  private Daisy daisy;
  private double temperature;



  public void calTemperature(){

  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public Daisy getDaisy() {
    return daisy;
  }

  public void setDaisy(Daisy daisy) {
    this.daisy = daisy;
  }

  @Override
  public String toString() {
    return "Patch{" +
        "color='" + color + '\'' +
        ", daisy=" + daisy +
        ", temperature=" + temperature +
        '}';
  }
}
