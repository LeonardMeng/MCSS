package com.group2;

/**
 * Daisy
 */
public class Daisy {
  // Private variable
  private int age;
  private DaisyType daisyType;
  private double albedoBlack = Parameters.BLACK_ALBEDO;
  private double albedoWhite = Parameters.WHITE_ALBEDO;
  
  // Constructor
  public Daisy(DaisyType daisyType) {
    this.daisyType = daisyType;
  }
  
  // Constructor
  public Daisy(int age, DaisyType daisyType) {
    this.age = age;
    this.daisyType = daisyType;
  }
  
  // Return the age
  public int getAge() {
    return age;
  }

  // Set the age
  public void setAge(int age) {
    this.age = age;
  }

  // Return the type of daisy
  public DaisyType getDaisyType() {
    return daisyType;
  }

  // Return the albedo
  public double getAlbedoRate(){
    if(daisyType == DaisyType.BLACK)
      return albedoBlack;
    else if(daisyType == DaisyType.WHITE)
      return albedoWhite;
    else
      return 0;
  }
  
  // Set the type of daisy
  public void setDaisyType(DaisyType daisyType) {
    this.daisyType = daisyType;
  }
}
