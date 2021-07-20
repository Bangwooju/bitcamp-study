package com.eomcs.oop.ex00;

public class Test3 {


  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float average;


    public void Calculate() {

      this.sum = this.kor + this.eng + this.math;
      this.average = this.sum / 3f;

    }
  }



  public static void main(String[] args) {

    Score score = new Score();

    // - 클래스로 만든 메모리는 레퍼런스를 통해 접근한다.
    score.name = "홍길동";
    score.kor = 100;
    score.eng = 90;
    score.math = 85;

    score.Calculate();

    System.out.printf("%s, %d, %d, %d, %d, %.1f\n", score.name, score.kor, score.eng, score.math,
        score.sum, score.average);
  }




}
