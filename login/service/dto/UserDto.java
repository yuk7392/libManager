package library_project.login.service.dto;

import java.util.List;

/* 사용자 정보 */
public class UserDto {
	private String cId;
	private String Cpwd;
	private String Cname;
	private String Cemail;
	private String cTel;
	private String grade;
	private int cAge;
	private List<BorrowList> CborrowList;
	
	public UserDto() {
		super();
	}

	public UserDto(String cId, String cpwd, String cname, String cemail, String cTel, int cAge, String grade) {
		this.cId = cId;
		Cpwd = cpwd;
		Cname = cname;
		Cemail = cemail;
		this.cTel = cTel;
		this.cAge = cAge;
		this.grade = grade;
	}

	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getCpwd() {
		return Cpwd;
	}
	public void setCpwd(String cpwd) {
		Cpwd = cpwd;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String cname) {
		Cname = cname;
	}
	public String getCemail() {
		return Cemail;
	}
	public void setCemail(String cemail) {
		Cemail = cemail;
	}
	public String getcTel() {
		return cTel;
	}
	public void setcTel(String cTel) {
		this.cTel = cTel;
	}
	public int getcAge() {
		return cAge;
	}
	public void setcAge(int i) {
		this.cAge = i;
	}

	public List<BorrowList> getCborrowList() {
		return CborrowList;
	}

	public void setCborrowList(List<BorrowList> cborrowList) {
		CborrowList = cborrowList;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
