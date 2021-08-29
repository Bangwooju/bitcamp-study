package com.eomcs.algorithm.quiz;

public class Programmers {

  // weekly challenge 1주차

  public static void main(String args[]){
    Solution sol = new Solution();
    System.out.println(sol.solution(3, 20, 4));

    Solution sol2 = new Solution();
    System.out.println(sol2.solution(5, 10, 1));

  }

}


class Solution {
  public long solution(int price, int money, int count) {
    long answer = 0;
    long totalPrice = 0;

    for (int i = 0; i < count ; i++) {
      totalPrice += price* (i + 1); 
    }

    answer = Math.abs(totalPrice - money);

    return answer;
  }
}
