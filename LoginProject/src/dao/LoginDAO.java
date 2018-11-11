package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vo.Member;
import static db.JdbcUtil.*;
import javax.sql.DataSource;//mysql

public class LoginDAO {
	
	//LoginDAO Ÿ���� ���۷��� ���� ����. ���������ڸ� private���� ����, �ܺ� Ŭ�������� ���� ����x
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
	
	//�α��ο� �����ϸ� Member ��ü�� ����, ��ȯ. �����ϸ� null��ȯ
	public Member selectLoginMember(String id, String passwd){
		Member loginMember=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try{
			pstmt=con.prepareStatement("SELECT * FROM users WHERE id=? AND passwd=?");
			pstmt.setString(1, id);
			pstmt.setString(2, passwd);
			rs=pstmt.executeQuery();
			if(rs.next()){ //���� ���ο��� ��ȸ�� ȸ�� ������ Member ��ü�� �Ӽ� ������ �����ϴ� �κ�, id.passwd �� ȸ���� �����ϰų� �ʰų� �� �� �ϳ��̱⿡ if�� while�� ������ �ݺ��� �ʿ�x
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
