package com.example.util;

public class PagingHelper {
	
	private int recordsPerPage = 15;		// 페이지 당 레코드 갯수
	private int linksPerPage = 10;			// 페이지 하단에 표시될 페이지 링크의 갯수
	private int totalPage;					// 전체 페이지 수
	private String url = "index?page=%1d";	// 페이지 링크에 표시할 URL
	
	public PagingHelper(int totalRecord) {	// 생성자의 인자로 전체 레코드 갯수가 전달
		this.totalPage = (int)Math.ceil((double)totalRecord / recordsPerPage);
	}
	
	public int getRecordsPerPage() {
		return this.recordsPerPage;
	}
	
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	
	public int getLinksPerPage() {
		return this.linksPerPage;
	}
	
	public void setLinksPerPage(int linksPerPage) {
		this.linksPerPage = linksPerPage;
	}
	
	public int getTotalPage() {	// 전체 페이지 수를 리턴
		return this.totalPage;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPageLink(int page) { // 페이지 링크를 리턴
		int temp = (int)Math.ceil((double)page / linksPerPage) * linksPerPage;
		int firstPage = temp - (linksPerPage - 1);
		int lastPage = Math.min(temp, this.totalPage);
		
		StringBuilder builder = new StringBuilder();
		if(firstPage > 1) {
			builder.append("<a href='" + String.format(url, firstPage-1) + "'>◀이전</a>&nbsp;");
		}
		for(int i = firstPage; i <= lastPage; i++) {
			if(i == page) {
				builder.append("<span style='color:red;'><b>" + i + "</b></span>&nbsp;");
			} else {
				builder.append("<a href='" + String.format(url, i) + "'><b>" + i + "</b></a>&nbsp;");
			}
		}
		if(lastPage < this.totalPage) {
			builder.append("<a href='" + String.format(url, lastPage+1) + "'>다음▶</a>");
		}
		return builder.toString();
	}
}
