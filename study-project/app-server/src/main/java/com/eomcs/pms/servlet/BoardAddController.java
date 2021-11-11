package com.eomcs.pms.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.eomcs.pms.dao.BoardDao;
import com.eomcs.pms.domain.Board;
import com.eomcs.pms.domain.Member;

@WebServlet("/board/add")
public class BoardAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  SqlSession sqlSession;
  BoardDao boardDao;

  @Override
  public void init() {
    ServletContext 웹애플리케이션공용저장소 = getServletContext();
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
    boardDao = (BoardDao) 웹애플리케이션공용저장소.getAttribute("boardDao");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {

      Board board = new Board();

      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setWriter((Member) request.getSession().getAttribute("loginUser"));

      boardDao.insert(board);
      sqlSession.commit();

      response.sendRedirect("list");


    } catch (Exception e) {
      // 오류를 출력할 때 사용할 수 있도록 예외 객체를 저장소에 보관한다.
      request.setAttribute("error", e);

      // 오류가 발생하면, 오류 내용을 출력할 뷰를 호출한다.
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}







