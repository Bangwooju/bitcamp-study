package com.eomcs.pms.handler;

public class ArrayList {


  static final int MAX_LENGTH = 5;
  Object[] objects = new Object[MAX_LENGTH];
  int size = 0;

  public void add(Object object) {
    if (size == objects.length) {
      Object[] arr = new Object[objects.length + (objects.length >> 1)];
      for (int i = 0; i < size; i++) {
        arr[i] = objects[i];
      }
      objects = arr; // boards에 저장된 옛날 배열 주소를 버리고 새 배열 주소를 저장한다.
    }
    this.objects[this.size++] = objects;
  }

  public Object[] toArray() {
    Object[] arr = new Object[this.size]; // 배열에 저장된 값을 담을 정도의 크기를 가진 새 배열을 만든다.
    for (int i = 0; i < this.size; i++) { // 배열에 저장된 값을 새 배열에 복사한다.
      arr[i] = objects[i];
    }
    return arr; // 새 배열을 리턴한다.
  }

  public boolean remove(Object object) {
    int index = indexOf(object);
    if (index == -1) {
      return false;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.objects[i - 1] = this.objects[i];
    }
    this.objects[--this.size] = null;

    return true;
  }

  private int indexOf(Object object) {
    for (int i = 0; i < this.size; i++) {
      if (this.objects[i] == object) {
        return i;
      }
    }
    return -1;
  }



}
