package com.eomcs.pms.table;

import java.util.ArrayList;
import java.util.List;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.server.Request;
import com.eomcs.pms.server.Response;

// 역할
// 회원 데이터를 저장하고 조회하는 일을 한다.
// 회원 데이터를 파일에 저장하고 파일로부터 로딩하는 일을 한다.
public class MemberTable {

  List<Member> list = new ArrayList<>();

  public void execute(Request request, Response response) throws Exception {
    switch(request.getCommand()) {
      case "member.insert" : insert(request, response); break;
      case "member.selectList" : selectList(request, response); break;
      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {

    Member member = request.getValue(Member.class);
    list.add(member);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }


  //  @SuppressWarnings("unchecked")
  //  private void selectOne(Request request, Response response) throws Exception {
  //    Map<String, String> params = request.getValue(Map.class);
  //    int no = Integer.valueOf(params.get("no"));
  //    Board board = findByNo(no);
  //    if(board != null) {
  //      response.setStatus(Response.SUCCESS);
  //      response.setValue(board);
  //    } else {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 게시글이 없습니다.");
  //    }
  ////
  ////  }
  //
  //  private Board findByNo(int no) {
  //    for(Board b : list) {
  //      if(b.getNo() == no) {
  //        return b;
  //      }
  //    }
  //    return null;
  //  }

}
