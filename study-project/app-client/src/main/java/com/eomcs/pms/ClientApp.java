package com.eomcs.pms;

import static com.eomcs.menu.Menu.ACCESS_ADMIN;
import static com.eomcs.menu.Menu.ACCESS_GENERAL;
import static com.eomcs.menu.Menu.ACCESS_LOGOUT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.eomcs.context.ApplicationContextListener;
import com.eomcs.menu.Menu;
import com.eomcs.menu.MenuFilter;
import com.eomcs.menu.MenuGroup;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.dao.MemberDao;
import com.eomcs.pms.dao.ProjectDao;
import com.eomcs.pms.dao.TaskDao;
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
import com.eomcs.util.Prompt;

public class ClientApp {


  SqlSession sqlSession;
  HashMap<String,Command> commandMap = new HashMap<>();

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

  //????????? ?????? ????????? ?????????
  List<ApplicationContextListener> listeners = new ArrayList<>();

  // ?????????(?????????)??? ???????????? ?????????
  public void addApplicationContextListener(ApplicationContextListener listener) {
    this.listeners.add(listener);
  }

  // ?????????(?????????)??? ???????????? ?????????
  public void removeApplicaionContextListener(ApplicationContextListener listener) {
    this.listeners.remove(listener);
  }

  private void notifyOnApplicationStarted() {
    // ????????????????????? ?????? ??? ??? ????????? ??????????????? ?????????.
    HashMap<String,Object> params = new HashMap<>();
    for (ApplicationContextListener listener : listeners) {
      listener.contextInitialized(params);
    }
  }

  private void notifyOnApplicationEnded() {
    // ????????????????????? ?????? ??? ??? ????????? ??????????????? ?????????.
    HashMap<String,Object> params = new HashMap<>();
    for (ApplicationContextListener listener : listeners) {
      listener.contextDestroyed(params);
    }
  }


  public ClientApp() throws Exception {

    // sqlSession ?????? ??????
    sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "com/eomcs/pms/conf/mybatis-config.xml")).openSession();
    BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
    //    MemberDao memberDao = new MariadbMemberDao(con);
    MemberDao memberDao = sqlSession.getMapper(MemberDao.class);

    ProjectDao projectDao = sqlSession.getMapper(ProjectDao.class);
    TaskDao taskDao = sqlSession.getMapper(TaskDao.class);


    commandMap.put("/member/add", new MemberAddHandler(memberDao, sqlSession));
    commandMap.put("/member/list", new MemberListHandler(memberDao));
    commandMap.put("/member/detail", new MemberDetailHandler(memberDao));
    commandMap.put("/member/update", new MemberUpdateHandler(memberDao, sqlSession));
    commandMap.put("/member/delete", new MemberDeleteHandler(memberDao, sqlSession));

    commandMap.put("/board/add", new BoardAddHandler(boardDao, sqlSession));
    commandMap.put("/board/list", new BoardListHandler(boardDao));
    commandMap.put("/board/detail", new BoardDetailHandler(boardDao, sqlSession));
    commandMap.put("/board/update", new BoardUpdateHandler(boardDao, sqlSession));
    commandMap.put("/board/delete", new BoardDeleteHandler(boardDao, sqlSession));
    commandMap.put("/board/search", new BoardSearchHandler(boardDao));

    commandMap.put("/auth/login", new AuthLoginHandler(memberDao));
    commandMap.put("/auth/userinfo", new AuthUserInfoHandler());
    commandMap.put("/auth/logout", new AuthLogoutHandler());
    MemberPrompt memberPrompt = new MemberPrompt(memberDao);

    commandMap.put("/project/add", new ProjectAddHandler(projectDao, memberPrompt, sqlSession));
    commandMap.put("/project/list", new ProjectListHandler(projectDao));
    commandMap.put("/project/detail", new ProjectDetailHandler(projectDao));
    commandMap.put("/project/update", new ProjectUpdateHandler(projectDao, memberPrompt, sqlSession));
    commandMap.put("/project/delete", new ProjectDeleteHandler(projectDao, sqlSession));

    ProjectPrompt projectPrompt = new ProjectPrompt(projectDao);
    commandMap.put("/task/add", new TaskAddHandler(taskDao, sqlSession));
    commandMap.put("/task/list", new TaskListHandler(projectPrompt, taskDao));
    commandMap.put("/task/detail", new TaskDetailHandler(taskDao));
    commandMap.put("/task/update", new TaskUpdateHandler(taskDao, sqlSession));
    commandMap.put("/task/delete", new TaskDeleteHandler(taskDao, sqlSession));

  }

  // MenuGroup?????? ????????? ????????? ????????????.
  MenuFilter menuFilter = menu -> 
  (menu.getAccessScope() & AuthLoginHandler.getUserAccessLevel()) > 0 ;


  Menu createMainMenu() {
    MenuGroup mainMenuGroup = new MenuGroup("??????");
    mainMenuGroup.setMenuFilter(menuFilter);
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
    boardMenu.setMenuFilter(menuFilter);
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
    memberMenu.setMenuFilter(menuFilter);
    memberMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/member/add"));
    memberMenu.add(new MenuItem("??????", "/member/list"));
    memberMenu.add(new MenuItem("????????????", "/member/detail"));
    //    memberMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/member/update"));
    //    memberMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/member/delete"));
    return memberMenu;
  }

  private Menu createProjectMenu() {
    MenuGroup projectMenu = new MenuGroup("????????????");
    projectMenu.setMenuFilter(menuFilter);
    projectMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/project/add"));
    projectMenu.add(new MenuItem("??????", "/project/list"));
    projectMenu.add(new MenuItem("????????????", "/project/detail"));
    //    projectMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/project/update"));
    //    projectMenu.add(new MenuItem("??????", ACCESS_GENERAL, "/project/delete"));
    return projectMenu;
  }

  private Menu createTaskMenu() {
    MenuGroup taskMenu = new MenuGroup("??????");
    taskMenu.setMenuFilter(menuFilter);
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

  void service() throws Exception {

    notifyOnApplicationStarted();

    createMainMenu().execute();

    Prompt.close();

    notifyOnApplicationEnded();

    //SqlSession ????????? ????????? ????????????.
    sqlSession.close();
  }



  public static void main(String[] args) throws Exception {
    System.out.println("[PMS ???????????????]");
    ClientApp app = new ClientApp(); 

    // ????????????????????? ??????????????? ???????????? ?????? ???????????? ????????????.
    app.addApplicationContextListener(new AppIniteListener());
    app.service();

    Prompt.close();
  }
}

