// 객체 --> JSON 문자열 : 객체의 필드 값을 json 형식의 문자열로 만들기

package com.eomcs.openapi.json;

import java.sql.Date;
import com.google.gson.Gson;

public class Exam0110 {

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


    Gson gson = new Gson();
    // 객체의 값을 JSON 문자열로 얻기
    String jsonStr = gson.toJson(member);
    System.out.println(jsonStr);
  }  


}

// JSON 객체 형식 - {객체 정보}
//   {"프로퍼티명" : 값, "프로퍼티명" : 값, ...}

// 값 : 
// - 문자열 => "값"
// - 숫자 => 값
// - 논리 => true, false

// 프로퍼티명은 반드시 문자열로 표현해야한다.



