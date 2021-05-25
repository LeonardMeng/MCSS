package com.group2;

public class Daisy {
  private int age;
  private DaisyType daisyType;
  private double albedoBlack = 0.8;
  private double albedoWhite = 0.4;

  public Daisy(DaisyType daisyType) {
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
