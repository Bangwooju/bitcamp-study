package com.eomcs.basic.ex10;

//import java.util.Date;


public class Exam0120 {
  public static void main(String[] args) {
    java.util.Date d = new java.util.Date(); // Date 객체를 만드는 순간의 시각을 저장해둔다.
    System.out.println(d.toString()); // toString: 정해진 문자열로 리턴해주는 기능
    System.out.printf("%tY-%1$tm-%1$td\n", d);
    System.out.printf("%tY-%tm-%td", d,d,d);

    //여기서 Object (객체)는 new이하를 통해 만들어낸 주소값.
    //d, d2는 리모콘의 역할을 해 원하는 기능(toString)을 담도록 한다.

    //궁금증..1. Date는 close해주지 않아도 되는걸까; close 기능이 없음. Scanner는 close기능이 있음 닫기 해줘야함.

  }
}
