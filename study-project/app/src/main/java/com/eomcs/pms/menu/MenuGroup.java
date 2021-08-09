package com.eomcs.pms.menu;

public class MenuGroup extends Menu {

  String prevMenutitle = "이전 메뉴";
  boolean disablePrevMenu;
  Menu[] childs = new Menu[100];
  int size;


  public MenuGroup(String title) {
    super(title);
  }

  public MenuGroup(String title, boolean disablePrevMenu) {
    super(title);
    this.disablePrevMenu = disablePrevMenu;
  }

  public void add(Menu child) {
    if(this.size == childs.length) {
      return;
    }
    childs[size++] = child;
  }



  public Menu remove(Menu child) {
    int index = indexOf(child);
    if(index == -1) {
      return null;
    }
    for(int i = index + 1; i < size ; i++) {
      childs[i -1 ] = childs[i];
    }
    childs[--size] = null;
    return child;
  }

  private int indexOf(Menu child) {
    for(int i = 0; i < this.size; i++) {
      if( child == this.childs[i] ) {
        return i;
      }
    }
    return -1;
  }


  public Menu getMenu(Menu child) {
    for(int i = 0; i < this.size; i++) {
      if( child == this.childs[i] ) {
        return this.childs[i]; 
      }
    }
    return null;
  }

  @Override
  public void execute() {

    System.out.printf("[%s]", title);
    while(true) {




    }


  }











}
