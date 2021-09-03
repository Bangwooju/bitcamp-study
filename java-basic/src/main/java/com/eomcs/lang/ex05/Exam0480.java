package com.eomcs.lang.ex05;

//# 비트 연산자 : 응용 사용자 권한 관리에 적용
//
public class Exam0480 {
  public static void main(String[] args) {
    //1) 사용자 권한을 값으로 정의
    final int LOGOUT = 0x01;
    final int GENERAL = 0x02;
    final int ADMIN = 0x04;

    //2) 메뉴의 접근 범위 설정
    int menu1 = LOGOUT;
    int menu2 = GENERAL;
    int menu3 = ADMIN;
    int menu4 = ADMIN | LOGOUT | GENERAL;

    System.out.println((menu1 & LOGOUT) > 0);
    //  어떤 값에 대해 logout 값을 & 한다는 것은 
    // LOGOUT 비트가 1인지 검사한다는 뜻이다.
    //  그 값을 2진수로 보았을 때 logout 비트가 1이라는 의미이다.

    System.out.println((menu1 & GENERAL) > 0);

    System.out.println((menu1 & ADMIN) > 0);

  }
}
