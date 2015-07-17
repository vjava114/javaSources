package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStPayStats extends Common {

	private String day;				//��¥
  private Integer k1;				//�ȵ���̵�(KT) ����
  private Integer k2;				//�ȵ���̵�(KT) ��ƽ
  private Integer k3;
  private Integer k4;
  private Integer s1;				//�ȵ���̵�(SKT) ����
  private Integer s2;				//�ȵ���̵�(SKT) ��ƽ
  private Integer s3;
  private Integer s4;
  private Integer l1;				//�ȵ���̵�(LGU+) ����
  private Integer l2;				//�ȵ���̵�(LGU+) ��ƽ
  private Integer l3;
  private Integer l4;
  private Integer sumK;			//�ȵ���̵�(KT) ����
  private Integer sumS;			//�ȵ���̵�(SKT) ����
  private Integer sumL;			//�ȵ���̵�(LGU+) ����
  private Integer b1;				//���ڵ� ��ȸ�Ǽ� ����
  private Integer b2;				//���ڵ� ��ȸ�Ǽ� ��ƽ
  private Integer b3;
  private Integer b4;
  private Integer sunjngK;	//�ȵ���̵�(KT) ����
  private Integer sunjngS;	//�ȵ���̵�(SKT) ����
  private Integer sunjngL;	//�ȵ���̵�(LGU+) ����
  private Integer ksl1;			//�ȵ���̵� �հ� ����
  private Integer ksl2;			//�ȵ���̵� �հ� ��ƽ
  private Integer sumKsl;		//�ȵ���̵� �հ� ����
  private Integer BsumKsl;	//�ȵ���̵� �հ� ���� ����
  private Integer sunjngKsl;//�ȵ���̵� �հ� ���� (�ȵ���̵� �հ� ���� - ���� ����)
 

	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Integer getK1() {
		return k1;
	}
	public void setK1(Integer k1) {
		this.k1 = k1;
	}
	public Integer getK2() {
		return k2;
	}
	public void setK2(Integer k2) {
		this.k2 = k2;
	}
	public Integer getK3() {
		return k3;
	}
	public void setK3(Integer k3) {
		this.k3 = k3;
	}
	public Integer getK4() {
		return k4;
	}
	public void setK4(Integer k4) {
		this.k4 = k4;
	}
	public Integer getS1() {
		return s1;
	}
	public void setS1(Integer s1) {
		this.s1 = s1;
	}
	public Integer getS2() {
		return s2;
	}
	public void setS2(Integer s2) {
		this.s2 = s2;
	}
	public Integer getS3() {
		return s3;
	}
	public void setS3(Integer s3) {
		this.s3 = s3;
	}
	public Integer getS4() {
		return s4;
	}
	public void setS4(Integer s4) {
		this.s4 = s4;
	}
	public Integer getL1() {
		return l1;
	}
	public void setL1(Integer l1) {
		this.l1 = l1;
	}
	public Integer getL2() {
		return l2;
	}
	public void setL2(Integer l2) {
		this.l2 = l2;
	}
	public Integer getL3() {
		return l3;
	}
	public void setL3(Integer l3) {
		this.l3 = l3;
	}
	public Integer getL4() {
		return l4;
	}
	public void setL4(Integer l4) {
		this.l4 = l4;
	}
	public Integer getSumK() {
		return sumK;
	}
	public void setSumK(Integer sumK) {
		this.sumK = sumK;
	}
	public Integer getSumS() {
		return sumS;
	}
	public void setSumS(Integer sumS) {
		this.sumS = sumS;
	}
	public Integer getSumL() {
		return sumL;
	}
	public void setSumL(Integer sumL) {
		this.sumL = sumL;
	}
	public Integer getB1() {
		return b1;
	}
	public void setB1(Integer b1) {
		this.b1 = b1;
	}
	public Integer getB2() {
		return b2;
	}
	public void setB2(Integer b2) {
		this.b2 = b2;
	}
	public Integer getB3() {
		return b3;
	}
	public void setB3(Integer b3) {
		this.b3 = b3;
	}
	public Integer getB4() {
		return b4;
	}
	public void setB4(Integer b4) {
		this.b4 = b4;
	}
	public Integer getSunjngK() {
		return sunjngK;
	}
	public void setSunjngK(Integer sunjngK) {
		this.sunjngK = sunjngK;
	}
	public Integer getSunjngS() {
		return sunjngS;
	}
	public void setSunjngS(Integer sunjngS) {
		this.sunjngS = sunjngS;
	}
	public Integer getSunjngL() {
		return sunjngL;
	}
	public void setSunjngL(Integer sunjngL) {
		this.sunjngL = sunjngL;
	}
	public Integer getKsl1() {
		return ksl1;
	}
	public void setKsl1(Integer ksl1) {
		this.ksl1 = ksl1;
	}
	public Integer getKsl2() {
		return ksl2;
	}
	public void setKsl2(Integer ksl2) {
		this.ksl2 = ksl2;
	}
	public Integer getSumKsl() {
		return sumKsl;
	}
	public void setSumKsl(Integer sumKsl) {
		this.sumKsl = sumKsl;
	}
	public Integer getBsumKsl() {
		return BsumKsl;
	}
	public void setBsumKsl(Integer bsumKsl) {
		BsumKsl = bsumKsl;
	}
	public Integer getSunjngKsl() {
		return sunjngKsl;
	}
	public void setSunjngKsl(Integer sunjngKsl) {
		this.sunjngKsl = sunjngKsl;
	}
	
}