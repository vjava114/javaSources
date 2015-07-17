package com.amore.domain;

public class Evt_CodeVO {
	
	// 셀렉트박스에 입력시킬 evt_code만 hera_event 테이블에서 가져옵니다.
	// Hera_eventVO 와 헷갈리지 않게 주의!!
	
	private String brand;
	private String evt_code;
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getEvt_code() {
		return evt_code;
	}
	public void setEvt_code(String evt_code) {
		this.evt_code = evt_code;
	}
	
	
}
