package member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbms.DBMS;

@WebServlet("/member/login")
public class memberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력한 파라미터 값들(아이디,비밀번호)를 불러옴
		String Id = request.getParameter("Id");
		String Pw = request.getParameter("Pw");
		int IdNum;
		
		Connection conn = DBMS.getConnection();
		// DB(ridibooks)에 접속
		
		try {
			//DB에 입력한 ID에 해당하는 정보가 있는지 확인
			String sql = "SELECT * FROM member WHERE id=?";
						
			PreparedStatement selectpstmt = conn.prepareStatement(sql);
			selectpstmt.setString(1, Id);		
			
			ResultSet rs = selectpstmt.executeQuery();
			
			// 아이디가 있다면 비밀번호가 맞는지 확인
			// 맞는 아이디가 없다면 상태코드에 404전달, 비밀번호가 틀렸다면 401전달			
			if(Id.equals(rs.getString("Id"))) {
				if(!Pw.equals(rs.getString("Pw"))) {
					response.setStatus(401);
				}else {
					// 아이디 식별번호 값을 서버의 loginId에 전달하고 LoginStatus에 true를 전달
					IdNum = rs.getInt("Id_Num");
					
					HttpSession session = request.getSession();
					if(session.isNew() || session.getAttribute("LoginStatus")==null) {
						session.setAttribute("LoginStatus", true);	
						session.setAttribute("loginId", IdNum);
					}
					
					response.setStatus(200);
					// 로그인 성공시 상태코드에 200을 전달
					RequestDispatcher rd = request.getRequestDispatcher("메인 페이지");
					// 로그인 성공시 메인 페이지로 이동
					rd.forward(request, response);
					
				}
			}else {
				response.setStatus(404);
			}			
						
			selectpstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBMS 접속 실패");
		}
		
		
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		doGet(request, response);
	}

}
