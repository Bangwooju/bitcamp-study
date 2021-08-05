package com.eomcs.pms.handler;

public class LinkedList {

  static class Node {
    Object item;
    Node next;

    public Node(Object item) {
      this.item = item;
    }
  }

  Node head;
  Node tail;
  int size;

  public void add(Object item) {

    Node node = new Node(item);
    if (head == null) {
      tail = head = node;
    } else {
      // 기존에 tail이 가리키는 마지막 노드의 next 변수에 새 노드 주소를 저장한다.
      tail.next = node;
      // 새로 만든 노드를 마지막 노드로 설정한다. 
      tail = node;
    }
    size++;

  }

  public Object[] toArray() {
    Object[] arr = new Object[this.size];

    Node node = head;
    for(int i =0; i<size; i++) {
      arr[i] = node.item;
      node = node.next;
    }
    return arr;
  }

  public boolean remove(Object item) {
    Node node = head;
    Node prev = null;

    while (node != null) {
      if (node.item == item) {
        if (node == head) {
          head = node.next;
        } else {
          prev.next = node.next; // 이전 노드를 다음 노드와 연결한다.
        }
        node.next = null; // 삭제할 노드가 더이상 다음 노드를 가리키지 않게한다.
        if (node == tail) { // 삭제할 현재 노드가 마지막 노드라면
          tail = prev; // 이전 노드를 마지막 노드로 설정한다.
        }
        size--;
        return true;
      }
      // 현재 노드가 아니라면,
      prev = node; // 현재 노드의 주소를 prev 변수에 저장하고,
      node = node.next; // node 변수에는 다음 노드의 주소를 저장한다.
    }
    return false;
  }


}
