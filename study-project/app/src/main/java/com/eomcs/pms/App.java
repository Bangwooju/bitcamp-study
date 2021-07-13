package com.eomcs.pms;

import java.sql.Date;
import java.util.Scanner;

public class App {

  public static void main(String[] args) {
    //1)변경 준비
    //2)사용자에게 명령프롬프트 출력
    //3)사용자의 명령을 입력 받아 출력
    //4)명령어 입력받아 출력하는 것을 무한반복
    //5)exit 나 quit 명령을 입력하면 반복 실행 중지
    //6)/member/add 와 /member/list 구분하기
    //7) /member/add 명령처리
    //8) /memeber/list 명령처리
    //9) /project/add 명령 처리
    //10) /project/list 명령 처리
    //11) /task/add 명령 처리
    //12) /task/list 명령 처리

    Scanner keyboardScan = new Scanner(System.in);
    // 회원정보
    final int LENGTH = 100;

    int[] no = new int[LENGTH];
    String[] name = new String[LENGTH];
    String[] email = new String[LENGTH];
    String[] password = new String[LENGTH];
    String[] photo = new String[LENGTH];
    String[] tel = new String[LENGTH];
    Date[] registeredDate = new Date[LENGTH];
    int size = 0;

    //프로젝트 정보
    final int PROJECT_LENGTH = 1000;

    int[] pNo = new int[PROJECT_LENGTH];
    String[] pTitle = new String[PROJECT_LENGTH];
    String[] pContent = new String[PROJECT_LENGTH];
    Date[] pStartDate = new Date[PROJECT_LENGTH];
    Date[] pEndDate = new Date[PROJECT_LENGTH];
    String[] pOwner = new String[PROJECT_LENGTH];
    String[] pMembers = new String[PROJECT_LENGTH];
    int pSize = 0;

    //업무 정보
    final int TASK_LENGTH = 100;

    int[] tNo = new int[TASK_LENGTH];
    String[] tContent = new String[TASK_LENGTH];
    Date[] tDeadline = new Date[TASK_LENGTH];
    String[] tOwner = new String[TASK_LENGTH];
    int[] tStatus = new int[TASK_LENGTH];
    int tSize = 0;

    while (true) { System.out.print("명령 >");
    String input = keyboardScan.nextLine();
    if (input.equals("quit")|| input.equals("exit")) {
      System.out.println("안녕!");
      break;
    } else if (input.equals("/member/add")) {
      System.out.println("[회원 등록]");
      System.out.print("번호? ");
      no[size] = Integer.parseInt(keyboardScan.nextLine());

      System.out.print("이름? ");
      name[size] = keyboardScan.nextLine();

      System.out.print("이메일? ");
      email[size] = keyboardScan.nextLine();

      System.out.print("암호? ");
      password[size] = keyboardScan.nextLine();

      System.out.print("사진? ");
      photo[size] = keyboardScan.nextLine();

      System.out.print("전화? ");
      tel[size] = keyboardScan.nextLine();

      registeredDate[size] = new Date(System.currentTimeMillis());

      size++;

    } else if (input.equals("/member/list")) {
      System.out.println("[회원 목록]");
      for (int i = 0; i < size; i++) {
        // 번호, 이름, 이메일, 전화, 가입일
        System.out.printf("%d, %s, %s, %s, %s\n", // 출력 형식 지정
            no[i], name[i], email[i], tel[i], registeredDate[i]);
      } 

    } else if (input.equals("/project/add")) {
      System.out.println("[프로젝트 등록]");
      System.out.print("번호? ");
      pNo[pSize] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("프로젝트명? ");
      pTitle[pSize] = keyboardScan.nextLine();

      System.out.print("내용? ");
      pContent[pSize] = keyboardScan.nextLine();

      System.out.print("시작일? ");
      pStartDate[pSize] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("종료일? ");
      pEndDate[pSize] = Date.valueOf(keyboardScan.nextLine());

      System.out.print("만든이? ");
      pOwner[pSize] = keyboardScan.nextLine();

      System.out.print("팀원? ");
      pMembers[pSize] = keyboardScan.nextLine();

      pSize++;

    } else if (input.equals("/project/list")) {
      System.out.println("[프로젝트 목록]");
      for (int i = 0; i < pSize; i++) {
        // 번호, 프로젝트명, 시작일, 종료일, 만든이
        System.out.printf("%d, %s, %s, %s, %s\n", // 출력 형식 지정
            pNo[i], pTitle[i], pStartDate[i], pEndDate[i], pOwner[i]);
      }  
    } else if (input.equals("/task/add")) {
      System.out.println("[작업 등록]");
      System.out.print("번호? ");
      tNo[tSize] = Integer.parseInt(keyboardScan.nextLine());

      System.out.print("내용? ");
      tContent[tSize] = keyboardScan.nextLine();

      System.out.print("마감일? ");
      tDeadline[tSize] = Date.valueOf(keyboardScan.nextLine());

      System.out.println("상태?");
      System.out.println("0: 신규");
      System.out.println("1: 진행중");
      System.out.println("2: 완료");
      System.out.print("> ");
      tStatus[tSize] = Integer.valueOf(keyboardScan.nextLine());

      System.out.print("담당자? ");
      tOwner[tSize] = keyboardScan.nextLine();

      tSize++;
    } else if (input.equals("/task/list")){
      System.out.println("[작업 목록]");
      for (int i = 0; i < tSize; i++) {
        String stateLabel = null;
        switch (tStatus[i]) {
          case 1:
            stateLabel = "진행중";
            break;
          case 2:
            stateLabel = "완료";
            break;
          default:
            stateLabel = "신규";
        }
        System.out.printf("%d, %s, %s, %s, %s\n", // 출력 형식 지정
            tNo[i], tContent[i], tDeadline[i], stateLabel, tOwner[i]);
      }
    }
    else {
      System.out.println("실행할 수 없는 명령입니다.");
    }
    System.out.println();
    }
    keyboardScan.close();
  }
}
