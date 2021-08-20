package com.eomcs.util;

import java.util.Arrays;

/// 일반 클래스에 제네릭을 적용하면,
// 최종적인 타입 이름은 그 클래스의 인스턴스를 생성할 때 전달한다.
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
  public boolean remove(E obj) {
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

  private int indexOf(E obj) {
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
    if(index < 0 || index >= this.size) {
      return null;
    }
    return (E)this.list[index];
  }

  @Override
  public E remove(int index) {
    if(index < 0 || index >= this.size) {
      return null;
    }

    @SuppressWarnings("unchecked")
    E delete = (E) this.list[index];
    for (int i = index + 1; i < this.size; i++) {
      this.list[i - 1] = this.list[i];
    }
    this.list[--this.size] = null;
    return delete;
  }

  @SuppressWarnings("unchecked")
  @Override
  public E[] toArray(E[] arr) {
    // 다음과 같이 제네릭을 적용한 배열 인스턴스는 생성할 수 없다.
    //    E[] temp = new T[this.size]; // 배열에 저장된 값을 담을 정도의 크기를 가진 새 배열을 만든다.

    // 해결책?
    // => 배열을 만들어주는 클래스의 도움을 받아야한다.
    if(arr.length >= this.size) {

      System.arraycopy(list, 0, arr, 0, this.size);
      return arr;

    } else {
      // 2) 파라미터로 받은 배열이 현재 목록에 들어 있는 값을 담을 만큼 크지 않다면
      // 새 배열을 만들어 복사한다

      // 그러나 다음과 같이 제네릭을 적용한 배열 인스턴스는 생성할 수 없다
      //      E[] temp = new E[this.size];  // 컴파일 오류

      // => Arrays.copyOf(원래배열, 복사할개수, 새로만들배열의타입)
      return (E[]) Arrays.copyOf(
          this.list,   // 원래 배열
          this.size,    // 복사할 개수. 현재 배열에 들어 있는 값들의 개수
          this.list.getClass()
          );  //*알아두기!*
    }
  }
}



