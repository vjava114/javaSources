package com.example.util;

public class PagingHelper {
	
	private int recordsPerPage = 15;		// ������ �� ���ڵ� ����
	private int linksPerPage = 10;			// ������ �ϴܿ� ǥ�õ� ������ ��ũ�� ����
	private int totalPage;					// ��ü ������ ��
	private String url = "index?page=%1d";	// ������ ��ũ�� ǥ���� URL
	
	public PagingHelper(int totalRecord) {	// �������� ���ڷ� ��ü ���ڵ� ������ ����
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
	
	public int getTotalPage() {	// ��ü ������ ���� ����
		return this.totalPage;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPageLink(int page) { // ������ ��ũ�� ����
		int temp = (int)Math.ceil((double)page / linksPerPage) * linksPerPage;
		int firstPage = temp - (linksPerPage - 1);
		int lastPage = Math.min(temp, this.totalPage);
		
		StringBuilder builder = new StringBuilder();
		if(firstPage > 1) {
			builder.append("<a href='" + String.format(url, firstPage-1) + "'>������</a>&nbsp;");
		}
		for(int i = firstPage; i <= lastPage; i++) {
			if(i == page) {
				builder.append("<span style='color:red;'><b>" + i + "</b></span>&nbsp;");
			} else {
				builder.append("<a href='" + String.format(url, i) + "'><b>" + i + "</b></a>&nbsp;");
			}
		}
		if(lastPage < this.totalPage) {
			builder.append("<a href='" + String.format(url, lastPage+1) + "'>������</a>");
		}
		return builder.toString();
	}
}
