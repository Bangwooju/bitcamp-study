// JSON 문자열 --> 객체 : 컬렉션 다루기

package com.eomcs.openapi.json;

import java.lang.reflect.Type;
import java.util.Collection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Exam0322 {

  public static void main(String[] args) {

    String jsonStr = "[{\"no\":101,\"name\":\"홍길동\"},{\"no\":102,\"name\":\"임꺽정\"},{\"no\":103,\"name\":\"안창호\"}]\r\n";

    // Exam0321과 다른 방법으로 Type 객체를 얻기
    Type collectionType = TypeToken.getParameterized(Collection.class, Member.class).getType();

    // Type 객체에 저장된 정보를 바탕으로 JSON 문자열로부터 컬렉션 객체를 만든다.
    Collection<Member> list = new Gson().fromJson(jsonStr, collectionType);
    for(Member m : list) {
      System.out.println(m);
    }

  }  


}
