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
		
		String MESSAGE_ID = String.valueOf(Math.random() % 100 + 1);	 //�޽��� ���� ID
		boolean SHOW_ON_IDLE = false;	 //��Ⱑ Ȱ��ȭ �����϶� �����ٰ����� (�����ô� false�϶�, ����Ʈ���� true�϶� �������� ���Ҵ�.)
		
		int LIVE_TIME = 1800;	 //��Ⱑ ��Ȱ��ȭ �����϶� GCM�� �޽����� ��ȿȭ�ϴ� �ð�
		int RETRY = 3;	 //�޽��� ���۽��н� ��õ� Ƚ��
		Result result = null;	// Sender �� ����� ��ȯ.
		
		
//////////////// ������!!!! ////////////////////
//		map.put("title", msg);
//		map.put("msgseq", "172");
//		map.put("userseq", "14");
//		map.put("push_type", "X");
//		map.put("push_url", "");
//		
//		regId = "APA91bHDmnomrgJOGQyOTv3LEMdiuD6Lyg9MCaPkFtvYBC8klASGsAlRzPxlM2xr318v7731--wO8JnpqyUr7rHQK-dcHjyKhjq_OKzjGe0fiFYZnz2QSP1xYIGjrYdWzgE1I2Lm_GYf";
////////////////// ������!!!! ////////////////////		
		
		
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
		
		System.out.println("���ް� : " + message.toString());

		try {
				
			Sender sender = new Sender(ConfigProject.getANDROID_APIID());			// ���� �۾��̵� ���
			result = sender.send(message, regId, RETRY);
			System.out.println("�ȵ���̵� �۽� ��� : " + result.toString());
			// System.out.println("[��� : " + result + "]");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�����ڵ� Ȯ������!! : " + result.getMessageId());
		return result;
	}
	
	
	
	////////// ���⵵ ������//////////////////
//	public static void main(String args[]){
//		HashMap<String, String> map = new HashMap<String, String>();
//		for(int i=0; i<1; i++)
//		{
//			String msg = String.valueOf(i);
//			sender("a","a:"+msg+"��°!",map);
//			
//		}
//		
//		
//		
//	}
	//////////���⵵ ������//////////////////


}
