package com.eomcs.algorithm.quiz;

public class Test {

  public static int divisorsPairs(int[] sequence) {

    int pairs = 0;
    for(int i = 0; i < sequence.length; i++) {
      for (int j = i + 1; j < sequence.length; j++) {
        if((sequence[i] % sequence[j]) == 0 || (sequence[j] % sequence[i]) == 0) {
          pairs++;          
        }
      }
    }
    return pairs;
  }

  public static void main(String[] args) {
    int[] values = {8, 4, 2, 3, 6, 10, 5};
    System.out.println(divisorsPairs(values));

  }

}


