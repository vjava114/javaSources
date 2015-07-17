package push;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;

import org.apache.tools.ant.taskdefs.Sleep;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

import constants.ConfigProject;

public class GCM {
	public static Result sender(String regId  , String msg, HashMap<String, String> map ) {
		
		String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);	 //메시지 고유 ID
		boolean SHOW_ON_IDLE = false;	 //기기가 활성화 상태일때 보여줄것인지 (갤럭시는 false일때, 델스트릭은 true일때 유실율이 낮았다.)
		
		int LIVE_TIME = 1800;	 //기기가 비활성화 상태일때 GCM가 메시지를 유효화하는 시간
		int RETRY = 3;	 //메시지 전송실패시 재시도 횟수
		Result result = null;	// Sender 후 결과값 반환.
		
		
//////////////// 지워라!!!! ////////////////////
//		map.put("title", msg);
//		map.put("msgseq", "172");
//		map.put("userseq", "14");
//		map.put("push_type", "X");
//		map.put("push_url", "");
//		
//		regId = "APA91bHDmnomrgJOGQyOTv3LEMdiuD6Lyg9MCaPkFtvYBC8klASGsAlRzPxlM2xr318v7731--wO8JnpqyUr7rHQK-dcHjyKhjq_OKzjGe0fiFYZnz2QSP1xYIGjrYdWzgE1I2Lm_GYf";
////////////////// 지워라!!!! ////////////////////		
		
		
		Message message = new Message.Builder()
		//.collapseKey("collapsekey" + System.currentTimeMillis())
		.delayWhileIdle(SHOW_ON_IDLE)
		.timeToLive(LIVE_TIME)
		.addData("msg",msg)
		.addData("title",map.get("title"))
		.addData("msgseq",map.get("msgseq"))
		.addData("userseq",map.get("userseq"))
		.addData("push_type",map.get("push_type"))
		.addData("push_url",map.get("push_url"))
		.build();
		
		System.out.println("전달값 : " + message.toString());

		try {
				
			Sender sender = new Sender(ConfigProject.getANDROID_APIID());			// 먼저 앱아이디 등록
			result = sender.send(message, regId, RETRY);
			System.out.println("안드로이드 송신 결과 : " + result.toString());
			// System.out.println("[결과 : " + result + "]");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("에러코드 확인하자!! : " + result.getMessageId());
		return result;
	}
	
	
	
	////////// 여기도 지워라//////////////////
//	public static void main(String args[]){
//		HashMap<String, String> map = new HashMap<String, String>();
//		for(int i=0; i<1; i++)
//		{
//			String msg = String.valueOf(i);
//			sender("a","a:"+msg+"번째!",map);
//			
//		}
//		
//		
//		
//	}
	//////////여기도 지워라//////////////////


}
