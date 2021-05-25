package com.group2;

public class Patch {
  private String color;
  private Daisy daisy;
  private double temperature;




  public void calTemperature(double albedoOfSurface, double solarLuminosity){
    double absorbedLuminosity =  0.0;
    double localHeating = 0;
    if(daisy == null){
      absorbedLuminosity = (1 - albedoOfSurface) * solarLuminosity;
    } else {
      absorbedLuminosity = (1 - daisy.getAlbedoRate()) * solarLuminosity;
    }

    if(absorbedLuminosity > 0){
      localHeating = 72 * Math.log(absorbedLuminosity) + 80;
    }else {
      localHeating = 80;
    }

    temperature = (temperature + localHeating) / 2;
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
  //remove the dead daisy
  public void goDieDaisy(){
    this.daisy = null;
    this.color = null;

  }
  //born a age 0 daisy
  public void generateDaisy(DaisyType DT, Daisy daisy){

      if(DT == DaisyType.BLACK){
        this.color = "Black";
      }
      else if(DT == DaisyType.WHITE){
        this.color = "White";
      }

      setDaisy(new Daisy(DT));
      this.daisy.setAge(0);


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
