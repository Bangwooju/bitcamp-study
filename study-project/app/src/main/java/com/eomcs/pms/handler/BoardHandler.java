package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardHandler {

  // 모든 게시판의 최대 배열 개수가 같기 때문에 다음 변수는 
  // 그냥 static 필드로 남겨둔다.
  static final int MAX_LENGTH = 5;
  Board[] boards = new Board[MAX_LENGTH];
  int size = 0;

  public  void add() {
    System.out.println("[새 게시글]");

    Board board = new Board();

    board.no = Prompt.inputInt("번호? ");
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer = Prompt.inputString("작성자? ");
    board.registeredDate = new Date(System.currentTimeMillis());
    //    board.viewCount = 0; // 인스턴스 변수는 생성되는 순간 기본 값이 0으로 설정된다.

    this.boards[this.size++] = board;
  }

  public void list() {
    System.out.println("[게시글 목록]");
    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          this.boards[i].no, 
          this.boards[i].title, 
          this.boards[i].writer,
          this.boards[i].registeredDate,
          this.boards[i].viewCount, 
          this.boards[i].like);
    }
  }

  public void detail() {
    System.out.println("[게시글 상세보기]");
    int no = Prompt.inputInt("번호? ");
    Board board = null;

    for (int i =0; i < this.size; i ++) {
      if( boards[i].no == no ) {
        board = boards[i];
        break;
      }
    }
    if (board ==null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    System.out.println("제목: " + board.title);
    System.out.println("내용: " + board.content);
    System.out.println("작성자: " + board.writer);
    System.out.println("등록일: " + board.registeredDate);
    System.out.println("조회수: " + ++board.viewCount);
  }

  public void update() {
    System.out.println("[게시글 변경]");
    int no = Prompt.inputInt("번호? ");
    Board board = null;

    for (int i =0; i < this.size; i ++) {
      if( boards[i].no == no ) {
        board = boards[i];
        break;
      }
    }
    if (board ==null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }
    String label = String.format("제목 (%s)? " + board.title);
    String title = Prompt.inputString(label);

    label = String.format("내용 (%s)? " + board.content);
    String content = Prompt.inputString(label);

    String input = Prompt.inputString("정말 변경하시겠습니까? (y/N");
    if(input.equalsIgnoreCase("N") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }
    board.title = title;
    board.content = content;
    System.out.println("게시글을 변경하였습니다.");
  }

}










