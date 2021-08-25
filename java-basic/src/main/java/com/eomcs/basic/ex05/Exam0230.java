// Deque의 Iterator와 for( : )의 차이점
package com.eomcs.basic.ex05;

import java.util.ArrayDeque;

public class Exam0230 {

  public static void main(String[] args) {



    ArrayDeque<String> stack = new ArrayDeque<>();

    // 다음과 같이 스택으로서 사용할 수 있다.
    // push() - 스택의 맨 마지막에 값을 추가한다.
    stack.push("aaa");
    stack.push("bbb");
    stack.push("ccc");

    // Iterator를 통해 데이터를 조회하고 싶다면,
    // 다음과 같이 for( : ) 문을 사용하는 것이 낫다.

    for(String s : stack) {
      System.out.println(s);
    }

  }

}

