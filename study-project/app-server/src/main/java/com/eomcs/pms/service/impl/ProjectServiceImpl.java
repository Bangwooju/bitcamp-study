package com.eomcs.pms.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.dao.TaskDao;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired TaskDao taskDao;
  @Autowired ProjectDao projectDao;

  @Transactional
  @Override
  public int remove(int no) throws Exception {
    try {
      return projectDao.inactive(no);
    } catch (Exception e) {
      throw e;
    } finally {
    }
  }

  @Transactional
  @Override
  public int add(Project project) throws Exception {
    projectDao.insert(project);
    projectDao.insertMembers(project);
    return project.getNo();
  }


  @Override
  public List<Project> list() throws Exception {
    return projectDao.findAll(null);
  }


  @Override
  public List<Project> list(String keyword) throws Exception {
    return projectDao.findAll(keyword);
  }


  @Override
  public List<Project> list(Map<String, Object> keywords) throws Exception {
    return projectDao.findByKeyword(keywords);
  }


  @Override
  public Project get(int no) throws Exception {
    return projectDao.findByNo(no);
  }

  @Transactional
  @Override
  public int update(Project project) throws Exception {
    // 프로젝트 정보를 변경한다.
    int count = projectDao.update(project);

    // 프로젝트 멤버를 변경한다.
    // 1) 기존 멤버를 비활성화시킨다.
    //    삭제하지 않고 왜?
    //    - 삭제하면 그 회원이 했던 작업도 모두 삭제되기 때문이다.
    //    - 그러면 프로젝트에서 수행한 작업 기록이 사라진다.
    //
    //    - 이전 프로젝트 정보(멤버 목록 포함)를 가져온다.
    Project oldProject = projectDao.findByNo(project.getNo());

    //    - 이전 프로젝트의 전체 멤버를 비활성 상태로 만든다.
    if (oldProject.getMembers().size() > 0) {
      projectDao.updateInactiveMembers(oldProject);
    }

    // 2) 변경한 프로젝트의 멤버를 활성 상태로 만든다.
    if (project.getMembers().size() > 0) {
      projectDao.updateActiveMembers(project);
    }

    // 3) 프로젝트에 추가한 멤버를 등록한다.
    List<Member> addMembers = minusMembers(
        project.getMembers(),
        oldProject.getMembers());

    if (addMembers.size() > 0) {
      // 파라미터로 받은 프로젝트 객체를 변경하지 않기 위해
      // 새 프로젝트 객체를 만들어 사용한다.
      // => 파라미터 값은 가능한 변경하지 말라!
      Project updateMembersProject = new Project();
      updateMembersProject.setNo(project.getNo());
      updateMembersProject.setMembers(addMembers);

      projectDao.insertMembers(updateMembersProject);
    }

    return count;
  }

  private List<Member> minusMembers(List<Member> g1, List<Member> g2) {
    ArrayList<Member> result = new ArrayList<>();
    outerLoop:
      for (Member m : g1) {
        for (Member m2 : g2) {
          if (m.getNo() == m2.getNo()) {
            continue outerLoop;
          }
        }
        result.add(m);
      }
    return result;
  }

}
