package com.eomcs.lang.ex05;

//# 비트 연산자 : 응용 사용자 권한 관리에 적용
//
public class Exam0481 {
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

    System.out.println((menu2 & LOGOUT) > 0);
    System.out.println((menu2 & GENERAL) > 0);
    System.out.println((menu2 & ADMIN) > 0);
    System.out.println("---------------------");
    System.out.println((menu3 & LOGOUT) > 0);
    System.out.println((menu3 & GENERAL) > 0);
    System.out.println((menu3 & ADMIN) > 0);
    System.out.println("---------------------");
    System.out.println((menu4 & LOGOUT) > 0);
    System.out.println((menu4 & GENERAL) > 0);
    System.out.println((menu4 & ADMIN) > 0);

  }
}
