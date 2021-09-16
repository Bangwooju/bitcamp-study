// 객체 --> JSON 문자열 : 다른 객체를 포함하는 경우

package com.eomcs.openapi.json;

import java.sql.Date;
import com.google.gson.Gson;

public class Exam0410 {

  public static void main(String[] args) {

    // 객체 준비
    Member member = new Member();
    member.setNo(100);
    member.setName("홍길동");
    member.setEmail("hong@test.com");
    member.setPassword("1111");
    member.setPhoto("hong.gif");
    member.setTel("010-1111-1111");
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    Board b = new Board();
    b.setNo(1);
    b.setTitle("제목");
    b.setContent("내용");
    b.setWriter(member);
    b.setViewCount(98);
    b.setLike(5);
    b.setRegisteredDate(new Date(System.currentTimeMillis()));

    // 객체의 값을 JSON 문자열로 얻기
    String jsonStr = new Gson().toJson(b);
    System.out.println(jsonStr);
  }  


}

// 다른 객체를 포함했을 때 JSON 형식 - 

//   {프로퍼티명 : 값, 
//  프로퍼티명 : {프로퍼티명:값, 프로퍼티명:값,...}

// 값 : 
// - 문자열 => "값"
// - 숫자 => 값
// - 논리 => true, false

// 프로퍼티명은 반드시 문자열로 표현해야한다.



