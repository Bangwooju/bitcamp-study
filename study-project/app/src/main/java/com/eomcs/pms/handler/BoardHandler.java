package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.domain.Board;
import com.eomcs.util.Prompt;

public class BoardHandler {
  static final int MAX_LENGTH = 5;
  Board[] boards = new Board[MAX_LENGTH];
  int size = 0;

  public void add() {
    System.out.println("[새 게시글]");

    Board board = new Board();

    board.no = Prompt.inputInt("번호? ");
    board.title = Prompt.inputString("제목? ");
    board.content = Prompt.inputString("내용? ");
    board.writer = Prompt.inputString("작성자? ");
    board.registeredDate = new Date(System.currentTimeMillis());

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
    for (int i = 0;i <this.size; i++) {
      if( boards[i].no == no ) {
        System.out.printf("제목: %s\n" , boards[i].title);
        System.out.printf("내용: %s\n" ,boards[i].content);
        System.out.printf("작성자: %s\n", boards[i].writer);
        System.out.printf("등록일: %s\n", boards[i].registeredDate);
        System.out.printf("조회수: %d\n", ++boards[i].viewCount);
        break;
      }
      System.out.println("해당 번호의 게시글이 없습니다.");
    }
  }

  public void update() {
    System.out.println("[게시글 변경]");
    int no = Prompt.inputInt("번호? ");

    Board board = null;

    for (int i = 0; i < this.size; i++) {
      if (boards[i].no == no) {
        board = boards[i];
        break;
      }
    }

    if (board == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", board.title));
    String content = Prompt.inputString(String.format("내용(%s)? ", board.content));

    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 변경을 취소하였습니다.");
      return;
    }

    board.title = title;
    board.content = content;
    System.out.println("게시글을 변경하였습니다.");
  }

  public void delete () {
    System.out.println("[게시글 삭제]");
    int no = Prompt.inputInt("번호? ");
    int boardIndex = -1;

    // Board 인스턴스가 들어있는 배열을 뒤져서 게시글 번호와 일치하는 Board의 인스턴스를
    // 찾는다.
    for(int i =0; i < this.size; i++) {
      if(this.boards[i].no == no) {
        boardIndex = i;
        break;
      }
    }

    if (boardIndex == -1) {
      System.out.println("해당 번호의 게시글은 없습니다.");
      return;
    }
    String input = Prompt.inputString("정말 삭제하시겠습니까? ( y/N )");
    if (input.equalsIgnoreCase("n")||(input.equals(""))) {        
      System.out.println("게시글 삭제를 취소하였습니다");
      return;
    }
    for(int i = boardIndex + 1 ; i < this.size; i++) {
      this.boards[i -1] = this.boards[i ];
    }
    this.boards[--this.size] =null;
    System.out.println("게시글을 삭제하였습니다.");
  }
}







