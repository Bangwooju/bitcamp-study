package com.eomcs.pms.dao;

import java.util.List;
import com.eomcs.pms.domain.Project;

// 역할
// - 게시글 데이터를 처리하는 객체 사용법을 정의한다.
public interface ProjectDao {
  void insert(Project project) throws Exception;
  List<Project> findAll() throws Exception;
  Project findByNo(int no) throws Exception;
  void update(Project project) throws Exception;
  void insertMember(int projectNo, int memberNo) throws Exception;
  void delete(int no) throws Exception;
  void deleteMember(int projectNo) throws Exception;
}
