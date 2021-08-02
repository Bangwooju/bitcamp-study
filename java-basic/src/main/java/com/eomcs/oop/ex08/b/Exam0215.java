// 캡슐화(encapsulation) - 접근 범위 테스트 - 종합
package com.eomcs.oop.ex08.b;

public class Exam0215 extends com.eomcs.oop.ex08.b.sub.Y {

  public static void main(String[] args) {
    m1(new X());
    m2(new com.eomcs.oop.ex08.b.sub.Y());
  }

  // 같은 패키지의 객체를 파라미터로 받은 경우
  static void m1(X obj) {
    //    obj.privateVar = 100; //접근 불가
    obj.defaultVar = 100; // 같은 패키지
    obj.protectedVar = 100; // 같은 패키지
    obj.publicVar = 100; // 모두 가능
  }

  // 다른 패키지의 객체를 파라미터로 받은 경우
  static void m2(com.eomcs.oop.ex08.b.sub.Y obj) {
    //  obj.privateVar = 100; //접근 불가
    //  obj.defaultVar = 100; // 다른 패키지
    //  obj.protectedVar = 100; // 다른 패키지
    obj.publicVar = 100; // 모두 가능
  }

  // 같은 객체를 파라미터로 받은 경우
  static void m3(com.eomcs.oop.ex08.b.Exam0215 obj) {
    // 다음 obj를 통해 접근하는 변수는 Exam0215 클래스의 인스턴스를 생성할 때 만든 변수이다.
    // 그러나 Exam0215 클래스에 선언된 변수가 아니라 상속 받은 변수이다.
    // 상속 받은 변수인 경우 상속 받은 클래스를 기준으로 접근 가능 여부를 따져야한다.
    //

    //  obj.privateVar = 100; //접근 불가
    //  obj.defaultVar = 100; // 다른 패키지
    obj.protectedVar = 100; // 상속을 통해 만든 변수는 접근 가능
    // Y클래스 설계도에 따라 만든 변수이지만, 상속받아 만든 변수이다.
    obj.publicVar = 100; // 모두 가능
  }


}

// private      : 클래스 안에서만 접근 가능
// (default)    : private + 같은 패키지 소속
// protected    : (default) + 서브 클래스로 만든 변수인 경우 서브 클래스에서 접근 가능
// public       : 모두 접근 가능
//
// 실무
// => 인스턴스 변수는 보통 private으로 접근을 제한한다.
// => 겟터,셋터는 public으로 접근을 모두에게 공개한다.
// => 일반 메서드도 public으로 접근을 모두에게 공개한다.
// => 그 클래스 내부에서만 사용되는 메서드는 private으로 접근을 제한한다.
// => 자식 클래스의 접근을 허용할 필요가 있을 경우에만 protected로 만든다.
// => 다른 개발자가 사용할 클래스 모음을 만들 때
//    그 모음집 내에서만 사용될 변수나 메서드인 경우 (default)로 접근을 제한한다.
//    즉 라이브러리를 만드는 개발자인 경우 (default)를 사용하는 경우가 있다.






