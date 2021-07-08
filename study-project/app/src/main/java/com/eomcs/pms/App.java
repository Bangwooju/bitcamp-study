package com.eomcs.pms;

import java.util.Date;
import java.util.Scanner;
//1)배열 사용전
//2)배열 사용 후
//3) 반복문 사용 : while문 사용 ^_^
//4) 반복문 사용 : for 문 사용 ^3^
//5) 여러 문장에서 반복해서 사용하는 값은 변수에 담아서 사용한다.
//6) 조회용으로만 사용할 변수라면 상수로 선언한다.
//7) 특정 조건에 따라 반복을 멈춘다.
//8) 날짜의 출력 형식을 "yyyy-MM-dd"로 변경하고 요구사항대로 print한다.

public class App {

  public static void main(String[] args) {
    System.out.println("[회원]");

    final int MAXLENGTH = 5;
    int[] no = new int[MAXLENGTH];
    String[] name = new String[MAXLENGTH];
    String[] email = new String[MAXLENGTH];
    String[] password = new String[MAXLENGTH];
    String[] photo = new String[MAXLENGTH];
    String[] tel = new String[MAXLENGTH];
    Date[] registedDate = new Date [MAXLENGTH];

    Scanner keyboardScan = new Scanner(System.in);
    int size = 0;
    for (int i = 0; i < MAXLENGTH ; i++) {
      size = size + 1;
      System.out.print("번호? ");
      no[i] = Integer.parseInt(keyboardScan.nextLine());
      System.out.print("이름? ");
      name[i] = keyboardScan.nextLine();
      System.out.print("이메일? ");
      email[i] = keyboardScan.nextLine();
      System.out.print("암호? ");
      password[i] = keyboardScan.nextLine();
      System.out.print("사진? ");
      photo[i] = keyboardScan.nextLine();
      System.out.print("전화? ");
      tel[i] = keyboardScan.nextLine();
      registedDate[i] = new Date();
      System.out.println();

      System.out.println("계속 입력하시겠습니까?(y/N)");
      String input = keyboardScan.nextLine();

      if(input.equalsIgnoreCase("N") ||input.equals("") ) {
        break;
      }
    }

    keyboardScan.close(); // 데이터 입출력이 끝났으면 도구를 닫는다.

    System.out.println("--------------------------------");

    for(int i =0; i < size ; i++) {
      System.out.printf("%d, %s, %s, %s,%tY-%5$tm-%5$td\n"
          ,no[i],name[i], email[i],tel[i], registedDate[i]);
      System.out.println();

    }

  }

}
