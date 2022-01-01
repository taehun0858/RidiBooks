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

// Id를 규칙에 맞게 입력했는지 확인하는 기능(Ajax 활용시 사용)
@WebServlet("/check/id")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 아스키코드 : 32~47 특, 48~57 아라비아, 56~64 특,65~90 영대,91~96 특,97~122 영소,123~126 특수
		// 숫자를 포함하고 있는지를 확인하는 메서드
		public boolean containNumber(String str) {
			boolean valid = false;
			for(char ch :str.toCharArray()) {
				int c = (int)ch;
				if( c >= 48 && c <= 57 ) {valid = true;return valid;}
				else {	valid = false;	}
			}
			return valid;
		}
		// 영대문자를 포함하고 있는지를 확인하는 메서드
		public boolean containUpperEng(String str) {
			boolean valid = false;
			for(char ch :str.toCharArray()) {
				int c = (int)ch;
				if( c >= 65 && c <= 90 ) {valid = true;return valid;}
				else {	valid = false;	}
			}
			return valid;
		}
		//영소문자를 포함하고 있는지를 확인하는 메서드
		public boolean containLowerEng(String str) {
			boolean valid = false;
			for(char ch :str.toCharArray()) {
				int c = (int)ch;
				if( c >= 97 && c <= 122 ) {valid = true;return valid;}
				else {	valid = false;	}
			}
			return valid;
		}	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Id = request.getParameter("Id");
		System.out.println("AJAX활용시 클라이언트에서 보낸 데이터 =>"+Id);
		
		if((Id.length()>=5 && Id.length()<20) && (containNumber(Id)||containLowerEng(Id)||containUpperEng(Id))) {
			// 입력한 값의 길이가 5이상 20이하이고, 영어나 숫자를 포함했는지 확인 했다면 200 전달, 안했다면 404전달
			
			Connection conn = DBMS.getConnection();
			// DB(ridibooks)에 접속
			
			// 입력한 아이디와 동일한 아이디가 있는지 확인, 있다면 상태코드에 404전달
			String sql = "SELECT * FROM member WHERE Id=?";
			PreparedStatement selectpstmt;
			try {
				selectpstmt = conn.prepareStatement(sql);
				selectpstmt.setString(1, Id);
				
				ResultSet rs = selectpstmt.executeQuery();
				while(rs.next()) {
					if(Id.equals(rs.getString("Id"))) {
						response.setStatus(404);return;
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}else {
			response.setStatus(404);
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		doGet(request, response);
	}

}
