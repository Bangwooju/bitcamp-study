package com.eomcs.util;

import java.lang.reflect.Array;

public class ArrayList<E> extends AbstractList<E> {

  static final int MAX_LENGTH = 5;

  Object[] list = new Object[MAX_LENGTH];

  @Override
  public void add(E obj) {
    if (size == list.length) {
      Object[] arr = new Object[list.length + (list.length >> 1)];
      for (int i = 0; i < size; i++) {
        arr[i] = list[i];
      }
      list = arr;
    }
    this.list[this.size++] = obj;
  }

  @Override
  public Object[] toArray() {
    Object[] arr = new Object[this.size]; // 배열에 저장된 값을 담을 정도의 크기를 가진 새 배열을 만든다.
    for (int i = 0; i < this.size; i++) { // 배열에 저장된 값을 새 배열에 복사한다.
      arr[i] = list[i];
    }
    return arr; // 새 배열을 리턴한다.
  }

  @Override
  public boolean remove(Object obj) {
    int index = indexOf(obj);
    if (index == -1) {
      return false;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.list[i - 1] = this.list[i];
    }
    this.list[--this.size] = null;

    return true;
  }


  private int indexOf(Object obj) {
    for (int i = 0; i < this.size; i++) {
      if (this.list[i] == obj) {
        return i;
      }
    }
    return -1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) {
    if (index < 0 || index >= this.size) { // 유효하지 않은 인덱스인 경우
      return null;
    }
    return (E) this.list[index];
  }

  @Override
  public E remove(int index) {

    if (index < 0 || index >= this.size) { // 인덱스가 무효하다면
      return null;
    }

    @SuppressWarnings("unchecked")
    E deleted = (E) this.list[index]; // 삭제할 항목 저장

    for (int i = index + 1; i < this.size; i++) {
      this.list[i - 1] = this.list[i];
    }
    this.list[--this.size] = null;

    return deleted; // 삭제한 항목 리턴
  }


  @SuppressWarnings("unchecked")
  @Override
  public Object[] toArray(Object[] arr) {

    if(arr.length >= this.size) {

      System.arraycopy(this.list, 0, arr, 0, this.size);
      return arr;
    } else {

      E[] temp = (E[]) Array.newInstance(arr.getClass().getComponentType(), this.size);
      System.arraycopy(this.list , 0, temp, 0, this.size);
      return temp;
    }
  }

}








