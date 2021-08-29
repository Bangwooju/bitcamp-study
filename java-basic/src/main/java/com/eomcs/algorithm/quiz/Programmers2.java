package com.eomcs.algorithm.quiz;

public class Programmers2 {

  // 자릿수 더하기
  public static void main(String[] args) {

    Solution2 sol = new Solution2();
    System.out.println(sol.solution(987));

  }
}

class Solution2 {
  public int solution(int n) {
    int answer = 0;
    // [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.

    while ( n != 0) {
      answer += n % 10;
      n = n / 10;
    }

    return answer;
  }
}

