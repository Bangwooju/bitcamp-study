package com.eomcs.pms.handler;


public class LinkedList {

  static class Node{

    Node next;
    Object object;

    public Node(Object object) {
      this.object = object;
    }
  }

  Node head;
  Node tail;
  int size;

  public void add(Object object) {
    Node node = new Node(object);

    if(head ==null) {
      tail = head = node;
    } else {
      tail.next = node;
      tail= node;
    }
    size++;
  }


  public Object[] toArray() {

    Object[] arr = new Object[this.size];
    Node node = head;
    for(int i = 0; i < size; i++) {
      arr[i] = node.object;
      node = node.next;
    }
    return arr;

  }

  public boolean remove(Object object) {

    Node node = head;
    Node prev = null;

    while(node != null) {
      if(node.object == object) {
        if(node == head) {
          head = node.next;
        } else {
          prev.next = node.next;
        }
        node.next = null;
        if(node == tail) {
          tail = prev;
        }
        --size;
        return true;
      }
      prev = node;
      node = node.next;
    }
    return false;
  }


}
