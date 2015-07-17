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
		
    URL url;//URL 주소 객체
    URLConnection connection;//URL접속을 가지는 객체
    InputStream is;//URL접속에서 내용을 읽기위한 Stream
    InputStreamReader isr;
    BufferedReader br;
    String result = "";
    
    try{
        //URL객체를 생성하고 해당 URL로 접속한다..
        url = new URL(urlPath);
        connection = url.openConnection();

        //내용을 읽어오기위한 InputStream객체를 생성한다..
        is = connection.getInputStream();
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);

        //내용을 읽어서 화면에 출력한다..
        String buf = null;
        while(true){
            buf = br.readLine();
            if(buf == null) break;
            result = buf;
        }
    }catch(MalformedURLException mue){
        System.err.println("잘못되 URL입니다.");
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



