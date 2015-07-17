package com.example.board.domain;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer no;
	private String content;
	private Date wdate;
	private String id;
	
	public Board(){}

	public Board(Integer no, String content, Date wdate, String id) {
		super();
		this.no = no;
		this.content = content;
		this.wdate = wdate;
		this.id = id;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Board [no=" + no + ", content=" + content + ", wdate=" + wdate
				+ ", id=" + id + "]";
	}
	
}
