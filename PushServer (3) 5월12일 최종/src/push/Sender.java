//package push;
//
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.sql.ResultSet;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLSession;
//
//import com.google.android.gcm.server.Message;
//
//public class Sender {
//	// 푸쉬 전송하는 메서드 (개별 핸드폰 키값 , 구글 토큰 값, 보낼 메시지)
//	public void sender(String regId, String authToken, String msg)
//	            throws Exception {
//
//	        StringBuffer postDataBuilder = new StringBuffer();
//
//	        postDataBuilder.append("registration_id=" + regId); // 등록ID
//
//	        postDataBuilder.append("&collapse_key=1");
//
//	        postDataBuilder.append("&delay_while_idle=1");
//
//	        postDataBuilder.append("&data.msg=" + URLEncoder.encode(msg, "UTF-8")); // 태울
//	                                                                                // 메시지
//
//	        byte[] postData = postDataBuilder.toString().getBytes("UTF8");
//
//	        String cd2m_url = "https://android.apis.google.com/c2dm/send";
//	        
//	        URL url = new URL( cd2m_url);
//	        HttpsURLConnection.setDefaultHostnameVerifier(new CustomVF()); 
//	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//	        conn.setDoOutput(true);
//
//	        conn.setUseCaches(false);
//
//	        conn.setRequestMethod("POST");
//
//	        conn.setRequestProperty("Content-Type",
//	                "application/x-www-form-urlencoded");
//
//	        conn.setRequestProperty("Content-Length",
//	                Integer.toString(postData.length));
//
//	        conn.setRequestProperty("Authorization", "GoogleLogin auth="
//	                + authToken);
//
//	        OutputStream out = conn.getOutputStream();
//	        System.out.println("JAVA C2DM OUTPUT >>>>>>>[" + postData + "]");
//	        out.write(postData);
//
//	        out.close();
//
//	        conn.getInputStream();
//
//	    }
//
//
//	private static class CustomVF implements HostnameVerifier {
//		 
//		  public boolean verify(String hostname, SSLSession session) {
//		   // TODO Auto-generated method stub
//		   return true;
//		  }
//
//	 
//	}
//
//
//}
