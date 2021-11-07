package library_project.user.service.dto;

/* 도서 목록 */
public class BookDto {
	private int bIsbn;
	private int bId;
	private String bTitle;
	private String bGenre;
	private String bAuthor;
	private String bPublisher;
	private int bAmount;
	private String isRent;
	private int lid; // 대여번호 (대여,반납에 사용 )
	
	public int getbIsbn() {
		return bIsbn;
	}
	public void setbIsbn(int bIsbn) {
		this.bIsbn = bIsbn;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbGenre() {
		return bGenre;
	}
	public void setbGenre(String bGenre) {
		this.bGenre = bGenre;
	}
	public String getbAuthor() {
		return bAuthor;
	}
	public void setbAuthor(String bAutor) {
		this.bAuthor = bAutor;
	}
	public String getbPublisher() {
		return bPublisher;
	}
	public void setbPublisher(String bPublisher) {
		this.bPublisher = bPublisher;
	}
	public String getIsRent() {
		return isRent;
	}
	public void setIsRent(String isRent) {
		this.isRent = isRent;
	}
	public int getbAmount() {
		return bAmount;
	}
	public void setbAmount(int bAmount) {
		this.bAmount = bAmount;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	
}
