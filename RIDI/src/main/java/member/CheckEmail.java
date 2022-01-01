package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbms.DBMS;

// 회원강비한 이메일이 있는지  확인하는 기능(Ajax 활용시 사용)
@WebServlet("/check/email")
public class CheckEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Email = request.getParameter("Email");
		System.out.println("AJAX활용시 클라이언트에서 보낸 데이터 =>"+Email);
		
		Connection conn = DBMS.getConnection();
		// DB(ridibooks)에 접속
		
		// 입력한 아이디와 동일한 이메일이 있는지 확인, 있다면 상태코드에 404전달
		String sql = "SELECT * FROM member WHERE Email=?";
		PreparedStatement selectpstmt;
		try {
			selectpstmt = conn.prepareStatement(sql);
			selectpstmt.setString(1, Email);
			
			ResultSet rs = selectpstmt.executeQuery();
			while(rs.next()) {
				if(Email.equals(rs.getString("Email"))) {
					response.setStatus(404);return;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		doGet(request, response);
	}

}
