package com.eomcs.algorithm.quiz;

public class Test {

  static int maxDiff(int[] values) {
    int answer = 0;

    for (int i = 1; i < values.length; i++) {
      if(Math.abs(values[i] - values[i-1]) > answer) {
        answer = Math.abs(values[i]-values[i-1]);
      }
    }
    return answer;
  }


  public static void main(String[] args) {
    System.out.println(maxDiff(new int[]{2, 9, 1, 0}));

  }

}


