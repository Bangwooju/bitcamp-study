package com.eomcs.pms.service.impl;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Task;
import com.eomcs.pms.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

  @Autowired TaskDao taskDao;

  @Transactional
  @Override
  public int remove(int no) throws Exception {
    return taskDao.delete(no);
  }

  @Transactional
  @Override
  public int add(Task task) throws Exception {
    return taskDao.insert(task);
  }

  @Override
  public List<Task> list() throws Exception {
    return taskDao.findAll(null);
  }

  @Override
  public List<Task> listByProject(int projectNo) throws Exception {
    HashMap<String,Object> map = new HashMap<>();
    map.put("projectNo", projectNo);
    return taskDao.findAll(map);
  }

  @Override
  public Task get(int no) throws Exception {
    return taskDao.findByNo(no);
  }

  @Transactional
  @Override
  public int update(Task task) throws Exception {
    return taskDao.update(task);
  }

}
