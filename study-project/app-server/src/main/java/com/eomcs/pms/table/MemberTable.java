package com.eomcs.pms.table;

import com.eomcs.pms.domain.Member;
import com.eomcs.pms.server.DataProcessor;
import com.eomcs.pms.server.Request;
import com.eomcs.pms.server.Response;

// 역할
// 회원 데이터를 저장하고 조회하는 일을 한다.
// 회원 데이터를 파일에 저장하고 파일로부터 로딩하는 일을 한다.
public class MemberTable extends JsonDataTable<Member> implements DataProcessor{

  public MemberTable() {
    super("memeber.json", Member.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch(request.getCommand()) {
      case "member.insert" : insert(request, response); break;
      case "member.selectList" : selectList(request, response); break;
      case "member.selectOne" : selectOne(request, response); break;
      case "member.selectOneByEmailPassword" : selectOneByEmailPassword(request, response); break;
      case "member.update" : update(request, response); break;
      case "member.delete" : delete(request, response); break;
      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }



  private void selectOneByEmailPassword(Request request, Response response) {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    Member member = null;
    for(Member m : list) {
      if(m.getEmail().equals(email) && m.getPassword().equals(password)) {
        member = m;
        break;
      }
    }
    if(member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
    }
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void insert(Request request, Response response) throws Exception {

    Member member = request.getObject(Member.class);
    list.add(member);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.valueOf(request.getParameter("no"));
    Member member = findByNo(no);
    if(member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) {
    Member member = request.getObject(Member.class);
    int index = indexOf(member.getNo());
    if(index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }

    list.set(index, member);
    response.setStatus(Response.SUCCESS);
  }

  private Member findByNo(int no) {
    for(Member m : list) {
      if(m.getNo() == no) {
        return m;
      }
    }
    return null;
  }


  private int indexOf(int memberNo) {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

}
