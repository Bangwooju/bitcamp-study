package com.eomcs.pms.handler;

import java.sql.Date;
import com.eomcs.pms.Member;
import com.eomcs.util.Prompt;

public class MemberHandler {

  public static final int LENGTH = 100;
  public static Member[] members = new Member[LENGTH];

  public static int size = 0;

  public static void add() {
    System.out.println("[회원 등록]");

    Member member = new Member();

    member.no = Prompt.inputInt("번호? ");
    member.name = Prompt.inputString("이름? ");
    member.email = Prompt.inputString("이메일? ");
    member.password = Prompt.inputString("암호? ");
    member.photo = Prompt.inputString("사진? ");
    member.tel = Prompt.inputString("전화? ");
    member.registeredDate = new Date(System.currentTimeMillis());

    members[size++] = member;
  }

  public static void list() {
    System.out.println("[회원 목록]");
    for (int i = 0; i < size; i++) {
      // 번호, 이름, 이메일, 전화, 가입일
      System.out.printf("%d, %s, %s, %s, %s\n", // 출력 형식 지정
          members[i].no, members[i].name, members[i].email, members[i].tel, members[i].registeredDate);
    }
  }

  public static boolean exist(String name) {
    for( int i =0; i <size; i++) {
      if(members[i].name.equals(name)) {
        return true;
      }
    }return false;

  }



}
