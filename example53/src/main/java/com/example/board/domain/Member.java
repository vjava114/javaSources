package com.example.board.domain;

import java.io.Serializable;


/*
 *  Serializable ?
 *  ==> 객체를 메모리에 담기 위해서 직렬화가 필요함.
 *  session 같은경우 was끼리 객체를 공유해야하는 경우가 생기는데,
 *  
 *  1. 객체는 통신으로 전달이 안되기 때문에 직렬화 후 통신하는것이다.
 *  2. 파일로 OutPut 할때에도 쓰임.
 */
public class Member implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String password;
	private String email;
	private String authority;
	
	public Member(){}

	public Member(String id, String password, String email, String authority) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.authority = authority;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", email="
				+ email + ", authority=" + authority + "]";
	}

}
