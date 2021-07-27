package com.eomcs.oop.ex03;

// 스태틱 변수를 사용할 때 다음과같이 소속 클래스를 미리 밝혀두면
// 클래스 이름 없이 변수를 스태틱 변수를 사용할 수 있다.

import static com.eomcs.oop.ex03.Member.GUEST;
import static com.eomcs.oop.ex03.Member.MANAGER;
import static com.eomcs.oop.ex03.Member.MEMBER;
public class Exam0163 {
  // 상수 변수를 같은 클래스의 멤버처럼 사용하고 싶다면,
  // 클래스 이름 없이 사용하고싶다면 상수변수를 미리 inport하여 컴파일러에게 그 소속을 알려줘라

  public static void main(String[] args) {

    // Member와 관련된 상수라면 
    // Member 클래스에 선언하는 것이 유지보수에 더 낫다.

    Member m4 = new Member();
    m4.id = "aaa";
    m4.password = "1111";
    m4.type = GUEST;
    // 스태틱 변수는 변수명 앞에 클래스명을 명시해야 한다.

    Member m5 = new Member();
    m5.id = "bbb";
    m5.password = "1111";
    m5.type = MANAGER;

    Member m6 = new Member();
    m6.id = "ccc";
    m6.password = "1111";
    m6.type = MEMBER;
  }
}
