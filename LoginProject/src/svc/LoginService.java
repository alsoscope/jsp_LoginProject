package svc;

import static db.JdbcUtil.*;
import java.sql.Connection;
import dao.LoginDAO;
import vo.Member;

//로그인 비즈니스 로직을 처리
public class LoginService {
	public Member getLoginMember(String id, String passwd){
		LoginDAO loginDAO=LoginDAO.getInstance();//LoginDAO클래스에 정의된 getInstance()메소드를 호출하여 LoginDAO 객체를 참조하는 레퍼런스 값을 얻어옴. 싱글톤 패턴
		Connection con=getConnection();
		loginDAO.setConnection(con);//LoginDAO객체에서 db작업을 할 때 사용할 connection 객체를 주입하는 부분
		Member loginMember=loginDAO.selectLoginMember(id, passwd);//LoginDAO 객체로 로그인한 사용자의 정보를 Member 객체로 반환하는 메소드(selectLoginmember)호출
		close(con);
		return loginMember;//로그인된 사용자의 정보를 반환
	}
}//LoginService
