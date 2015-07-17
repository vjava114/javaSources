package com.wallet.common.util;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

import com.ibm.icu.text.SimpleDateFormat;
import com.oroinc.net.ftp.FTP;
import com.oroinc.net.ftp.FTPClient;
import com.oroinc.net.ftp.FTPReply;
import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiException;
import com.sun.jimi.core.JimiUtils;

public class FileFTP {
	String server = "10.25.5.72";
	int port = 21;
	String id = "eciusr";
	String password = "eciusr!";
	private FTPClient ftpClient = new FTPClient();

	private void connect(){
		try{
			int reply;
			ftpClient.connect(server, port);
			reply = ftpClient.getReplyCode();
			if(!FTPReply.isPositiveCompletion(reply)){
				ftpClient.disconnect();
				Log.error("서버로부터 연결을 거부당했습니다.");
				System.exit(1);
			}else{
				Log.debug("connect ==> " + ftpClient.getReplyString());
			}
			ftpClient.login(id, password);
		}catch(IOException ioe){
			if(ftpClient.isConnected()){
				try{
					ftpClient.disconnect();
				}catch(IOException e){
				}
			}
			Log.error(FileFTP.class+" >>>> "+"서버에 연결할 수 없습니다.");
			System.exit(1);
		}
	}
	
	
	public void upload(String UserId, String group, InputStream is){
		File uploadFile = null;
		FileOutputStream fileOutputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		try {
			inputStreamReader = new InputStreamReader(is);
			bufferedReader = new BufferedReader(inputStreamReader);
			uploadFile = new File(PropertiesUtil.get("FTPFILE")+"origin"+"/temp.jpg");
			fileOutputStream = new FileOutputStream(uploadFile);
			int i;
			while(true){  
				if((i = bufferedReader.read()) == -1){
					break;
				}
				fileOutputStream.write(i); 
			}
			fileOutputStream.flush();
			fileOutputStream.close();
			
			System.setProperty("java.awt.headless", "true"); 
			Image image = JimiUtils.getThumbnail(PropertiesUtil.get("FTPFILE")+"origin"+"/temp.jpg", 50, 50, 100);
			Jimi.putImage(image, PropertiesUtil.get("FTPFILE")+"thumb"+"/temp.jpg");
			
			upload(UserId, group, "origin");
			upload(UserId, group, "thumb");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JimiException e) {
			e.printStackTrace();
		}
	}
	

	private void upload(String UserId, String group, String type){
		File uploadFile = null;
		FileInputStream  fileInputStream = null;
		try{
			uploadFile = new File(PropertiesUtil.get("FTPFILE")+type+"/temp.jpg");
			fileInputStream = new FileInputStream(uploadFile);
			SimpleDateFormat simple = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			connect();
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.changeWorkingDirectory("exappApp");
			ftpClient.changeWorkingDirectory("upimg");
			ftpClient.changeWorkingDirectory(group);
			ftpClient.changeWorkingDirectory(type);

			String DirectoryYear = simple.format(date).substring(0,4);
			String DirectoryMonth = simple.format(date).substring(4,6);
			String DirectoryDay = simple.format(date).substring(6,8);

			boolean changeWorkingDirectoryYear = ftpClient.changeWorkingDirectory(DirectoryYear);
			boolean changeWorkingDirectoryMonth = ftpClient.changeWorkingDirectory(DirectoryMonth);
			boolean changeWorkingDirectoryDay = ftpClient.changeWorkingDirectory(DirectoryDay);

			if(!changeWorkingDirectoryYear || !changeWorkingDirectoryMonth || !changeWorkingDirectoryDay){
				if(!changeWorkingDirectoryYear){
					ftpClient.makeDirectory(DirectoryYear);
				}
				if(!changeWorkingDirectoryMonth){
					ftpClient.changeWorkingDirectory(DirectoryYear);
					ftpClient.makeDirectory(DirectoryMonth);
				}
				if(!changeWorkingDirectoryDay){
					ftpClient.changeWorkingDirectory(DirectoryMonth);
					ftpClient.makeDirectory(DirectoryDay);
				}
				ftpClient.changeWorkingDirectory(DirectoryDay);
			}
			boolean isSuccess = ftpClient.storeFile(simple.format(date).substring(8)+"_"+UserId+".jpg",  fileInputStream);
			if(isSuccess){
				Log.debug("FTP File = "+simple.format(date).substring(8)+"_"+UserId+".jpg 업로드 성공");
			}else{
				Log.error(ftpClient.getReplyString() + " :: " + isSuccess);
			}

		}catch(IOException ioe){
			Log.error(FileFTP.class+" >>>> "+ioe.getMessage());
		}catch(Exception e){
			Log.error(FileFTP.class+" >>>> "+e.getMessage());
		}finally{
			try {
				fileInputStream.close();
				ftpClient.logout();
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
