package library_project.user.service.dto;

/* 요청 사항 */
public class OrderBookDto {
	private int oId;
	private String oName;
	private String oUserid;
	private String oTitle;
	private String oPublisher;
	private String oReason;
	private String oEmail;
	private String oStatus;
	private String oAnswer;
	
	
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getoUserid() {
		return oUserid;
	}
	public void setoUserid(String oUserid) {
		this.oUserid = oUserid;
	}
	public String getoTitle() {
		return oTitle;
	}
	public void setoTitle(String oTitle) {
		this.oTitle = oTitle;
	}
	public String getoPublisher() {
		return oPublisher;
	}
	public void setoPublisher(String oPublisher) {
		this.oPublisher = oPublisher;
	}
	public String getoReason() {
		return oReason;
	}
	public void setoReason(String oReason) {
		this.oReason = oReason;
	}
	public String getoEmail() {
		return oEmail;
	}
	public void setoEmail(String oEmail) {
		this.oEmail = oEmail;
	}
	public String getoStatus() {
		return oStatus;
	}
	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}
	public String getoAnswer() {
		return oAnswer;
	}
	public void setoAnswer(String oAnswer) {
		this.oAnswer = oAnswer;
	}
	
}
