package com.group2;

public class Daisy {
  private int age;
  private DaisyType daisyType;

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

  public void setDaisyType(DaisyType daisyType) {
    this.daisyType = daisyType;
  }
}
