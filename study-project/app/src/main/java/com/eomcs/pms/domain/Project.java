package com.eomcs.pms.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.eomcs.csv.CsvValue;

public class Project implements CsvValue {
  private int no;
  private String title;
  private String content;
  private Date startDate;
  private Date endDate;
  private Member owner;
  private List<Member> members = new ArrayList<>();

  private List<Task> tasks = new ArrayList<>();


  // 다음 메서드는 CsvValue 규칙에 따라 만든 것이다.
  @Override
  public String toCsvString() {

    // 프로젝트 정보를 csv로 출력할 때 3줄로 표현한다.
    StringBuilder strBuilder = new StringBuilder();

    strBuilder.append(String.format("%d,%s,%s,%s,%s,%d,%s,",
        this.getNo(),
        this.getTitle(),
        this.getContent(),
        this.getStartDate(),
        this.getEndDate(),
        this.getOwner().getNo(),
        this.getOwner().getName()));

    //2) 프로젝트 멤버 정보를 저장한다.
    // => 프로젝트 멤버의 수를 저장한다.
    strBuilder.append(String.format("%d,", this.getMembers().size())); // 이전 값과 다음 값을 구분하는 용도

    // 프로젝트 멤버들의 정보를 저장한다.
    for (Member m : this.getMembers()) {
      strBuilder.append(String.format("%d,%s,", m.getNo(), m.getName()));
    }

    // 3) 프로젝트 작업 정보를 수행한다.
    // 작업의 수를 저장한다.
    strBuilder.append(String.format("%d,", this.getTasks().size()));

    // 작업들의 정보를 저장한다.
    for(Task t : this.getTasks()) {
      strBuilder.append(String.format("%s,", t.toCsvString()));
    }

    return strBuilder.toString();
  }

  @Override
  public void loadCsv(String csv) {

    String[] values = csv.split(",");

    // 1) 프로젝트 기본 정보를 로딩
    this.setNo(Integer.valueOf(values[0]));
    this.setTitle(values[1]);
    this.setContent(values[2]);
    this.setStartDate(Date.valueOf(values[3]));
    this.setEndDate(Date.valueOf(values[4]));

    // 2) 프로젝트 관리자 정보 로딩
    Member owner = new Member();
    owner.setNo(Integer.valueOf(values[5]));
    owner.setName(values[6]);

    this.setOwner(owner);

    int lastIndex = 0;
    // 3) 프로젝트 멤버 정보 로딩
    int memberSize = Integer.valueOf(values[7]);
    for(int i = 0, offset = 8; i < memberSize; i++, offset +=2) {
      // => 파일에서 멤버 번호와 이름을 로딩한다.
      Member m = new Member();
      m.setNo(Integer.valueOf(values[offset]));
      m.setName(values[offset+1]);

      // => 프로젝트에 멤버에 추가한다.
      this.getMembers().add(m);

      // => 작업 데이터를 읽을 때 사용할 마지막 인덱스 번호를 저장해 둔다.
      lastIndex = offset + 1;
    }

    // 4) 작업 정보 로딩
    int taskSize = Integer.valueOf(values[lastIndex +1]);

    for(int i = 0, offset = lastIndex +2; i < taskSize; i++, offset +=6) {
      // => 파일에서 작업 데이터를 로딩한다.
      Task t = new Task();
      t.loadCsv(String.join(",", Arrays.copyOfRange(values, offset, offset + 6)));
      //      t.loadCsv(String.format("%s, %s, %s, %s, %s, %s", 
      //          values[offset],
      //          values[offset+1],
      //          values[offset+2],
      //          values[offset+3],
      //          values[offset+4],
      //          values[offset+5]));

      this.getTasks().add(t);
    }

  }


  @Override
  public String toString() {
    return "Project [no=" + no + ", title=" + title + ", content=" + content + ", startDate="
        + startDate + ", endDate=" + endDate + ", owner=" + owner + ", members=" + members
        + ", tasks=" + tasks + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public Date getStartDate() {
    return startDate;
  }
  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }
  public Date getEndDate() {
    return endDate;
  }
  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }
  public Member getOwner() {
    return owner;
  }
  public void setOwner(Member owner) {
    this.owner = owner;
  }
  public List<Member> getMembers() {
    return members;
  }
  public void setMembers(List<Member> members) {
    this.members = members;
  }
  public List<Task> getTasks() {
    return tasks;
  }
  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public String getMemberNames() {
    if (this.members == null) {
      return "";
    }

    StringBuilder names = new StringBuilder();
    for (Member member : this.members) {
      if (names.length() > 0) {
        names.append(",");
      }
      names.append(member.getName());
    }
    return names.toString();
  }

  public Task findTaskByNo(int taskNo) {
    for (Task task : this.tasks) {
      if (task.getNo() == taskNo) {
        return task;
      }
    }
    return null;
  }
}