// JSON 문자열 --> 객체 : JSON 문자열을 해석하여 객체를 생성하기

package com.eomcs.openapi.json;

import com.google.gson.Gson;

public class Exam0220 {

  public static void main(String[] args) {



    String jsonStr = "[{\"no\":101,\"name\":\"홍길동\"},{\"no\":102,\"name\":\"임꺽정\"},{\"no\":103,\"name\":\"안창호\"}]\r\n";

    // JSON 처리 객체 준비
    // JSON 문자열을 가지고 객체 만들기
    Member[] members = new Gson().fromJson(jsonStr, Member[].class);

    for(Member m : members) {
      System.out.println(m);
    }

  }  


}
