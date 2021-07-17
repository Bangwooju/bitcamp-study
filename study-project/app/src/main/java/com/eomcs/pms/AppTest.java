
package com.eomcs.pms;

import java.sql.Date;
import java.util.Scanner;

public class AppTest{

  static Scanner sc = new Scanner(System.in);

  static final int MAX_LENGTH = 100; 
  static int[] no = new int [MAX_LENGTH];
  static String[] name = new String[MAX_LENGTH];
  static String[] email = new String[MAX_LENGTH];
  static String[] password = new String[MAX_LENGTH];
  static String[] photo = new String[MAX_LENGTH];
  static String[] tel = new String[MAX_LENGTH]; 
  static Date[] registeredDate = new Date[MAX_LENGTH];
  static int size =0;

  static final int PROJECT_LENGTH = 1000;

  static int[] pNo = new int [PROJECT_LENGTH];
  static String[] pTitle = new String[PROJECT_LENGTH];
  static String[] pContent = new String[PROJECT_LENGTH];
  static Date[] pStartDate = new Date[PROJECT_LENGTH];
  static Date[] pEndDate = new Date[PROJECT_LENGTH];
  static String[] pOwner = new String[PROJECT_LENGTH];
  static String[] pMembers = new String[PROJECT_LENGTH];
  static int pSize=0;

  static final int TASK_LENGTH = 100;
  static int[] tNo = new int[TASK_LENGTH];
  static String[] tContent = new String[TASK_LENGTH];
  static Date[] tDeadLine = new Date[TASK_LENGTH];
  static int[] tStatus = new int[TASK_LENGTH];
  static String[] tOwner = new String[TASK_LENGTH];
  static int tSize = 0;

  public static void main(String[] args) {

    while (true) {
      String input = prompt("명령> ");

      if (input.equals("exit") || input.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else if (input.equals("/member/add")) {
        System.out.println("[회원등록]");
        addMember();

      } else if (input.equals("/member/list")) {
        System.out.println("[회원목록]");
        listMembers();

      }else if (input.equals("/project/add")) {
        System.out.println("[프로젝트 등록]");
        addProject();

      }
      else if(input.equals("/project/list")) {
        System.out.println("[프로젝트 목록]");
        listProjects();

      }else if (input.equals("/task/add")){
        System.out.println("[작업 등록]");
        addTask();

      }else if (input.equals("/task/list")) {
        System.out.println("[작업 목록]");
        listTasks();


      }else {
        System.out.println("실행할 수 없는 명령입니다. ");
      }
      System.out.println();
    }
    sc.close();
  }

  static String prompt(String title) {
    System.out.print(title);
    String input = sc.nextLine();
    return input;
  }


  static void addMember() {

    System.out.print("번호? ");
    no[size] = Integer.parseInt(sc.nextLine());
    System.out.print("이름? ");
    name[size] = sc.nextLine();
    System.out.print("이메일? ");
    email[size] = sc.nextLine();
    System.out.print("암호? ");
    password[size] = sc.nextLine();
    System.out.print("사진? ");
    photo[size] = sc.nextLine();
    System.out.print("전화? ");
    tel[size] = sc.nextLine();

    registeredDate[size] = new Date(System.currentTimeMillis());
    size++;


  }

  static void listMembers() {
    for(int i = 0; i < size;i++ ) {
      System.out.printf("%d, %s, %s, %s, %tY-%5$tm-%5$td\n",
          no[i], name[i], email[i], tel[i], registeredDate[i] );
    }
  }

  static void addProject() {
    System.out.print("번호? ");
    pNo[pSize] = Integer.parseInt(sc.nextLine());

    System.out.print("프로젝트명? ");
    pTitle[pSize] = sc.nextLine();
    System.out.print("내용? ");
    pContent[pSize] = sc.nextLine();
    System.out.print("시작일? " );
    pStartDate[pSize] = Date.valueOf(sc.nextLine());
    System.out.print("종료일? " );
    pEndDate[pSize] = Date.valueOf(sc.nextLine());
    System.out.print("만든이? " );
    pOwner[pSize] = sc.nextLine();
    System.out.print("팀원: ");
    pMembers[pSize] = sc.nextLine();
    pSize++;
  }

  static void listProjects() {
    for (int i = 0; i < pSize; i++) {
      System.out.printf("%d, %s, %tY-%3$tm-%3$td, %tY-%4$tm-%4$td, %s\n",
          pNo[i], pTitle[i], pStartDate[i], pEndDate[i],pOwner[i]);
    }
  }

  static void addTask() {
    System.out.print("번호? ");
    tNo[tSize] = Integer.parseInt(sc.nextLine());
    System.out.print("내용? ");
    tContent[tSize] = sc.nextLine();
    System.out.print("완료일? ");
    tDeadLine[tSize] = Date.valueOf(sc.nextLine());
    System.out.print("상태? ");
    System.out.println("0 : 신규");
    System.out.println("1: 진행중");
    System.out.println("2: 완료");
    System.out.print("> ");
    tStatus[tSize] = Integer.parseInt(sc.nextLine());
    System.out.print("담당자? ");
    tOwner[tSize] = sc.nextLine();
    tSize++;

  }

  static void listTasks() {
    for(int i =0; i <tSize; i++) {  
      String stateLabel = null;
      switch(tStatus[i]) {
        case 1 :
          stateLabel = "상태: 진행중";
          break;
        case 2 :
          stateLabel = "상태: 완료";
          break;
        default:
          stateLabel = "상태: 신규";
      }
      System.out.printf("%d, %s, %s, %s, %s\n"
          , tNo[i], tContent[i], tDeadLine[i], stateLabel, tOwner[i]) ;
    }

  }

}
