package Data.Service;

import java.util.ArrayList;
import java.util.HashMap;

import Data.bean.PushItemBean;
import Data.storage.biz.pushBiz;


// 안드로이드냐 아이폰이냐에 따라 서로다른 list에 저장..
public class PushImpl extends PushForm {
 
	ArrayList<PushItemBean> iphone;
	ArrayList<PushItemBean> android;
	
 
 
	public Object input(HashMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public Object output() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

 
	public ArrayList<PushItemBean> process() throws Exception {
		
		pushBiz  p = new pushBiz();
		ArrayList<HashMap<String, String>> o = new ArrayList<HashMap<String, String>>();
		ArrayList<PushItemBean> io = new ArrayList<PushItemBean>();
		PushItemBean bean = null;
		 
		try {
			o = p.select(null);
 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
			for(int i = 0;i < o.size();i++)
			{
 		 
 			     bean = new PushItemBean();
				 bean.setMsg(String.valueOf(o.get(i).get("msg")));
				 bean.setPush_id(String.valueOf(o.get(i).get("pushid")));
				 bean.setType(String.valueOf(o.get(i).get("type")));
				 bean.setTitle(String.valueOf(o.get(i).get("mtitle")));
				 bean.setMsg_seq(String.valueOf(o.get(i).get("msg_seq")));
				 bean.setMember_seq(String.valueOf(o.get(i).get("member_seq")));
				 bean.setSeq(String.valueOf(o.get(i).get("seq")));
				 bean.setStatus(String.valueOf(o.get(i).get("status")));
				 bean.setPush_type(String.valueOf(o.get(i).get("push_type")));
				 bean.setPush_url(String.valueOf(o.get(i).get("push_url")));				 
				 bean.setPush_number(String.valueOf(o.get(i).get("push_number")));
				 
				 bean.setMl_retry(String.valueOf(o.get(i).get("ml_retry")));
				 bean.setPl_retry(String.valueOf(o.get(i).get("pl_retry")));
				 bean.setPhone(String.valueOf(o.get(i).get("phone")));
				 
				 bean.setReserv_date(String.valueOf(o.get(i).get("reserv_date")));
				 bean.setReserv_yn(String.valueOf(o.get(i).get("reserv_yn")));
				 
				 bean.setLms_title(String.valueOf(o.get(i).get("lms_title")));
				 bean.setLms_msg(String.valueOf(o.get(i).get("lms_msg")));
				 bean.setReplace_yn(String.valueOf(o.get(i).get("replace_yn")));
				 
					
				 io.add(i, bean);
 
			}
		
		 System.out.println("======>io size [" + io.size() + "]"); 
		 		 
		return io;
	}
 
	@SuppressWarnings("unchecked")
	public String  run() throws Exception {
		// TODO Auto-generated method stub
		
		ArrayList<PushItemBean> buff = new ArrayList<PushItemBean>();
		android = new ArrayList<PushItemBean>();
		iphone = new ArrayList<PushItemBean>();
		
		buff =  process();
		//System.out.println("======>buff size [" + buff.size() + "]"); 
		for(int j = 0 ; j < buff.size();j++){
			
//		 	 System.out.println("======> No[" + j + "] [" +buff.get(j).getType() + "]");
//		 	 System.out.println("======>no [" +  j + "] [" + buff.get(j).getMember_seq() + "]");
			if(buff.get(j).getType().equals("android") )
			{
//				 System.out.println("======>  [getType : " +buff.get(j).getType() + "]");
//				 System.out.println("======>  [getMember_seq : " +buff.get(j).getMember_seq() + "]");
//				 System.out.println("======>  [getMsg : " +buff.get(j).getMsg() + "]");
//				 System.out.println("======>  [getMsg_seq : " +buff.get(j).getMsg_seq() + "]");
//				 System.out.println("======>  [getPush_id : " +buff.get(j).getPush_id()  + "]");
//				 System.out.println("======>  [getTitle : " +buff.get(j).getTitle()+ "]");
//				 System.out.println("======>  [getSeq : " +buff.get(j).getSeq()+ "]");

				 android.add(buff.get(j));
				
			}
			else  {
//				 System.out.println("======>  [getType : " +buff.get(j).getType() + "]");
//				 System.out.println("======>  [getMember_seq : " +buff.get(j).getMember_seq() + "]");
//				 System.out.println("======>  [getMsg : " +buff.get(j).getMsg() + "]");
//				 System.out.println("======>  [getMsg_seq : " +buff.get(j).getMsg_seq() + "]");
//				 System.out.println("======>  [getPush_id : " +buff.get(j).getPush_id()  + "]");
//				 System.out.println("======>  [getTitle : " +buff.get(j).getTitle()+ "]");
//				 System.out.println("======>  [getSeq : " +buff.get(j).getSeq()+ "]");
				iphone.add(buff.get(j));
				
			}
		}
 	
		
		return null;
 
	}

	public ArrayList<PushItemBean> getIphone() {
		return iphone;
	}

	public ArrayList<PushItemBean> getAndroid() {
		return android;
	}

	
	
}
