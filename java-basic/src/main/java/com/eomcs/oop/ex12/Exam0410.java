// 리턴 문장에 람다(lambda) 활용
package com.eomcs.oop.ex12;

public class Exam0410 {

  static interface Interest {
    double compute(int money);
  }

  // 팩토리 메서드
  // => Interest 구현체를 생성하여 리턴하는 메서드
  // 객체 생성 과정이 복잡해서 만든 것, 인터페이스를 클래스가 아닌 메서드로 구현한다.
  static Interest getInterest(final double rate) {
    // 로컬 클래스로 인터페이스 구현한 후 객체 리턴하기
    class InterestImpl implements Interest {
      @Override
      public double compute(int money) {
        return money + (money * rate / 100);
      }
    }
    return new InterestImpl();
  }

  public static void main(String[] args) {
    Interest i1 = getInterest(1.5);
    System.out.printf("금액: %.2f\n", i1.compute(1_0000_0000));

    Interest i2 = getInterest(2.5);
    System.out.printf("금액: %.2f\n", i2.compute(1_0000_0000));
  }

}


