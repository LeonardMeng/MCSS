package com.group2;

public class Patch {
  // Parameter initial
  private Daisy daisy;
  private double albedo = 0;
  private double temperature;


  /**
   * Calculate patch's temperature
   * @param albedoOfSurface Surface absorption rate
   * @param solarLuminosity Light intensity
   */
  public void calTemperature(double albedoOfSurface, double solarLuminosity){
    double absorbedLuminosity =  0.0;
    double localHeating = 0;
    //  For extension begin
    if(albedo > 0)
      albedoOfSurface = albedo;
    // For extension end
    // Set the absorption rate according to whether there are daisies.
    if(daisy == null){
      absorbedLuminosity = (1 - albedoOfSurface) * solarLuminosity;
    } else {
      absorbedLuminosity = (1 - daisy.getAlbedoRate()) * solarLuminosity;
    }
    // Set the local heat according to the absorption luminosity.
    if(absorbedLuminosity > 0){
      localHeating = 72 * Math.log(absorbedLuminosity) + 80;
    }else {
      localHeating = 80;
    }
    //  Calculete temperature
    temperature = (temperature + localHeating) / 2;
  }

  // Return the temperature
  public double getTemperature() {
    return temperature;
  }

  // Set the temperature in this class
  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  // Return the Daisy
  public Daisy getDaisy() {
    return daisy;
  }

  // Set the daisy in this class
  public void setDaisy(Daisy daisy) {
    this.daisy = daisy;
  }
  // Remove the dead daisy
  public void goDieDaisy(){
    this.daisy = null;

  }
  
  // Get the albedo
  public double getAlbedo() {
    return albedo;
  }
  
  // Set the albedo in this class
  public void setAlbedo(double albedo) {
    this.albedo = albedo;
  }

  // Out put formatting
  @Override
  public String toString() {
    return "Patch{" +
        ", daisy=" + daisy +
        ", temperature=" + temperature +
        '}';
  }
}
