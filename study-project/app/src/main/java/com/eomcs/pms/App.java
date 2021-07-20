package com.eomcs.pms;

import com.eomcs.pms.handler.BoardHandler;
import com.eomcs.pms.handler.MemberHandler;
import com.eomcs.pms.handler.ProjectHandler;
import com.eomcs.pms.handler.TaskHandler;
import com.eomcs.util.Prompt;

public class App {

  public static void main(String[] args) {

    while (true) {

      String input = Prompt.inputString("명령> ");

      BoardHandler boardhandler = new BoardHandler();
      BoardHandler boardhandler2 = new BoardHandler();
      BoardHandler boardhandler3 = new BoardHandler();

      if (input.equals("exit") || input.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else if (input.equals("/member/add")) {
        MemberHandler.add();

      } else if (input.equals("/member/list")) {
        MemberHandler.list();

      }  else if (input.equals("/project/add")) {
        ProjectHandler.add();

      }  else if (input.equals("/project/list")) {
        ProjectHandler.list();

      }  else if (input.equals("/task/add")) {
        TaskHandler.add();

      }  else if (input.equals("/task/list")) {
        TaskHandler.list();

      } else if (input.equals("/board/add")) {
        BoardHandler.add(boardhandler);

      }  else if (input.equals("/board/list")) {
        BoardHandler.list(boardhandler);

      }else if (input.equals("/board2/add")) {
        BoardHandler.add(boardhandler2);

      }  else if (input.equals("/board2/list")) {
        BoardHandler.list(boardhandler2);

      }else if (input.equals("/board3/add")) {
        BoardHandler.add(boardhandler3);

      }  else if (input.equals("/board3/list")) {
        BoardHandler.list(boardhandler3);

      }else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
      System.out.println();
    }

    Prompt.close();
  }








}












