package com.eomcs.util;

public interface List<E> {
  void add(E item);
  Object[] toArray();
  boolean remove(E obj);
  int size();
  E get(int index);
  E remove(int index);

  E[] toArray(E[] arr);

}









