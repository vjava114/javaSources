package push;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetToken {
	// 구글 토큰 받아오는 메서드 (푸쉬 전송할때 필요 - 한번만 받아오면된다)
    public String getAuthToken() throws Exception {

        String authtoken = "";

        StringBuffer postDataBuilder = new StringBuffer();

        postDataBuilder.append("accountType=HOSTED_OR_GOOGLE"); // 똑같이 써주셔야 합니다.

        postDataBuilder.append("&Email=아이디@gmail.com"); // 개발자 구글 id

        postDataBuilder.append("&Passwd=패스워드"); // 개발자 구글 비빌번호

        postDataBuilder.append("&service=ac2dm");

        postDataBuilder.append("&source=test-1.0");

        byte[] postData = postDataBuilder.toString().getBytes("UTF8");

        URL url = new URL("https://www.google.com/accounts/ClientLogin");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoOutput(true);

        conn.setUseCaches(false);

        conn.setRequestMethod("POST");

        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        conn.setRequestProperty("Content-Length",
                Integer.toString(postData.length));

        OutputStream out = conn.getOutputStream();

        out.write(postData);

        out.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));

        String sidLine = br.readLine();

        String lsidLine = br.readLine();

        String authLine = br.readLine();

        System.out.println("sidLine----------->>>" + sidLine);

        System.out.println("lsidLine----------->>>" + lsidLine);

        System.out.println("authLine----------->>>" + authLine);

        System.out.println("AuthKey----------->>>"
                + authLine.substring(5, authLine.length()));

        authtoken = authLine.substring(5, authLine.length());

        return authtoken;

    }




}
