package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	private static final long serialVersionUID=1L;

	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//id 정보를 저장하고 있는 session을 삭제 후 index.jsp로 리다이렉트
		HttpSession session=request.getSession();//로그아웃 요청한 사용자의 세션 객체session 얻어오는 부분
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
}//LogoutServlet
