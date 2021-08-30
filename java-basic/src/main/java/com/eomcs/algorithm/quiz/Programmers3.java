package com.eomcs.algorithm.quiz;

public class Programmers3 {

  public static void main(String[] args) {

    int[] arr = new int [] {10};
    int[] arr2 = new int[] {4,3,5,1};

    Solution3 sol = new Solution3();
    System.out.println(sol.solution(arr));
    System.out.println(sol.solution(arr2));

  }


}

class Solution3 {
  public int[] solution(int[] arr) {
    if ( arr.length == 1 )
      return new int[] {-1};   

    int index = 0;
    for (int i = 0; i < arr.length ; i++) {
      if(arr[index] > arr[i]) {
        index = i;
      }
    }

    System.out.println(index);

    int[] answer = new int[arr.length - 1]; 
    int count = 0;
    for(int i = 0; i < arr.length ; i++) {
      if(i == index) {
        continue;
      }
      answer[count] = arr[i];
      count++;
    }      

    return answer;

  }
}


