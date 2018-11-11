package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import svc.LoginService;
import vo.Member;

//로그인 요청을 처리하는(요청을 받는) 서블릿 페이지
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID=1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String id=request.getParameter("id");
		String passwd=request.getParameter("passwd");
		
		//로그인 비즈니스 로직을 처리하는 LoginService 클래스 객체를 생성
		LoginService loginService=new LoginService();
		
		//로그인에 성공한 사용자의 정보를 얻어오는 getLoginMember 메소드를 호출하는 부분
		//getLoginMember 메소드에서는 로그인에 성공하면 로그인에 성공한 사용자의 정보를 Member객체에 담아서 반환, 로그인에 실패하면 null
		Member loginMember=loginService.getLoginMember(id,passwd);
		
		//로그인이 성공되면 Member 객체가 넘어오고 실패하면 null이 넘어옴
		if(loginMember!=null){
			HttpSession session=request.getSession();
			session.setAttribute("id", id);//로그인이 성공했을 경우 세션 영역에 id라는 이름으로 로그인에 성공한 아이디 값을 속성으로 공유
			response.sendRedirect("index.jsp");
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");//자바 스크립트
			out.println("alert('로그인실패')");
			out.println("history.back()");//loginForm.html
			out.println("</script>");
		}
	}
}//LoginServlet
