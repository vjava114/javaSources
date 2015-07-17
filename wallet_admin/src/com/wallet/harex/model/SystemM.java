package com.wallet.harex.model;

import com.rocomo.common.model.Common;

@SuppressWarnings("serial")
public class SystemM extends Common{
	private String processId;	
	private String hostName;   
	private String processName;
	private String ifMethod;   
	private String sslYn;      
	private String sslYnNm;      
	private String ip;         
	private String port;       
	private String httpUrl;    
	private String monInterval;
	private String regDttm;    
	private String lastDttm;  
	private Integer seq;
	
	private String operCompOper;     
	private String chargeDeptOper;   
	private String chargeNameOper;   
	private String phoneNoOper;      
	private String compTelOper;      
	private String msgOper;          
	private String smsYnOper;        
	private String smsYnOperNm;        
	private String rcvPhoneNoOper;   
	private String emailYnOperNm;      
	private String emailYnOper;      
	private String rcvEmailOper;     
	private String seqIf;            
	private String ifModuleNameIf;   
	private String target_hostNameIf;
	private String targetIpIf;       
	private String targetPortIf;     
	private String ifMethodIf;       
	private String sslYnIf;          
	private String sslYnIfNm;          
	private String regDttmIf;        
	private String lastDttmIf;
	
	
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getIfMethod() {
		return ifMethod;
	}
	public void setIfMethod(String ifMethod) {
		this.ifMethod = ifMethod;
	}
	public String getSslYn() {
		return sslYn;
	}
	public void setSslYn(String sslYn) {
		this.sslYn = sslYn;
	}
	public String getSslYnNm() {
		return sslYnNm;
	}
	public void setSslYnNm(String sslYnNm) {
		this.sslYnNm = sslYnNm;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getHttpUrl() {
		return httpUrl;
	}
	public void setHttpUrl(String httpUrl) {
		this.httpUrl = httpUrl;
	}
	public String getMonInterval() {
		return monInterval;
	}
	public void setMonInterval(String monInterval) {
		this.monInterval = monInterval;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getLastDttm() {
		return lastDttm;
	}
	public void setLastDttm(String lastDttm) {
		this.lastDttm = lastDttm;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getOperCompOper() {
		return operCompOper;
	}
	public void setOperCompOper(String operCompOper) {
		this.operCompOper = operCompOper;
	}
	public String getChargeDeptOper() {
		return chargeDeptOper;
	}
	public void setChargeDeptOper(String chargeDeptOper) {
		this.chargeDeptOper = chargeDeptOper;
	}
	public String getChargeNameOper() {
		return chargeNameOper;
	}
	public void setChargeNameOper(String chargeNameOper) {
		this.chargeNameOper = chargeNameOper;
	}
	public String getPhoneNoOper() {
		return phoneNoOper;
	}
	public void setPhoneNoOper(String phoneNoOper) {
		this.phoneNoOper = phoneNoOper == null ? null : phoneNoOper.trim().replace("-", "");
	}
	public String getCompTelOper() {
		return compTelOper;
	}
	public void setCompTelOper(String compTelOper) {
		this.compTelOper = compTelOper == null ? null : compTelOper.trim().replace("-", "");
	}
	public String getMsgOper() {
		return msgOper;
	}
	public void setMsgOper(String msgOper) {
		this.msgOper = msgOper;
	}
	public String getSmsYnOper() {
		return smsYnOper;
	}
	public void setSmsYnOper(String smsYnOper) {
		this.smsYnOper = smsYnOper;
	}
	public String getSmsYnOperNm() {
		return smsYnOperNm;
	}
	public void setSmsYnOperNm(String smsYnOperNm) {
		this.smsYnOperNm = smsYnOperNm;
	}
	public String getRcvPhoneNoOper() {
		return rcvPhoneNoOper;
	}
	public void setRcvPhoneNoOper(String rcvPhoneNoOper) {
		this.rcvPhoneNoOper = rcvPhoneNoOper == null ? null : rcvPhoneNoOper.trim().replace("-", "");
	}
	public String getEmailYnOperNm() {
		return emailYnOperNm;
	}
	public void setEmailYnOperNm(String emailYnOperNm) {
		this.emailYnOperNm = emailYnOperNm;
	}
	public String getRcvEmailOper() {
		return rcvEmailOper;
	}
	public void setRcvEmailOper(String rcvEmailOper) {
		this.rcvEmailOper = rcvEmailOper == null ? null : rcvEmailOper.trim();
	}
	public String getSeqIf() {
		return seqIf;
	}
	public void setSeqIf(String seqIf) {
		this.seqIf = seqIf;
	}
	public String getIfModuleNameIf() {
		return ifModuleNameIf;
	}
	public void setIfModuleNameIf(String ifModuleNameIf) {
		this.ifModuleNameIf = ifModuleNameIf;
	}
	public String getTarget_hostNameIf() {
		return target_hostNameIf;
	}
	public void setTarget_hostNameIf(String target_hostNameIf) {
		this.target_hostNameIf = target_hostNameIf;
	}
	public String getTargetIpIf() {
		return targetIpIf;
	}
	public void setTargetIpIf(String targetIpIf) {
		this.targetIpIf = targetIpIf;
	}
	public String getTargetPortIf() {
		return targetPortIf;
	}
	public void setTargetPortIf(String targetPortIf) {
		this.targetPortIf = targetPortIf;
	}
	public String getIfMethodIf() {
		return ifMethodIf;
	}
	public void setIfMethodIf(String ifMethodIf) {
		this.ifMethodIf = ifMethodIf;
	}
	public String getSslYnIf() {
		return sslYnIf;
	}
	public void setSslYnIf(String sslYnIf) {
		this.sslYnIf = sslYnIf;
	}
	public String getSslYnIfNm() {
		return sslYnIfNm;
	}
	public void setSslYnIfNm(String sslYnIfNm) {
		this.sslYnIfNm = sslYnIfNm;
	}
	public String getRegDttmIf() {
		return regDttmIf;
	}
	public void setRegDttmIf(String regDttmIf) {
		this.regDttmIf = regDttmIf;
	}
	public String getLastDttmIf() {
		return lastDttmIf;
	}
	public void setLastDttmIf(String lastDttmIf) {
		this.lastDttmIf = lastDttmIf;
	}
	public String getEmailYnOper() {
		return emailYnOper;
	}
	public void setEmailYnOper(String emailYnOper) {
		this.emailYnOper = emailYnOper;
	}       
	
}

	