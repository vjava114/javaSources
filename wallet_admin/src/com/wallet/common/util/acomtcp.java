package com.wallet.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class acomtcp {

	/**소켓 연결**/
	public static Socket InitTcpPortAsClient(String url, String port) throws Exception {
		Socket client=null;
		client = new Socket(url, Integer.parseInt(port));
		return client;
	}

	/**output 보내기**/
	public static void OutputSocket(Socket socket, String prarm, int off, int len){
		//		byte[] oxl = new byte[2];
		//		oxl[0] = (byte)0x00;
		//		oxl[1] = (byte)0xd2;
		try {
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));		
			//			dos.write(oxl, off, 2);
			dos.writeBytes(prarm);
			
//			dos.write(prarm.getBytes(), off, len);
			
//			byte[] bytes = prarm.getBytes();
//			
			Log.debug("send String : " + prarm.getBytes().length);
			
			dos.flush();
		} catch (Exception e) {
			Log.error(acomtcp.class+"= OutputSocket = "+e.toString());
		}
	}

	/**output 보내기**/
	public static void OutputSocket(Socket socket, byte[] prarm1, byte[] prarm2){
		//		byte[] oxl = new byte[2];
		//		oxl[0] = (byte)0x00;
		//		oxl[1] = (byte)0xd2;
		try {
			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));		
			//			dos.write(oxl, off, 2);
