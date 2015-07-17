package com.wallet.membership.common;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.wallet.common.util.Log;
import com.wallet.common.util.PropertiesUtil;
import com.wallet.common.web.CommonController;

public class UpdateStore extends CommonController{
	private Logger log = Log.getLogger("logs");
	public UpdateStore() {
		// TODO Auto-generated constructor stub
	}
	public boolean update(String store_id , String Brand_Id, String setStarPayMembDc, String setStarPayMembUse, String setStarPayMembSave,String Mode){
		log.info("update complex Infomation........");
		log.info("updating storeId = " + store_id);
		log.info("updating brandId = " + Brand_Id);
		log.info("updating ollehUseYn = " + setStarPayMembUse);
		log.info("updating ollehDcYn = " + setStarPayMembDc);
		log.info("updating ollehSaveYn = " + setStarPayMembSave);
		log.info("updating Mode = " + Mode);

		String path = PropertiesUtil.get("memb_cpn_server_host");
		String Url = path+"/mcserver/adStoreControl.do?serviceMode="+Mode+"&kShopId="+store_id+"&pkShopId="+Brand_Id 
				+"&ollehUseYn="+setStarPayMembUse+"&ollehDcYn="+setStarPayMembDc+"&ollehSaveYn="+setStarPayMembSave;
		
		log.info("update Url : " + Url);
		NetManager netManager = new NetManager(Url);
		netManager.getGETConnention();
		JSONObject Obj = netManager.getJSON();
		try{
			JSONObject Header = Obj.getJSONObject("header");
			String Result = Header.getString("code");
			log.info("update result Json  : " + netManager.getString());
			if(Result.equals("0000")){
				return true;
			}else{
				log.info("updating fail..");
				return false;
			}
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	// 다수의 가맹점 등록
	public boolean update(ArrayList<String> store_id , String Brand_Id, String setStarPayMembDc, 
			String setStarPayMembUse, String setStarPayMembSave, String Mode){
		log.info("update complex Infomation........");
		for(int i=0;i<store_id.size();i++){
			log.info("updating count : " + i + "  storeId : " + store_id);
			if(!update(store_id.get(i) , Brand_Id, setStarPayMembDc,setStarPayMembUse,setStarPayMembSave,Mode)){
				return false;
			}
		}
		return true;
	}
	
}
