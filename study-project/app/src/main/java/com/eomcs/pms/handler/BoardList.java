package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Board;

public class BoardList extends ArrayList {
  public Board findByNo(int no) {
    Object[] list = toArray();

    for (Object object : list) {
      Board board = (Board)object;
      if (board.no == no) {
        return board;
      }
    }
    return null;
  }

}








