package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardHandler {


  public static final int LENGTH = 100;

  Board[] boards= new Board[LENGTH];
  int size = 0;

  public static void add(BoardHandler that) {
    System.out.println("[새 게시글]");

    Board board = new Board();

    board.no = Prompt.inputInt("번호? ");
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer = Prompt.inputString("작성자? ");
    board.registeredDate = new Date(System.currentTimeMillis());

    System.out.println(" 게시글을 등록하였습니다. ");

    that.boards[that.size++] = board;
  }

  public static void list(BoardHandler that) {
    System.out.println("[게시글 목록]");
    for (int i = 0; i < that.size; i++) {
      // 번호, 이름, 이메일, 전화, 가입일
      System.out.printf("%d, %s, %s, %s, %s\n", // 출력 형식 지정
          that.boards[i].no, that.boards[i].title, that.boards[i].writer, 
          that.boards[i].registeredDate, that.boards[i].viewCount);
    }
  }
}
