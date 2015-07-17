package com.wallet.admin.model;

import com.rocomo.common.model.Common;
import java.util.Date;

public class MwAdAccessLog extends Common {
   
    private String insTm;			/*등록일시*/
    private String part;			/*소속*/
    private String adminId;		/*등록id*/
    private String msg;				/*내용*/
    
		public String getInsTm() {
			return insTm;
		}
		public void setInsTm(String insTm) {
			this.insTm = insTm;
		}
		public String getPart() {
			return part;
		}
		public void setPart(String part) {
			this.part = part;
		}
		public String getAdminId() {
			return adminId;
		}
		public void setAdminId(String adminId) {
			this.adminId = adminId;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
    

}