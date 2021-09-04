package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Project;

public class TaskListHandler extends AbstractTaskHandler {

  public TaskListHandler(ProjectListHandler projectHandler) {
    super(projectHandler);
  }

  public void execute() {
    System.out.println("[작업 목록]");

    Project project = projectHandler.promptProject();
    if (project == null) {
      System.out.println("작업 등록을 취소합니다.");
      return;
    }

    printTasks(project);
  }

}





