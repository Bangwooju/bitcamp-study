package com.eomcs.basic.ex10;

//import java.util.Date;


public class Exam0110 {
  public static void main(String[] args) {
    long millis = System.currentTimeMillis();
    System.out.println(millis);

    java.util.Date d = new java.sql.Date(millis); //sql은 util 보다 업그레이드 된 버전이다.

    java.util.Date d2 = new java.util.Date(millis);
    System.out.println(d.toString()); // toString: 정해진 문자열로 리턴해주는 기능
    System.out.println(d2.toString());

    //여기서 Object (객체)는 new이하를 통해 만들어낸 주소값.
    //d, d2는 리모콘의 역할을 해 원하는 기능(toString)을 담도록 한다.
  }
}
