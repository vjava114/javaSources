package com.amore.domain;

import java.util.Date;

// hera_event 테이블

public class Hera_eventVO {
	private String evt_code;
	private String brand;
	private String userno;
	private String phone;
	private int vcnt;
	private Date rdate;
	private String inst_dt;
	private String inst_tm;
	
	
	public String getEvt_code() {
		return evt_code;
	}
	public void setEvt_code(String evt_code) {
		this.evt_code = evt_code;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getUserno() {
		return userno;
	}
	public void setUser_no(String userno) {
		this.userno = userno;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getVcnt() {
		return vcnt;
	}
	public void setVcnt(int vcnt) {
		this.vcnt = vcnt;
	}
	public Date getRdate() {
		return rdate;
	}
	public void setRdate(Date rdate) {
		this.rdate = rdate;
	}
	public String getInst_dt() {
		return inst_dt;
	}
	public void setInst_dt(String inst_dt) {
		this.inst_dt = inst_dt;
	}
	public String getInst_tm() {
		return inst_tm;
	}
	public void setInst_tm(String inst_tm) {
		this.inst_tm = inst_tm;
	}


}
