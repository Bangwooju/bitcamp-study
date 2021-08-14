package com.eomcs.pms.handler;

import com.eomcs.pms.domain.Task;

public class TaskList extends ArrayList{


  public Task findByNo(int no) {
    Object[] list = toArray();
    for (Object object : list) {
      Task task = (Task) object;
      if (task.no == no) {
        return task;
      }
    }
    return null;
  }

}








