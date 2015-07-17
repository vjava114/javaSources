package com.wallet.common.util;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class UrlConnection{
	
	public static String connect(String urlPath) {
		
		Log.debug(" ### UrlConnection connect START ### ");
		
    URL url;//URL �ּ� ��ü
    URLConnection connection;//URL������ ������ ��ü
    InputStream is;//URL���ӿ��� ������ �б����� Stream
    InputStreamReader isr;
    BufferedReader br;
    String result = "";
    
    try{
        //URL��ü�� �����ϰ� �ش� URL�� �����Ѵ�..
        url = new URL(urlPath);
        connection = url.openConnection();

        //������ �о�������� InputStream��ü�� �����Ѵ�..
        is = connection.getInputStream();
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);

        //������ �о ȭ�鿡 ����Ѵ�..
        String buf = null;
        while(true){
            buf = br.readLine();
            if(buf == null) break;
            result = buf;
        }
    }catch(MalformedURLException mue){
        System.err.println("�߸��� URL�Դϴ�.");
        System.exit(1);
    }catch(IOException ioe){
        System.err.println("IOException " + ioe);
        ioe.printStackTrace();
        System.exit(1);
    }
    
    Log.debug(" ### UrlConnection connect START ### ");
    
    return result;
	}
}



