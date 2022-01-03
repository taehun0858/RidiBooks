package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get 동작 - 검색?
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST동작 회원가입,로그인
		
		// 
		request.setCharacterEncoding("UTF-8");
		MemberService service = new MemberService();
		int statusCode = service.joinMember(request, response);		
		response.setStatus(statusCode);
		// 성공적으로 회원가입시 200코드 전달됨. -> 코드가 전달되는데서 Id고유 번호를 전달해주는걸로 변경예정
		HttpSession session = request.getSession();
		if(session.isNew() || session.getAttribute("isLogin")==null) {
			session.setAttribute("isLogin", true);
			// 추후 세션에 isLogin속성에 boolean이 아니라 Id의 고유 번호를 넣어줄 예정
		}
		
		if(statusCode == HttpServletResponse.SC_OK) {
			// 상태코드가 200이면 다음 페이지로 이동
			response.sendRedirect("회원가입이 완료된 다음 보일 페이지 URL");
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Put 동작 - 회원 상태 변경(탈퇴)
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Delete 동작 - 생각 중인것 없음
	}
}
