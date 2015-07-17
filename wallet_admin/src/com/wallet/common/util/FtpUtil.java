package com.wallet.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;

import com.wallet.common.util.Log;
import com.oroinc.net.ftp.FTP;
import com.oroinc.net.ftp.FTPClient;

/**
 * 일반 Command 방식 FTP 모듈
 */
public class FtpUtil {

	/**
	 * FTP오픈 및 파일전송
	 */
	public static boolean uploadFile(String ip, int port, String id, String pw, String dir, boolean passive, String filename) throws Exception {
		Log.info("uploadFile ip="+ip + ", port=" + port + ", dir=" + dir + ", filename=" + filename);
		try{
			FTPClient ftp = getFTPClient(ip, port, id, pw, dir, passive);
			return setUploadFile(ftp, filename);
		} catch (Exception e){
			return false;
		}
	}

	/**
	 * FTP오픈 및 파일다운로드
	 */
	public static boolean downloadFile(String ip, int port, String id,
			String pw, String dir, boolean passive, String filename)
			throws Exception {
		Log.info("downloadFile ip="+ip + ", port=" + port + ", dir=" + dir + ", filename=" + filename);
		FTPClient ftp = getFTPClient(ip, port, id, pw, dir, passive);
		return getDownloadFile(ftp, filename);
	}

	/**
	 * FTP오픈
	 */
	private static FTPClient getFTPClient(String ip, int port, String id,
			String pw, String dir, boolean passive) throws IOException {
		FTPClient ftp = null; // FTP Client 객체
		try {
			ftp = new FTPClient(); // FTP Client 객체 생성
			// ftp.setControlEncoding("UTF-8"); // 문자 코드를 UTF-8로 인코딩
			ftp.connect(ip, port); // 서버접속 서버주소,포트번호
			ftp.login(id, pw); // FTP 로그인 ID, PASSWORLD 입력
			if (passive)
				ftp.enterLocalPassiveMode(); // Passive Mode 접속일때
			if (!StringUtil.isNullTrim(dir)) {
				boolean ischanged = ftp.changeWorkingDirectory(dir); // 작업디렉토리변경
				if(!ischanged){
					String[] dirs = dir.split("/");
					for(int i=0; i < dirs.length; i++){
						ftp.makeDirectory(dirs[i]);
						ftp.changeWorkingDirectory(dirs[i]);
					}
				}
//				if (!ischanged) {
//					ftp.makeDirectory(dir);
//					ftp.changeWorkingDirectory(dir); // 다시 작업디렉토리변경
//					Log.debug("ftp server directory after false : " + dir);
//				} else {
//					Log.debug("ftp server directory after success : " + dir);
//				}
			}
			ftp.setFileType(FTP.BINARY_FILE_TYPE); // 업로드 파일 타입 셋팅
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectException("Connection timed out: connect");
		}
		return ftp;
	}

	/**
	 * 파일전송
	 */
	private static boolean setUploadFile(FTPClient ftp, String filename)
			throws Exception {
		boolean issend = false;
		FileInputStream fis = null; // File Input Stream
		File uploadfile = new File(filename); // File 객체
		try {
			fis = new FileInputStream(uploadfile); // 업로드할 File 생성
			boolean sf = ftp.storeFile(uploadfile.getName(), fis); // File업로드

			if (sf == true) {
				Log.debug("파일 업로드 성공");
				issend = true;
			} else {
				Log.debug("파일 업로드 실패");
				throw new Exception("File Upload Fail...");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (fis != null) {
				try {
					fis.close(); // Stream 닫기
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		try {
			ftp.logout(); // FTP Log Out
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect(); // 접속 끊기
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return issend;
	}

	/**
	 * 파일다운로드
	 */
	private static boolean getDownloadFile(FTPClient ftp, String filename)
			throws Exception {
		boolean isreceive = false;

		FileOutputStream fos = null; // File Output Stream
		File downloadfile = new File(filename); // File 객체
		try {
			fos = new FileOutputStream(downloadfile); // 다운로드할 File 생성
			ftp.retrieveFile(downloadfile.getName(), fos);
			isreceive = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (fos != null) {
				try {
					fos.close(); // Stream 닫기
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		try {
			ftp.logout(); // FTP Log Out
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftp != null && ftp.isConnected()) {
				try {
					ftp.disconnect(); // 접속 끊기
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isreceive;
	}
}
