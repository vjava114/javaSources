package com.example.board.domain;

public class PageHelper {

	public static final int LINES_PER_PAGE = 15;
	private int begin;
	private int size;
	
	public PageHelper(int page) {
		this.begin = (page-1) * LINES_PER_PAGE;
		this.size = LINES_PER_PAGE;
	}

	public int getBegin() {
		return begin;
	}

	public int getSize() {
		return size;
	}
	
}
