package com.eomcs.pms.menu;

public abstract class Menu {

  String title;

  // 메뉴 이름없이 인스턴스를 생성할 수 없도록 
  // 기본 생성자를 정의하지 않는다.
  // 대신 인스턴스를 만들 때 반드시 메뉴 이름을 입력하도록 강요하기 위해
  // 다음과 같이 String 을 파라미터로 받는 생성자를 정의한다.
  public Menu(String title) {
    this.title = title;
  }

  public abstract void execute();

}
