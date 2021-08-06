package com.eomcs.pms.handler;

//Generalization을 통해 수퍼클래스를 정의한 경우 그 수퍼클래스는
// 서브클래스의 공통분모를 모아두는 용도로 만드는 것이다.
// 직접 사용하기위해 만든 클래스가 아니다.
// 즉 서브클래스에게 공통분모를 상속해주기위한 역할을 한다.
// 이런 클래스의 경우 추상클래스로 정의한다.

// 인터페이스
// - 모든 필드는 public, static, final 필드이다,
// - 모든 메서드는 public, abstract 메서드이다. 
// - default 키워드를 이용하여 구현 메서드를 만들 수 있다.
// - 객체 사용 규칙을 정의할 때 사용하는 문법이다.
// - 당연히 인스턴스를 생성할 수 없다.

public interface List {
  // 인터페이스의 메서드는 기본적으로 public 이고 abstract이다.
  void add(Object item);
  Object[] toArray();
  boolean remove(Object item) ;



}
