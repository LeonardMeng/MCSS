package com.group2;

/**
 * Daisy
 */
public class Daisy {
  private int age;
  private DaisyType daisyType;
  private double albedoBlack = Parameters.BLACK_ALBEDO;
  private double albedoWhite = Parameters.WHITE_ALBEDO;

  public Daisy(DaisyType daisyType) {
    this.daisyType = daisyType;
  }

  public Daisy(int age, DaisyType daisyType) {
    this.age = age;
    this.daisyType = daisyType;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public DaisyType getDaisyType() {
    return daisyType;
  }

  public double getAlbedoRate(){
    if(daisyType == DaisyType.BLACK)
      return albedoBlack;
    else if(daisyType == DaisyType.WHITE)
      return albedoWhite;
    else
      return 0;
  }

  public void setDaisyType(DaisyType daisyType) {
    this.daisyType = daisyType;
  }
}
