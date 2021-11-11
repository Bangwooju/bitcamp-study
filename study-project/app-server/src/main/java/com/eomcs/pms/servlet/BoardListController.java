package com.eomcs.pms.servlet;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  BoardDao boardDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      // 클라이언트 요청을 처리하는데 필요한 데이터 준비
      Collection<Board> boardList = boardDao.findAll();

      // 뷰 컴포넌트가 준비한 데이터를 사용할 수 있도록 저장소에 보관한다.
      request.setAttribute("boardList", boardList);
      request.setAttribute("pageTitle", "게시글 목록");
      request.setAttribute("contentUrl", "/board/BoardList.jsp");

      request.getRequestDispatcher("/template1.jsp").forward(request, response);

    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







