package com.eomcs.oop.ex00.util;

public class Calculator {

  public int result = 0;

  public void plus(int... values) {
    for(int value : values)
      this.result += value;
  }

  public void minus(int value) {
    this.result -= value;
  }

  public void multiple(int value) {
    this.result *= value;
  }

  public void divide(int value) {
    this.result /= value;
  }

}

