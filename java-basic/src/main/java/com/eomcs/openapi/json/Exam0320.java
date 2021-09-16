// JSON 문자열 --> 객체 : 컬렉션 다루기

package com.eomcs.openapi.json;

import java.lang.reflect.Type;
import java.util.Collection;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Exam0320 {

  public static void main(String[] args) {



    String jsonStr = "[{\"no\":101,\"name\":\"홍길동\"},{\"no\":102,\"name\":\"임꺽정\"},{\"no\":103,\"name\":\"안창호\"}]\r\n";

    // 컬렉션의 타입을 설정한다.
    //    Type collectionType = new TypeToken<Collection<Integer>>(){}.getType();
    // 위 문장을 풀어서 보면, 
    // TypeToken 클래스의 서브 클래스를 만든다.
    class MyTypeToken extends TypeToken<Collection<Member>> {
      // 수퍼 클래스를 지정할 때 제네릭 타입을 설정한다.
      // TypeToken 클레스에는 Type 인터페이스의 구현체를 만드는 메서드가 있기 때문에
      // 이 클래스의 서브 클래스를 만드는 것이다.
      // 타입 파라미터에 컬렉션 타입을 전달하는 목적 이외에는 다른 이유가 없다.
      // 그래서 서브 클래스에 뭔가를 추가할 필요는 없다.
    }

    // TypeToken 객체 준비
    MyTypeToken typeToken = new MyTypeToken();
    // TypeToken 객체를 통해 Type 구현체를 만든다.
    Type collectionType = typeToken.getType();

    // Type 객체에 저장된 정보를 바탕으로 JSON 문자열로부터 컬렉션 객체를 만든다.
    Collection<Member> list = new Gson().fromJson(jsonStr, collectionType);
    for(Member m : list) {
      System.out.println(m);
    }

  }  


}
