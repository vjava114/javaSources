package com.wallet.membership.model.custom;


public class MemberEvent {
	private String idx;
	private String notiId; //����ID
	private String notiTarget; //������� 00:��� 01:���޻� 02:�����
	private String id; // ���޻�ID, �����ID
	private String notiType; // 01:�̺�Ʈ, 02:����
	private String title; //����
	private String content; // ����
	private String imageUrl; 
	private String newNoti; //�űԿ���
	private String valSday; // ���ý�����(�̺�Ʈ�ϰ��)
	private String valEday; // ����������(�̺�Ʈ�ϰ��)
	private String displayYn; //���ÿ��� Y
	private String regUser; //�����
	private String regDtm; //�����
	private String chgUser; //������
	private String chgDtm; //������
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
