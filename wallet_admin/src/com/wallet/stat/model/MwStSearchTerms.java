package com.wallet.stat.model;

import com.rocomo.common.model.Common;

public class MwStSearchTerms extends Common {

    private String sday;		//��¥ - ������
    private String eday;		//��¥ - ������
    private String period;	//�˻����� : �Ϻ�,����,�⵵��,���� ��
    private String kind;		//�˻����� : �ܸ�����, �̿��ڱ���, ��ī���� ��
    
    
		public String getSday() {
			return sday;
		}
		public void setSday(String sday) {
			this.sday = sday;
		}
		public String getEday() {
			return eday;
		}
		public void setEday(String eday) {
			this.eday = eday;
		}
		public String getPeriod() {
			return period;
		}
		public void setPeriod(String period) {
			this.period = period;
		}
		public String getKind() {
			return kind;
		}
		public void setKind(String kind) {
			this.kind = kind;
		}
   
}