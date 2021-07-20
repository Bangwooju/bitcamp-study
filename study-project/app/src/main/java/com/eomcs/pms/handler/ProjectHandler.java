package com.eomcs.pms.handler;

import com.eomcs.pms.Project;
import com.eomcs.util.Prompt;

public class ProjectHandler {

  // 프로젝트 정보
  public static final int LENGTH = 1000;
  public static Project[] projects = new Project[LENGTH];

  public static int size = 0;


  public static void add() {
    System.out.println("[프로젝트 등록]");

    Project project = new Project();

    project.no = Prompt.inputInt("번호? ");
    project.title = Prompt.inputString("프로젝트명? ");
    project.content= Prompt.inputString("내용? ");
    project.startDate= Prompt.inputDate("시작일? ");
    project.endDate= Prompt.inputDate("종료일? ");

    String owner = Prompt.inputString("만든이? > (취소: 빈문자열 출력)");
    while (true) {
      if(MemberHandler.exist(owner)) {
        project.owner = owner;
        break;
      }else if(owner.equals("")){
        System.out.println("프로젝트 등록을 취소합니다.");
        return;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }

    String members = "";
    while(true) {
      String member = Prompt.inputString("팀원? (완료: 빈문자열 출력)");
      if(MemberHandler.exist(members)) {
        if(member.length() > 0) {
          members += ",";
        }
        members += member;
        continue;
      }else if(member.equals("")) {
        break;
      }
      System.out.println("등록된 회원이 아닙니다.");
    }

    project.members = members;

    projects[size++] = project;
  }

  public static void list() {
    System.out.println("[프로젝트 목록]");
    for (int i = 0; i < size; i++) {
      // 번호, 프로젝트명, 시작일, 종료일, 만든이
      System.out.printf("%d, %s, %s, %s, %s, [%s]\n", // 출력 형식 지정
          projects[i].no, projects[i].title, projects[i].startDate,
          projects[i].endDate, projects[i].owner, projects[i].members);
    }
  }

}
