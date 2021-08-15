package com.eomcs.pms.handler;

public interface List {
  public abstract void add(Object item);

  public abstract Object[] toArray();

  public abstract boolean remove(Object obj);
}
