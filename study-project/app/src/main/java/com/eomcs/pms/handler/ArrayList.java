package com.eomcs.pms.handler;

public class ArrayList {

  static final int MAX_LENGTH = 5;
  Object[] object = new Object[MAX_LENGTH];
  int size = 0;

  public void add(Object obj) {

    if(size == object.length) {
      Object[] arr = new Object[object.length + (object.length >> 1)];
      for(int i = 0 ; i < size ; i ++) {
        arr[i] = object[i];
      }
      object = arr;
    }

    this.object[this.size++] = obj;
  }

  public Object[] toArray() {

    Object[] arr = new Object[this.size];

    for(int i = 0; i < size; i++) {
      arr[i] = object[i];
    }
    return arr;
  }


  public boolean remove(Object object) {
    int index = indexOf(object);
    if( index == -1) {
      return false;
    }
    for (int i = index + 1; i < this.size; i++) {
      this.object[i - 1] = this.object[i];
    }
    this.object[--this.size] = null;
    return true;
  }


  private int indexOf(Object object) {
    for (int i = 0; i < this.size; i++) {
      if (this.object[i] == object) {
        return i;
      }
    }
    return -1;
  }




}
