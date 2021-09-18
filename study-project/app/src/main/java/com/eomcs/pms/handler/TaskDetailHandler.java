package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.domain.Task;
import com.eomcs.util.Prompt;

public class TaskDetailHandler extends AbstractTaskHandler {

  public TaskDetailHandler(ProjectPrompt projectPrompt) {
    super(projectPrompt);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[작업 상세보기]");

    Project project = projectPrompt.promptProject();
    if (project == null) {
      System.out.println("작업 조회를 취소합니다.");
      return;
    }

    printTasks(project);

    System.out.println("-------------------------------------");

    int taskNo = Prompt.inputInt("작업 번호? ");

    Task task = project.findTaskByNo(taskNo);
    if (task == null) {
      System.out.println("해당 번호의 작업이 없습니다.");
      return;
    }

    System.out.printf("내용: %s\n", task.getContent());
    System.out.printf("마감일: %s\n", task.getDeadline());
    System.out.printf("상태: %s\n", getStatusLabel(task.getStatus()));
    System.out.printf("담당자: %s\n", task.getOwner().getName());

    Member loginUser = AuthLoginHandler.getLoginUser();
    if(loginUser == null || task.getOwner().getNo() != loginUser.getNo() && 
        !loginUser.getEmail().equals("root@test.com")) {
      return;
    }

    request.setAttribute("project", project);
    request.setAttribute("task", task);

    while(true) {
      String input = Prompt.inputString("변경(U), 삭제(D), 이전메뉴(0)");
      switch(input) {
        case "u" :
        case "U" : 
          request.getRequestDispatcher("/task/update").forword(request);
          return;
        case "d" :
        case "D" :
          request.getRequestDispatcher("/task/delete").forword(request);
          return;
        case "0" :
          return;
        default :
          System.out.println("명령어가 올바르지 않습니다!");

      }
    }
  }
}





