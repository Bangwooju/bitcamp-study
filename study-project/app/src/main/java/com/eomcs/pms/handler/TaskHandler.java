package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskHandler {

  public static final int LENGTH = 100;
  public static int size = 0;
  public static Task[] tasks = new Task[LENGTH];

  public static void add() {
    System.out.println("[작업 등록]");


    Task task = new Task();

    task.no = Prompt.inputInt("번호? ");
    task.content= Prompt.inputString("내용? ");
    task.deadline= Prompt.inputDate("마감일? ");

    System.out.println("상태?");
    System.out.println("0: 신규");
    System.out.println("1: 진행중");
    System.out.println("2: 완료");
    task.status= Prompt.inputInt("> ");


    String owner = Prompt.inputString("만든이? > (취소: 빈문자열 출력)");
    while (true) {
      if(MemberHandler.exist(owner)) {
        task.owner = owner;
        break;
      }else if(owner.equals("")){
        System.out.println("작업 등록을 취소합니다.");
        return;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }

    tasks[size++] = task;
  }

  public static void list() {
    System.out.println("[작업 목록]");

    for (int i = 0; i < size; i++) {
      String stateLabel = null;
      switch (tasks[i].status) {
        case 1:
          stateLabel = "진행중";
          break;
        case 2:
          stateLabel = "완료";
          break;
        default:
          stateLabel = "신규";
      }
      // 번호, 작업명, 마감일, 프로젝트, 상태, 담당자
      System.out.printf("%d, %s, %s, %s, %s\n", // 출력 형식 지정
          tasks[i].no, tasks[i].content, tasks[i].deadline, stateLabel, tasks[i].owner);
    }
  }
}