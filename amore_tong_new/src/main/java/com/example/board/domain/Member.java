package com.example.board.domain;

import java.io.Serializable;

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
