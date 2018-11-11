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

//�α��� ��û�� ó���ϴ�(��û�� �޴�) ���� ������
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
		
		//�α��� ����Ͻ� ������ ó���ϴ� LoginService Ŭ���� ��ü�� ����
		LoginService loginService=new LoginService();
		
		//�α��ο� ������ ������� ������ ������ getLoginMember �޼ҵ带 ȣ���ϴ� �κ�
		//getLoginMember �޼ҵ忡���� �α��ο� �����ϸ� �α��ο� ������ ������� ������ Member��ü�� ��Ƽ� ��ȯ, �α��ο� �����ϸ� null
		Member loginMember=loginService.getLoginMember(id,passwd);
		
		//�α����� �����Ǹ� Member ��ü�� �Ѿ���� �����ϸ� null�� �Ѿ��
		if(loginMember!=null){
			HttpSession session=request.getSession();
			session.setAttribute("id", id);//�α����� �������� ��� ���� ������ id��� �̸����� �α��ο� ������ ���̵� ���� �Ӽ����� ����
			response.sendRedirect("index.jsp");
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");//�ڹ� ��ũ��Ʈ
			out.println("alert('�α��ν���')");
			out.println("history.back()");//loginForm.html
			out.println("</script>");
		}
	}
}//LoginServlet
