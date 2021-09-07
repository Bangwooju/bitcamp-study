package com.eomcs.pms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;
import com.eomcs.pms.domain.Project;
import com.eomcs.pms.handler.AuthLoginHandler;
import com.eomcs.pms.handler.AuthLogoutHandler;
import com.eomcs.pms.handler.AuthUserInfoHandler;
import com.eomcs.pms.handler.BoardAddHandler;
import com.eomcs.pms.handler.BoardDeleteHandler;
import com.eomcs.pms.handler.BoardDetailHandler;
import com.eomcs.pms.handler.BoardListHandler;
import com.eomcs.pms.handler.BoardSearchHandler;
import com.eomcs.pms.handler.BoardUpdateHandler;
import com.eomcs.pms.handler.Command;
import com.eomcs.pms.handler.MemberAddHandler;
import com.eomcs.pms.handler.MemberDeleteHandler;
import com.eomcs.pms.handler.MemberDetailHandler;
import com.eomcs.pms.handler.MemberListHandler;
import com.eomcs.pms.handler.MemberPromptHandler;
import com.eomcs.pms.handler.MemberUpdateHandler;
import com.eomcs.pms.handler.ProjectAddHandler;
import com.eomcs.pms.handler.ProjectDeleteHandler;
import com.eomcs.pms.handler.ProjectDetailHandler;
import com.eomcs.pms.handler.ProjectListHandler;
import com.eomcs.pms.handler.ProjectPrompt;
import com.eomcs.pms.handler.ProjectUpdateHandler;
import com.eomcs.pms.handler.TaskAddHandler;
import com.eomcs.pms.handler.TaskDeleteHandler;
import com.eomcs.pms.handler.TaskDetailHandler;
import com.eomcs.pms.handler.TaskListHandler;
import com.eomcs.pms.handler.TaskUpdateHandler;
import com.eomcs.util.Prompt;

public class App {
  List<Board> boardList = new ArrayList<>();
  List<Member> memberList = new LinkedList<>();
  List<Project> projectList = new ArrayList<>();
  ProjectPrompt projectPrompt = new ProjectPrompt(projectList);

  HashMap<String, Command> commandMap = new HashMap<>();
  MemberPromptHandler memberPrompt = new MemberPromptHandler(memberList);

  class MenuItem extends Menu {

    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }


