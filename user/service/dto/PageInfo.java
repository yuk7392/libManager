package library_project.user.service.dto;

/* 도서 목록 페이징 정보 */
public class PageInfo {
	private int totalCount; 		// 총 게시물 개수
	private int listCount = 25;		// 한 페이지당 보여줄 게시물 개수
	private int totalPage;			// 총 페이지
	private int curPage=1;			// 현재 페이지(첫 생성시 1)

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		// 총 페이지 수
		totalPage = totalCount / listCount;
		if (totalCount % listCount > 0) 
			totalPage++;
			
	}

	public int getTotalCount() {
		return totalCount;
	}
	
	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

}
