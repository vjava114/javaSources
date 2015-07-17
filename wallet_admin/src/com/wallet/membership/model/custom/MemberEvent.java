package com.wallet.membership.model.custom;


public class MemberEvent {
	private String idx;
	private String notiId; //공지ID
	private String notiTarget; //공지대상 00:모두 01:제휴사 02:멤버십
	private String id; // 제휴사ID, 멤버십ID
	private String notiType; // 01:이벤트, 02:공지
	private String title; //제목
	private String content; // 내용
	private String imageUrl; 
	private String newNoti; //신규여부
	private String valSday; // 전시시작일(이벤트일경우)
	private String valEday; // 전시종료일(이벤트일경우)
	private String displayYn; //전시여부 Y
	private String regUser; //등록자
	private String regDtm; //등록일
	private String chgUser; //수정자
	private String chgDtm; //수정일
	private String membId;
	private String name;
	public String getIdx() {
		return idx;
	}
	public void setIdx(String idx) {
		this.idx = idx;
	}
	public String getNotiId() {
		return notiId;
	}
	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}
	public String getNotiTarget() {
		return notiTarget;
	}
	public void setNotiTarget(String notiTarget) {
		this.notiTarget = notiTarget;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNotiType() {
		return notiType;
	}
	public void setNotiType(String notiType) {
		this.notiType = notiType;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getNewNoti() {
		return newNoti;
	}
	public void setNewNoti(String newNoti) {
		this.newNoti = newNoti;
	}
	public String getValSday() {
		return valSday;
	}
	public void setValSday(String valSday) {
		this.valSday = valSday;
	}
	public String getValEday() {
		return valEday;
	}
	public void setValEday(String valEday) {
		this.valEday = valEday;
	}
	public String getDisplayYn() {
		return displayYn;
	}
	public void setDisplayYn(String displayYn) {
		this.displayYn = displayYn;
	}
	public String getRegUser() {
		return regUser;
	}
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}
	public String getRegDtm() {
		return regDtm;
	}
	public void setRegDtm(String regDtm) {
		this.regDtm = regDtm;
	}
	public String getChgUser() {
		return chgUser;
	}
	public void setChgUser(String chgUser) {
		this.chgUser = chgUser;
	}
	public String getChgDtm() {
		return chgDtm;
	}
	public void setChgDtm(String chgDtm) {
		this.chgDtm = chgDtm;
	}
	public String getMembId() {
		return membId;
	}
	public void setMembId(String membId) {
		this.membId = membId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
