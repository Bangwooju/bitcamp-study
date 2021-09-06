package com.eomcs.menu;

public abstract class Menu {

  public static final int ACCESS_LOGOUT = 0x01;
  public static final int ACCESS_GENERAL= 0x04;
  public static final int ACCESS_ADMIN = 0x02;

  String title;

  int accessScope;

  public Menu(String title) {
    this(title, ACCESS_ADMIN | ACCESS_LOGOUT | ACCESS_GENERAL);
  }

  public Menu(String title, int accessScope) {
    this.title = title;
    this.accessScope = accessScope;
  }

  public abstract void execute();
}