    public MenuItem(String title, int accessScope, String menuId) {
      super(title, accessScope);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      command.execute();
    }
  }

  public static void main(String[] args) {
    App app = new App(); 
    app.service();
  }

  public App() {
    commandMap.put("/board/add", new BoardAddHandler(boardList));
    commandMap.put("/board/list", new BoardListHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));
    commandMap.put("/board/search", new BoardSearchHandler(boardList));

    commandMap.put("/member/add", new MemberAddHandler(memberList));
    commandMap.put("/member/list", new MemberListHandler(memberList));
    commandMap.put("/member/detail", new MemberDetailHandler(memberList));
    commandMap.put("/member/update", new MemberUpdateHandler(memberList));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberList));

    commandMap.put("/project/add", new ProjectAddHandler(projectList, memberPrompt));
    commandMap.put("/project/list", new ProjectListHandler(projectList));
    commandMap.put("/project/detail", new ProjectDetailHandler(projectList));
    commandMap.put("/project/update", new ProjectUpdateHandler(projectList, memberPrompt));
    commandMap.put("/project/delete", new ProjectDeleteHandler(projectList));

    commandMap.put("/task/add", new TaskAddHandler(projectPrompt));
    commandMap.put("/task/list", new TaskListHandler(projectPrompt));
    commandMap.put("/task/detail", new TaskDetailHandler(projectPrompt));
    commandMap.put("/task/update", new TaskUpdateHandler(projectPrompt));
    commandMap.put("/task/delete", new TaskDeleteHandler(projectPrompt));

    commandMap.put("/auth/login", new AuthLoginHandler(memberList));
    commandMap.put("/auth/userinfo", new AuthUserInfoHandler());
    commandMap.put("/auth/logout", new AuthLogoutHandler());
  }



  void service() {
    loadBoards();
    createMenu().execute();
    Prompt.close();
    saveBoards();


  }

  Menu createMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("메인");
    mainMenuGroup.setPrevMenuTitle("종료");

    mainMenuGroup.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mainMenuGroup.add(new MenuItem("내정보", Menu.ACCESS_GENERAL , "/auth/userinfo"));
    mainMenuGroup.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL, "/auth/userinfo"));
    mainMenuGroup.add(createBoardMenu());
    mainMenuGroup.add(createMemberMenu());
    mainMenuGroup.add(createProjectMenu());
    mainMenuGroup.add(createTaskMenu());

    return mainMenuGroup;
  }

  private Menu createBoardMenu() {
    MenuGroup memberMenu = new MenuGroup("게시판");
    memberMenu.add(new MenuItem("등록", Menu.ACCESS_GENERAL, "/board/add"));
    memberMenu.add(new MenuItem("목록", "/board/list"));
    memberMenu.add(new MenuItem("상세보기", "/board/detail"));
    memberMenu.add(new MenuItem("변경", Menu.ACCESS_GENERAL, "/board/update"));
    memberMenu.add(new MenuItem("삭제", Menu.ACCESS_GENERAL, "/board/delete"));
    return memberMenu;
  }

  private Menu createMemberMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new MenuItem("등록", Menu.ACCESS_GENERAL, "/member/add"));
    memberMenu.add(new MenuItem("목록", "/member/list"));
    memberMenu.add(new MenuItem("상세보기", "/member/detail"));
    memberMenu.add(new MenuItem("변경", Menu.ACCESS_GENERAL, "/member/update"));
    memberMenu.add(new MenuItem("삭제", Menu.ACCESS_GENERAL, "/member/delete"));
    return memberMenu;
  }

  private Menu createProjectMenu() {
    MenuGroup projectMenu = new MenuGroup("프로젝트");
    projectMenu.add(new MenuItem("등록", Menu.ACCESS_GENERAL, "/project/add"));
    projectMenu.add(new MenuItem("목록", "/project/list"));
    projectMenu.add(new MenuItem("상세보기", "/project/detail"));
    projectMenu.add(new MenuItem("변경", Menu.ACCESS_GENERAL, "/project/update"));
    projectMenu.add(new MenuItem("삭제", Menu.ACCESS_GENERAL, "/project/delete"));
    return projectMenu;
  }

  private Menu createTaskMenu() {
    MenuGroup taskMenu = new MenuGroup("작업");
    taskMenu.add(new MenuItem("등록", Menu.ACCESS_GENERAL, "/task/add"));
    taskMenu.add(new MenuItem("목록", "/task/list"));
    taskMenu.add(new MenuItem("상세보기", "/task/detail"));
    taskMenu.add(new MenuItem("변경", Menu.ACCESS_GENERAL, "/task/update"));
    taskMenu.add(new MenuItem("삭제", Menu.ACCESS_GENERAL, "/task/delete"));
    return taskMenu;
  }

  @SuppressWarnings("unchecked")
  private void loadBoards() {
    //파일에서 바이트를 읽어오는 일을 하는 객체
    try (
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("board.data3"))) {

      List<Board> list = (List<Board>) in.readObject();
      list.addAll(list);

      System.out.println("게시글 데이터 로딩 완료!");
    } catch(Exception e) {
      System.out.println("파일에서 게시글을 읽어 오는 중 오류 발생!");
    }
  }

  private void saveBoards() {
    // 게시글 데이터를 파일로 내보내기(저장하기, 쓰기)
    try (ObjectOutputStream out2 = 
        new ObjectOutputStream(new FileOutputStream("board.data3"))) {
      // 출력할 게시글 개수를 먼저 저장한다.
      out2.writeObject(boardList);
      System.out.println("게시글 저장 완료!");
    } catch (Exception e) {
      System.out.println("게시글을 파일에 저장 중 오류 발생!");
    }
    // 이렇게 게시글 데이터를 출력할 때 나름의 형식에 따라 데이터를 출력한다.
    // - 처음 4바이트는 저장할 게시글의 개수이고,
    // - 그 다음 4바이트는 게시글 번호이고, 
    // - 그 다음 4바이트는 제목의 바이트 개수이고 등등
    // 파일의 데이터를 출력할 때 사용하는 규칙을 "파일 포맷(format)" 이라 부른다.
    // 당연히 파일에서 데이터를 읽을 때는 저장한 규칙에 맞춰 읽어야 한다.
    // 즉 "파일 포맷"에 맞춰 읽어야한다.
    // .ppt 파일을 읽을 때는 ppt 파일 포맷에 맞춰 읽어야하고, 
    // .gif 파일을 읽을 때는 gif 파일 포맷에 맞춰 읽어야한다.
    // 만약 파일 포맷을 모른다면 해당 파일을 제대로 읽을 수가 없다.
  }

}












