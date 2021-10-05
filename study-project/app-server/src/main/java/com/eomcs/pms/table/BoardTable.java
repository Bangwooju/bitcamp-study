package com.eomcs.pms.table;

import java.util.ArrayList;
import com.eomcs.pms.domain.Board;
import com.eomcs.server.DataProcessor;
import com.eomcs.server.Request;
import com.eomcs.server.Response;

// 역할
// 게시글 데이터를 저장하고 조회하는 일을 한다.
// 게시글 데이터를 파일에 저장하고 파일로부터 로딩하는 일을 한다.
public class BoardTable extends JsonDataTable<Board> implements DataProcessor {


  public BoardTable() {
    super("board.json", Board.class);
  }

  public void execute(Request request, Response response) throws Exception {
    switch(request.getCommand()) {
      case "board.insert" : insert(request, response); break;
      case "board.selectList" : selectList(request, response); break;
      case "board.selectOne" : selectOne(request, response); break;
      case "board.selectListByKeyword": selectListByKeyword(request, response); break;
      case "board.update" : update(request, response); break;
      case "board.delete" : delete(request, response); break;
      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void selectListByKeyword(Request request, Response response) {
    String keyword = request.getParameter("keyword");

    ArrayList<Board> searchResult = new ArrayList<>();
    for (Board board : list) {
      if (!board.getTitle().contains(keyword) &&
          !board.getContent().contains(keyword) &&
          !board.getWriter().getName().contains(keyword)) {
        continue;
      }
      searchResult.add(board);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);

  }

  private void delete(Request request, Response response) {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);

  }

  private void update(Request request, Response response) {
    Board board = request.getObject(Board.class);
    int index = indexOf(board.getNo());
    if(index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.set(index, board);
    response.setStatus(Response.SUCCESS);

  }

  private void selectList(Request request, Response response) {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void insert(Request request, Response response) throws Exception {
    Board board = request.getObject(Board.class);
    list.add(board);
    response.setStatus(Response.SUCCESS);
  }


  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Board b = findByNo(no);

    if (b != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(b);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
    }

  }

  private Board findByNo(int no) {
    for(Board b : list) {
      if(b.getNo() == no) {
        return b;
      }
    }
    return null;
  }

  private int indexOf(int boardNo) {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == boardNo) {
        return i;
      }
    }
    return -1;
  }

}