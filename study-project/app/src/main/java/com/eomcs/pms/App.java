package com.eomcs.pms;

import static com.eomcs.menu.Menu.ACCESS_ADMIN;
import static com.eomcs.menu.Menu.ACCESS_GENERAL;
import static com.eomcs.menu.Menu.ACCESS_LOGOUT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.eomcs.context.ApplicationContextListener;
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
import com.eomcs.pms.handler.CommandRequest;
import com.eomcs.pms.handler.MemberAddHandler;
import com.eomcs.pms.handler.MemberDeleteHandler;
import com.eomcs.pms.handler.MemberDetailHandler;
import com.eomcs.pms.handler.MemberListHandler;
import com.eomcs.pms.handler.MemberPrompt;
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
import com.eomcs.pms.listener.AppIniteListener;
import com.eomcs.pms.listener.FileListener;
import com.eomcs.util.Prompt;


public class App {
  List<Board> boardList = new ArrayList<>();
  List<Member> memberList = new LinkedList<>();
  List<Project> projectList = new ArrayList<>();

  HashMap<String,Command> commandMap = new HashMap<>();

  MemberPrompt memberPrompt = new MemberPrompt(memberList);
  ProjectPrompt projectPrompt = new ProjectPrompt(projectList);

  List<ApplicationContextListener> listeners = new ArrayList<>();

  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }

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
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.printf("%s ????????? ???????????? ??? ?????? ??????!\n", menuId);
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    App app = new App(); 
    app.addApplicationContextListener(new AppIniteListener());
    app.addApplicationContextListener(new FileListener());
    app.service();
  }

  public App() {

    commandMap.put("/board/add", new BoardAddHandler(boardList));
    commandMap.put("/board/list", new BoardListHandler(boardList));
    commandMap.put("/board/update", new BoardUpdateHandler(boardList));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardList));
    commandMap.put("/board/detail", new BoardDetailHandler(boardList));
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
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    commandMap.put("/auth/userinfo", new AuthUserInfoHandler());
  }

  private void notifyOnApplicationStarted() {
    Map<String, Object> params = new HashMap<>();
    params.put("boardList", boardList);
    params.put("memberList", memberList);
    params.put("projectList", projectList);
    for(ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  private void notifyOnApplicationEnded() {
    Map<String, Object> params = new HashMap<>();
    params.put("boardList", boardList);
    params.put("memberList", memberList);
    params.put("projectList", projectList);
    for(ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(params);
    }
  }

  void service() {

    notifyOnApplicationStarted();
    createMainMenu().execute();
    Prompt.close();

    notifyOnApplicationEnded();

  }



  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("??????");
    mainMenuGroup.setPrevMenuTitle("??????");

    mainMenuGroup.add(new MenuItem("?????????", ACCESS_LOGOUT , "/auth/login"));
    mainMenuGroup.add(new MenuItem("?????????", ACCESS_GENERAL, "/auth/userinfo"));
    mainMenuGroup.add(new MenuItem("????????????", ACCESS_GENERAL, "/auth/logout"));

    mainMenuGroup.add(createBoardMenu());
    mainMenuGroup.add(createMemberMenu());
    mainMenuGroup.add(createProjectMenu());
    mainMenuGroup.add(createTaskMenu());
    mainMenuGroup.add(createAdminMenu());

    return mainMenuGroup;
  }

  private Menu createBoardMenu() {
    MenuGroup boardMenu = new MenuGroup("?????????");
    boardMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/board/add"));
    boardMenu.add(new MenuItem("??????", "/board/list"));
    boardMenu.add(new MenuItem("????????????", "/board/detail"));
    //    boardMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/board/update"));
    //    boardMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/board/delete"));
    boardMenu.add(new MenuItem("??????", "/board/search"));
    return boardMenu;
  }

  private Menu createMemberMenu() {
    MenuGroup memberMenu = new MenuGroup("??????");
    memberMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/member/add"));
    memberMenu.add(new MenuItem("??????", "/member/list"));
    memberMenu.add(new MenuItem("????????????", "/member/detail"));
    //    memberMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/member/update"));
    //    memberMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/member/delete"));
    return memberMenu;
  }

  private Menu createProjectMenu() {
    MenuGroup projectMenu = new MenuGroup("????????????");
    projectMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/project/add"));
    projectMenu.add(new MenuItem("??????", "/project/list"));
    projectMenu.add(new MenuItem("????????????", "/project/detail"));
    //    projectMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/project/update"));
    //    projectMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/project/delete"));
    return projectMenu;
  }

  private Menu createTaskMenu() {
    MenuGroup taskMenu = new MenuGroup("??????");
    taskMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/task/add"));
    taskMenu.add(new MenuItem("??????", "/task/list"));
    taskMenu.add(new MenuItem("????????????", "/task/detail"));
    //    taskMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/task/update"));
    //    taskMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/task/delete"));
    return taskMenu;
  }

  private Menu createAdminMenu() {
    MenuGroup adminMenu = new MenuGroup("?????????", ACCESS_ADMIN);
    adminMenu.add(new MenuItem("?????? ??????", "/member/add"));
    adminMenu.add(new MenuItem("???????????? ??????", "/project/add"));
    adminMenu.add(new MenuItem("?????? ??????", "/task/add"));
    adminMenu.add(new MenuItem("????????? ??????", "/board/add"));
    return adminMenu;
  }
}












