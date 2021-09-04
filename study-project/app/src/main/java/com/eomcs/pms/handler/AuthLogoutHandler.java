package com.eomcs.pms.handler;

import java.util.List;
import com.eomcs.pms.domain.Member;

public class AuthLogoutHandler implements Command {

  List<Member> memberList;

  static Member loginUser;
  public static Member getLoginUser() {
    return loginUser;
  }

  public AuthLogoutHandler(List<Member> memberList) {
    this.memberList = memberList;
  }

  public void execute() {
    System.out.println("[로그아웃]");

    loginUser = null;
    System.out.println("로그아웃 하였습니다.");
  }

}







