package member;

import java.io.IOException;
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

import dbms.DBMS;


// 14세 이상 회원가입 기능

@WebServlet("/member/signup")
public class memberSignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 입력한 파라미터 값들(아이디,비밀번호,이름,생년,성,동의)을 가져옴.
		String Id = request.getParameter("Id");
		String Pw = request.getParameter("Pw");
		String Name = request.getParameter("Name");
		String Birth = request.getParameter("Birth");
		String Sex = request.getParameter("Sex");
		String Email = request.getParameter("Email");
		String Agree = request.getParameter("Agree");
		
		Connection conn = DBMS.getConnection();
		// DB(ridibooks)에 접속
		
		try {
			//DB에 입력한 파라미터값들을 넣어서 회원 정보를 저장
			String sql = "INSERT INTO member(Id, Pw, Email, Name, Birth, Sex, Agree) VALUES (?,?,?,?,?,?,?)";
			
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Id);
			pstmt.setString(2, Pw);
			pstmt.setString(3, Email);
			pstmt.setString(4, Name);
			pstmt.setString(5, Birth);
			pstmt.setString(6, Sex);
			pstmt.setString(7, Agree);
			
			int result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DBMS 접속 실패");
		}
		
		
		response.setStatus(200);
		// 회원가입이 성공했을 때 상태코드에 200을 반환해줌
		RequestDispatcher rd = request.getRequestDispatcher("로그인 페이지url");
		// 회원가입 성공시 로그인 페이지로 이동
		rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		doGet(request, response);
	}

}
