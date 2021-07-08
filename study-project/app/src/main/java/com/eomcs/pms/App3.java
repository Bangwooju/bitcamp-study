package com.eomcs.pms;

import java.sql.Date;
import java.util.Scanner;

public class App3 {

  public static void main(String[] args) {
    System.out.println("[작업]");

    final int MAXLENGTH = 100;
    int size =0;
    Scanner keyboardScan = new Scanner(System.in);


    System.out.print("프로젝트? ");
    String project = keyboardScan.nextLine();

    int[] no = new int [MAXLENGTH];
    String[] content = new String[MAXLENGTH];
    Date[] deadline = new Date[MAXLENGTH];
    String[] owner = new String[MAXLENGTH];
    int[] status = new int[MAXLENGTH];


    for (int i = 0; i < 5; i++) {
      System.out.print("번호? ");
      no[i] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("내용? ");
      content[i] = keyboardScan.nextLine();

      System.out.print("마감일? ");
      deadline[i] = Date.valueOf(keyboardScan.nextLine());

      System.out.println("상태?");
      System.out.println("0: 신규");
      System.out.println("1: 진행중");
      System.out.println("2: 완료");
      System.out.print("> ");
      status[i] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("담당자? ");
      owner[i] = keyboardScan.nextLine();
      size += 1;

      System.out.println("계속 입력하시겠습니까? (y/N) ");
      String input = keyboardScan.nextLine();
      if (input.equalsIgnoreCase("N")|| input.equals("N")) {
        break;
      }

    }

    keyboardScan.close();

    System.out.println("--------------------------------");
    System.out.printf("[%s]", project);
    System.out.println();

    for(int i =0; i < size; i++ ) {
      String statusLabel = null;
      switch (status[i]) {
        case 1:
          statusLabel = "상태: 진행중";
          break;
        case 2:
          statusLabel = "상태: 완료";
          break;
        default:
          statusLabel = "상태: 신규";
      }
      System.out.printf("%d, %s, %s, %s, %s\n", no[i],content[i],deadline[i],statusLabel, owner[i]);
    }
  }
}
