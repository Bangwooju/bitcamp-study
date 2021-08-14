package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Member;

public class MemberList extends ArrayList {

  public Member findByNo(int no) {
    Object[] list = toArray();

    for (Object object : list) {
      Member member = (Member)object;

      if (member.no == no) {
        return member;
      }
    }
    return null;
  }


  public boolean exist(String name) {
    Object[] list = toArray();

    for (Object object : list) {
      Member member = (Member)object;
      if (member.name.equals(name)) {
        return true;
      }
    }
    return false;
  }
}








