package com.eomcs.pms.menu;

import com.eomcs.util.Prompt;

public class MenuGroup extends Menu{

  int size;
  String prevMenuTitle = "이전 메뉴";

  boolean disablePrevMenu;
  Menu[] childs = new Menu[100];


  public void setPrevMenuTitle(String prevMenuTitle) {
    this.prevMenuTitle = prevMenuTitle;
  }


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
    for (int i = index + 1; i < this.size; i++) {
      childs[i-1] = childs[i];
    }
    childs[--this.size] = null;
    return child;
  }

  public int indexOf(Menu child) {
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i] == child) {
        return i;
      }
    }
    return -1;
  }


  public Menu getMenu(String title) {
    for (int i = 0; i < this.size; i++) {
      if (this.childs[i].title.equals(title)) {
        return childs[i];
      }
    }
    return null;
  }

  public void execute() {
    System.out.printf("\n[%s]\n", this.title);

    for(int i =0 ; i < this.size; i++) {
      System.out.printf("%d. %s\n", i +1, this.childs[i].title);
    }

    if(!disablePrevMenu) {
      System.out.printf("0. %s", this.prevMenuTitle);
    }

    int menuNo = Prompt.inputInt("선택> ");
    if(menuNo == 0 && !disablePrevMenu) {
      return;
    } 

    if(menuNo < 0 && menuNo > this.size) {
      System.out.println("무효한 메뉴 번호입니다.");  
    }
    childs[menuNo -1 ].execute();

  }
}
