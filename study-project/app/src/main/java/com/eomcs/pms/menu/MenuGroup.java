package com.eomcs.pms.menu;

import com.eomcs.util.Prompt;

public class MenuGroup extends Menu{

  Menu[] childs = new Menu[100];
  int size;
  String prevMenuTitle = "이전 메뉴";
  boolean disablePreMenu;

  public MenuGroup(String title) {
    super(title);
  }

  public void add(Menu child) {
    if(this.childs.length == this.size) {
      return;
    }
    this.childs[this.size++] = child; 
  }


  public Menu remove(Menu child) {
    int index = indexOf(child);
    if(index == -1) {
      return null;
    }
    for(int i = index + 1; i < this.size; i++) {
      this.childs[i-1] = this.childs[i];
    }
    this.childs[--this.size] = null;
    return child;
  }


  public int indexOf(Menu child) {
    for(int i = 0; i < this.size; i++) {
      if(this.childs[i] == child) {
        return i;
      }
    }
    return -1;
  }

  public Menu getMenu(String title) {
    for(int i = 0; i < this.size; i++) {
      if(this.childs[i].title.equals(title)) {
        return this.childs[i];
      }
    }
    return null;
  }


  @Override
  public void execute() {

    while(true) {
      System.out.printf("[%s]\n", this.title);
      for(int i = 0; i < this.size; i++) {
        System.out.printf("%d, %s\n", i+1, this.childs[i].title);
      }
      if(!disablePreMenu) {
        System.out.printf("0. %s\n", this.prevMenuTitle);
      }
      int menuNo = Prompt.inputInt("선택> ");
      if(menuNo == 0 && !disablePreMenu) {
        return;
      }
      if(menuNo < 0 || menuNo > this.size) {
        System.out.println("무효한 메뉴 번호입니다.");
        continue;
      }
      this.childs[menuNo -1].execute();
    }
  }

  public void setPrevMenuTitle(String prevMenuTitle) {
    this.prevMenuTitle = prevMenuTitle;
  }

}
