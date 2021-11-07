package library_project.login.service.dto;

/* 대여 목록*/
public class BorrowList {
	private int lid; // 대여 번호
	private String borrower; // 대여자 id
	private int bid; // 대여한 책 번호
	
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getBorrower() {
		return borrower;
	}
	public void setBorrower(String borrower) {
		this.borrower = borrower;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	
}
