package member;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberService {
	public int joinMember(HttpServletRequest request, HttpServletResponse response) {
		String Id = request.getParameter("Id");
		String Pw = request.getParameter("Pw");
		String Pw2 = request.getParameter("Pw2");
		String Name = request.getParameter("Name");
		String Birth = request.getParameter("Birth");
		String Sex = request.getParameter("Sex");
		String Email = request.getParameter("Email");
		String Agree = request.getParameter("Agree"); 
		// member테이블에 저장하는게 나을지 아님 처음 했던데로 Agree테이블에 따로 저장하는게 나을지??
		int statusCode = 400;
		
		// 항목들 리스트에 저장
		String parameters[] = {Id,Pw,Pw2,Name,Birth,Sex,Email,Agree};
		
		
		// 파리미터 값 들 확인 하는 코드....추가 필요함.
		// 항목들 중 하나라도 빠져있으면 400코드 반환
		for(String parameter : parameters) {
			parameter=parameter.trim(); // 빈칸 제거
			parameter=parameter.replace(" ", ""); // 빈칸 제거
			if(parameter==null||parameter.isEmpty()) {				
				return statusCode = 400;
			}
		}
		// 파라미터 길이 제한 확인
		if((Id.length()<5 || Id.length()>20) || (Pw.length()<8 || Pw.length()>18) || (Email.length()>30) || Name.length()>17) {
			statusCode = 401; return statusCode;
		}
		
		if(!Pw.equals(Pw2)) { 
			// 비밀번호와 비밀번호 확인의 값은 같아야함.
			statusCode = 401; return statusCode;}
		
		
		MemberDto member = new MemberDto();
		member.setId(Id);
		member.setPw(Pw);
		member.setName(Name);
		member.setBirth(Birth);
		member.setSex(Sex.charAt(0));
		member.setEmail(Email); 
		// LocalDateTime M_date = LocalDateTime.now();
		// member.setM_Date(M_date); 
		// 회원가입한 날짜와 시간 인데 시간이 안들어감(형식이 Date이니까) 
		
		
		MemberDao dao = new MemberDao();
		boolean success = dao.insertMemebr(member);
		
		
		if(success) statusCode = HttpServletResponse.SC_OK;
		
		return statusCode;
		
	}
}
