package library_project.user.service.dto;

/* 추천 도서 */
public class RCBookDto extends BookDto {
	private int rNum;
	private String rTitle;
	private String rAuthor;
	private String rPublisher;
	private String rReason;
	private int recommendNum;
	
	public int getrNum() {
		return rNum;
	}
	public void setrNum(int rNum) {
		this.rNum = rNum;
	}
	public String getrTitle() {
		return rTitle;
	}
	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}
	public String getrAuthor() {
		return rAuthor;
	}
	public void setrAuthor(String rAuthor) {
		this.rAuthor = rAuthor;
	}
	public String getrPublisher() {
		return rPublisher;
	}
	public void setrPublisher(String rPublisher) {
		this.rPublisher = rPublisher;
	}
	public String getrReason() {
		return rReason;
	}
	public void setrReason(String rReason) {
		this.rReason = rReason;
	}
	public int getRecommendNum() {
		return recommendNum;
	}
	public void setRecommendNum(int recommendNum) {
		this.recommendNum = recommendNum;
	}
	
	
}
