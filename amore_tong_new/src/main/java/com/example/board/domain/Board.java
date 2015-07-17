package com.example.board.domain;

import java.io.Serializable;
import java.util.Date;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer no;
	private String title;
	private String content;
	private Date wdate;
	private Integer read_count;
	private Integer group_no;
	private Integer sequence_in_group;
	private Integer indent_in_group;
	private Integer ref_no;
	private String id;
	
	public Board() {
		this.no = 0;
		this.title = null;
		this.content = null;
		this.wdate = null;
		this.read_count = 0;
		this.group_no = 0;
		this.sequence_in_group = 0;
		this.indent_in_group = 0;
		this.ref_no = 0;
		this.id = null;
	}

	public Board(Integer no, String title, String content, Date wdate,
			Integer read_count, Integer group_no, Integer sequence_in_group,
			Integer indent_in_group, Integer ref_no, String id) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.read_count = read_count;
		this.group_no = group_no;
		this.sequence_in_group = sequence_in_group;
		this.indent_in_group = indent_in_group;
		this.ref_no = ref_no;
		this.id = id;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getRead_count() {
		return read_count;
	}

	public void setRead_count(Integer read_count) {
		this.read_count = read_count;
	}

	public Integer getGroup_no() {
		return group_no;
	}

	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}

	public Integer getSequence_in_group() {
		return sequence_in_group;
	}

	public void setSequence_in_group(Integer sequence_in_group) {
		this.sequence_in_group = sequence_in_group;
	}

	public Integer getIndent_in_group() {
		return indent_in_group;
	}

	public void setIndent_in_group(Integer indent_in_group) {
		this.indent_in_group = indent_in_group;
	}

	public Integer getRef_no() {
		return ref_no;
	}

	public void setRef_no(Integer ref_no) {
		this.ref_no = ref_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