//			dos.writeBytes(prarm);
			
			dos.write(prarm1, 0, prarm1.length);
			dos.write(prarm2, 0, prarm2.length);
			
			Log.debug("send byte : " + prarm1.length + prarm2.length);
			Log.debug(new String(prarm1));
			Log.debug(new String(prarm2));
			
			dos.flush();
		} catch (Exception e) {
			Log.error(acomtcp.class+"= OutputSocket = "+e.toString());
		}
	}

	/**input 받기**/
	public static String InputSocket(Socket socket, int off, int len) {
		byte[] bmsg = new byte[len];
		try {
			DataInputStream br = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
			byte[] size = new byte[2];
			
			br.read(size, 0, 2);
			
			br.read(bmsg, 2, len-2);

			//			byte[] tempLength = null;
			//			for(int i = 65; i < 69; i++){
			//				tempLength[i - 65] = bmsg[i]; 
			//			}
			//			int byteLength = (tempLength[0] << 24) + ((tempLength[1] & 0xFF) << 16) + ((tempLength[2] & 0xFF) << 8) + (tempLength[3] & 0xFF);
			//			System.out.println(byteLength);


		} catch (Exception e) {
			Log.error(acomtcp.class+"= InputSocket = "+e.toString());
		}
		try {
			return new String(bmsg, "EUC-KR");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(bmsg);
	}


	public static String SubStr(String str, int start, int len)
	{
		int     i=0, j=0, flag=0;
		byte[] b1 =new byte[len];
		byte[] b2=str.getBytes();

		if (start > b2.length)
			return "";

		for (i=0; i<len && ((start+i)<b2.length); i++)
		{
			b1[i] = b2[start+i];
			if ((b1[i] & (byte)0x80) < 0) // 한글
				flag = 1-flag;
		}

		if (flag == 1)
			b1[i-1] = (byte)' ';

		return new String(b1);
	}


	public static String Fill_Char(String str, int dl,byte padchar, String align)
	{
		byte[] b1;
		byte[] b2;

		int     i=0, j=0;
		int     flag=0;
		b1 = new byte[dl];
		b2 = str.getBytes();

		if (dl <= b2.length)
		{
			for(i=0; i < dl; i++)
			{
				b1[i] = b2[i];
				if ((b2[i] & (byte)0x80) < 0) // 한글
				{
					flag = 1-flag;
				}
			}
			if (flag == 1)
				b1[i-1] = padchar;

			return new String(b1);
		}

		if (align.equals("R"))
		{
			for(i=0; i<dl-b2.length; i++)
				b1[i] = padchar;

			for(j=0; i<dl; i++, j++)
			{
				b1[i] = b2[j];
				if ((b2[j] & (byte)0x80) < 0) // 한글
				{
					flag = 1-flag;
				}
			}
			if (flag == 1)
				b1[i-1] = padchar;
		}
		else
		{
			for(j=0; i<b2.length; i++)
			{
				b1[i] = b2[i];
				if ((b2[i] & (byte)0x80) < 0) // 한글
					flag = 1-flag;
			}
			if (flag == 1)
				b1[i-1] = padchar;

			for(; i<dl; i++)
				b1[i] = padchar;
		}

		return new String(b1);
	}

	public static byte[] fillChar(String str, int dl,byte padchar, String align)
	{
		byte[] b1 = new byte[dl];
		byte[] b2;
		try {
			int     i=0, j=0;
			int     flag=0;
			
			b2 = str.getBytes("EUC-KR");

			if (dl <= b2.length)
			{
				for(i=0; i < dl; i++)
				{
					b1[i] = b2[i];
					if ((b2[i] & (byte)0x80) < 0) // 한글
					{
						flag = 1-flag;
						Log.debug("한글있다 !! 1");
					}
				}
				if (flag == 1)
					b1[i-1] = padchar;

				return b1;
			}

			if (align.equals("R"))
			{
				for(i=0; i<dl-b2.length; i++)
					b1[i] = padchar;

				for(j=0; i<dl; i++, j++)
				{
					b1[i] = b2[j];
					if ((b2[j] & (byte)0x80) < 0) // 한글
					{
						flag = 1-flag;
						Log.debug("한글있다 !! 2");
					}
				}
				if (flag == 1)
					b1[i-1] = padchar;
			}
			else
			{
				for(j=0; i<b2.length; i++)
				{
					b1[i] = b2[i];
					if ((b2[i] & (byte)0x80) < 0) // 한글
						flag = 1-flag;
				}
				if (flag == 1)
					b1[i-1] = padchar;

				for(; i<dl; i++)
					b1[i] = padchar;
			}

		} catch (Exception e) {
		}
		return b1;
	}

	public static byte[] fillChar3(String str, int dl, String datatype) {
		byte[] rtnBytes = new byte[dl];

		try {
			str = new String(str.getBytes(), "EUC-KR");

			while (str.getBytes("EUC-KR").length < dl) {
				if ("A".equals(datatype)) {
					str += "                              ";
				} else if ("N".equals(datatype)) {
					str = "0000000000" + str;
				} else {
					break;
				}
			}
			
			if (datatype.equals("A") ||
					datatype.equals("M") )
				return fillChar(str, dl, (byte)' ', "L");
	
			if (datatype.equals("N")) {
				int startIdx = str.getBytes().length - dl;
				rtnBytes = (str.substring(startIdx)).getBytes(); 
			}
		} catch (Exception e) {
		}
		
		return rtnBytes;
	}
	
	public static String Fill_Char3(String str, int dl, String datatype )
	{
		String s1="";
		
		try {
			while (str.getBytes().length < dl) {
				if ("A".equals(datatype)) {
					str += "                              ";
				} else if ("N".equals(datatype)) {
					str = "0000000000" + str;
				} else {
					break;
				}
			}
			
			if (datatype.equals("A") ||
					datatype.equals("M") )
				s1 = Fill_Char(str, dl, (byte)' ', "L");
	
			if (datatype.equals("N")) {
				int startIdx = str.getBytes().length - dl;
				s1 = str.substring(startIdx);
	//			s1 = Fill_Char(str, dl, (byte)'0', "R");
			}
		} catch (Exception e) {
		}

		return s1;

	}
	public static String t3_bytes(String sorc, int di) {
		byte[] b1 =new byte[di];

		b1[0]  = (byte)0xff;
		b1[1] = (byte)0x0d;
		b1[2] = (byte)0x0a;
		
		return new String(b1);
	}
	
	public static String Two_bytes(String sorc, int di) {
		int     i=0, j=0;
		byte[] b1 =new byte[di];
		byte[] b2=sorc.getBytes();

		for(i=0, j=0; i<b2.length && j<di ; i++, j++) {
			if (b2[i] == ' ') {
				b1[j++] = (byte)0xa1;
				b1[j] =  (byte)0xa1;
			}else if ((b2[i] & (byte)0x80) < 0) {
				b1[j++] = b2[i++];
				b1[j] = b2[i];
			}
			else {
				b1[j++] =  (byte)0xa3;
				b1[j] =  (byte)(b2[i] +  (byte)0x80);
			}
		}

		if (j < di) {

			for( ; j<di; j++)
			{
				b1[j++] = (byte)0xa1;
				b1[j] = (byte)0xa1;
			}
		}
		return new String(b1);
	}

}



