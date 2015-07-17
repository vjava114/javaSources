package com.amore.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserVO {
	
	
	
	private String user_no;
	private String uphone;
	private String name;
	private String angel_code;
	private String angel_name;
	private String coupon_cnt;
	private String shop_name;		// 보통 안쓰는 컬럼이지만, ap 등에서 사용된다.
	private String shop_phone;
	private String shop_code;
	private Date fdate;

	// herea_event 테이블 정보를 가져옴. ( 아이바티스에서 조인할때, hera_event 정보에 쉽게 접근할수 있음)
	private Hera_eventVO eventVO;
	public void setEventVO(Hera_eventVO eventVO) {
		this.eventVO = eventVO;
	}
	public String getShop_name() {
		return shop_name;
	}
	

	public String getUser_no() {
		return user_no;
	}

	public void setUser_no(String user_no) {
		this.user_no = user_no;
	}

	public String getUphone() {
		return uphone;
	}
	

	public void setUphone(String uphone) {
		this.uphone = uphone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAngel_code() {
		return angel_code;
	}

	public void setAngel_code(String angel_code) {
		this.angel_code = angel_code;
	}

	public String getAngel_name() {
		return angel_name;
	}

	public void setAngel_name(String angel_name) {
		this.angel_name = angel_name;
	}

	public String getCoupon_cnt() {
		return coupon_cnt;
	}

	public void setCoupon_cnt(String coupon_cnt) {
		this.coupon_cnt = coupon_cnt;
	}

	public Hera_eventVO getEventVO() {
		return eventVO;
	}

	

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	public String getShop_phone() {
		return shop_phone;
	}

	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}
	public String getShop_code() {
		return shop_code;
	}

	public void setShop_code(String shop_code) {
		this.shop_code = shop_code;
	}

	public Date getFdate() {
		return fdate;
	}

	public void setFdate(Date fdate) {
		this.fdate = fdate;
	}
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("UserVO [user_no=");
//		builder.append(user_no);
//		builder.append(", uphone=");
//		builder.append(uphone);
//		builder.append(", name=");
//		builder.append(name);
//		builder.append(", angel_code=");
//		builder.append(angel_code);
//		builder.append(", angel_name=");
//		builder.append(angel_name);
//		builder.append(", coupon_cnt=");
//		builder.append(coupon_cnt);
//		builder.append(", shop_name=");
//		builder.append(shop_name);
//		builder.append(", shop_phone=");
//		builder.append(shop_phone);
//		builder.append(", shop_code=");
//		builder.append(shop_code);
//		builder.append(", fdate=");
//		builder.append(fdate);
//		builder.append(", eventVO=");
//		builder.append(eventVO);
//		builder.append("]");
//		return builder.toString();
//	}
	
}
