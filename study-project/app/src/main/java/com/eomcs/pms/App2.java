package com.eomcs.pms;

import java.sql.Date;
import java.util.Scanner;

public class App2 {

  public static void main(String[] args) {
    System.out.println("[프로젝트]");

    Scanner keyboardScan = new Scanner(System.in);

    final int LENGTH = 199;

    int[] no = new int [LENGTH];
    String[] title = new String[LENGTH];
    String[] content = new String[LENGTH];
    Date[] startDate= new Date[LENGTH];
    Date[] endDate = new Date[LENGTH];
    String[] owner = new String[LENGTH];
    String[] members = new String[LENGTH];

    int size = 0;
    for(int i =0; i < LENGTH; i++) {
      System.out.print("번호? ");
      no[i] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("프로젝트명? ");
      title[i] = keyboardScan.nextLine();

      System.out.print("내용? ");
      content[i] = keyboardScan.nextLine();

      System.out.print("시작일? ");
      startDate[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("종료일? ");
      endDate[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("만든이? ");
      owner[i] = keyboardScan.nextLine();

      System.out.print("팀원? ");
      members[i] = keyboardScan.nextLine();

      size = size + 1;

      System.out.println();

      System.out.println("계속 입력하시겠습니까? (y/N)");
      String str = keyboardScan.nextLine();
      if (str.equalsIgnoreCase("N")|| str.equals("N")) {
        break;
      }
      System.out.println();

    }

    keyboardScan.close();

    System.out.println("--------------------------------");

    for(int i =0; i < size; i++) {
      System.out.printf("번호: %d\n", no[i]);
      System.out.printf("프로젝트명: %s\n", title[i]);
      System.out.printf("내용: %s\n", content[i]);
      System.out.printf("시작일: %s\n", startDate[i]);
      System.out.printf("종료일: %s\n", endDate[i]);
      System.out.printf("만든이: %s\n", owner[i]);
      System.out.printf("팀원: %s\n", members[i]);
      System.out.println();

    }
  }
}
