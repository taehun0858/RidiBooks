package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDao {
	// DB접속
	private Connection getConnection() {
		InitialContext ic;
		try {
			ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/maria/ridibooks");
			Connection conn = ds.getConnection();
			return conn;
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("Connecting Pool 과 관련된 예외가 발생");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connecting 관련된 예외가 발생");
		}
		
		return null;		
	}
	
	// member정보 추가하기 -> 성공 시 true반환, 실패 시  false반환 
	public boolean insertMemebr(MemberDto member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean success = false;		
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO member(Id,Pw,Email,Name,Birth,m_date,m_redate,Sex) VALUES(?,?,?,?,?,?,?,?);";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getName());
			pstmt.setString(5, member.getBirth());
			pstmt.setDate(6, member.getM_Date());
			pstmt.setDate(7, member.getM_reDate());
			pstmt.setString(8, String.valueOf(member.getSex())); 	
			// member의 데이터 형태가 char이지만 pstmt에서는 char저장이 없는듯-> member.getSex()를 String로 형변환
			
			int result = pstmt.executeUpdate();
			
			success = result == 1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!= null) {try {conn.close();} 
				catch (SQLException e) {e.printStackTrace();}
			}
			if(pstmt!= null) {try {pstmt.close();} 
				catch (SQLException e) {e.printStackTrace();}
			}
		}
		
		return success;
	}
}
