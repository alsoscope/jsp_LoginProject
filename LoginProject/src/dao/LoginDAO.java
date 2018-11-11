package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vo.Member;
import static db.JdbcUtil.*;
import javax.sql.DataSource;//mysql

public class LoginDAO {
	
	//LoginDAO 타입의 레퍼런스 변수 선언. 접근제한자를 private으로 지정, 외부 클래스에서 직접 접근x
	private static LoginDAO loginDAO;
	private Connection con;
	DataSource ds;//mysql
	
	private LoginDAO(){
	}//constructer
	
	//singleton pattern
	public static LoginDAO getInstance(){
		if(loginDAO==null){
			loginDAO=new LoginDAO();
		}
		return loginDAO;
	}
	
	public void setConnection(Connection con){
		this.con=con;
	}
	
	//로그인에 성공하면 Member 객체에 저장, 반환. 실패하면 null반환
	public Member selectLoginMember(String id, String passwd){
		Member loginMember=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			pstmt=con.prepareStatement("SELECT * FROM users WHERE id=? AND passwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs=pstmt.executeQuery();
			if(rs.next()){ //위의 라인에서 조회한 회원 정보를 Member 객체의 속성 값으로 설정하는 부분, id.passwd 당 회원은 존재하거나 않거나 둘 중 하나이기에 if문 while로 여러번 반복할 필요x
				loginMember=new Member();
				loginMember.setAddr(rs.getString("addr"));
				loginMember.setAge(rs.getInt("age"));
				loginMember.setEmail(rs.getString("email"));
				loginMember.setGender(rs.getString("gender"));
				loginMember.setId(rs.getString("id"));
				loginMember.setName(rs.getString("name"));
				loginMember.setNation(rs.getString("nation"));
				loginMember.setPasswd(rs.getString("passwd"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				close(rs);
				close(pstmt);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return loginMember;
	}
}//LoginDAO
