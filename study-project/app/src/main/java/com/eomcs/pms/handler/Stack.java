package com.eomcs.pms.handler;

public class Stack extends ArrayList{

  public void push(Object item) {
    this.add(item);
  }

  public void pop(){
    this.remove(this.size() - 1);
  }

}
