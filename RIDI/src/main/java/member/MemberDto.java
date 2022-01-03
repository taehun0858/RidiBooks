package member;

import java.sql.Date;

public class MemberDto {
	private String Id;					// 아이디
	private String Pw;					// 비밀번호
	private String Email;				// 이메일
	private String Name;				// 이름
	private String Birth;				// 생년월일
	private Date m_Date;		// 가입일
	private Date m_reDate;		// 최근 접속일
	private char Sex;					// 성별
	private int m_cash = 0;					// 보유 캐쉬
	private int m_point = 0;				// 보유 포인트
	
	public MemberDto() {
		// 회원이 생기면서 생길 테이블 정보들을 생성하는 코드
		// 내 서재, 내 쿠폰, 내 위시리스트, 내 카트, 내 알림 등등 
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPw() {
		return Pw;
	}
	public void setPw(String pw) {
		Pw = pw;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String birth) {
		Birth = birth;
	}
	public Date getM_Date() {
		return m_Date;
	}
	public void setM_Date(Date m_Date) {
		this.m_Date = m_Date;
	}
	public Date getM_reDate() {
		return m_reDate;
	}
	public void setM_reDate(Date m_reDate) {
		this.m_reDate = m_reDate;
	}
	public char getSex() {
		return Sex;
	}
	public void setSex(char sex) {
		Sex = sex;
	}
	public int getM_cash() {
		return m_cash;
	}
	public void setM_cash(int m_cash) {
		this.m_cash = m_cash;
	}
	public int getM_point() {
		return m_point;
	}
	public void setM_point(int m_point) {
		this.m_point = m_point;
	}
	
	
	
}
