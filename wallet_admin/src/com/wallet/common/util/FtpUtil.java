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
 * �Ϲ� Command ��� FTP ���
 */
public class FtpUtil {

	/**
	 * FTP���� �� ��������
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
	 * FTP���� �� ���ϴٿ�ε�
	 */
	public static boolean downloadFile(String ip, int port, String id,
			String pw, String dir, boolean passive, String filename)
			throws Exception {
		Log.info("downloadFile ip="+ip + ", port=" + port + ", dir=" + dir + ", filename=" + filename);
		FTPClient ftp = getFTPClient(ip, port, id, pw, dir, passive);
		return getDownloadFile(ftp, filename);
	}

	/**
	 * FTP����
	 */
	private static FTPClient getFTPClient(String ip, int port, String id,
			String pw, String dir, boolean passive) throws IOException {
		FTPClient ftp = null; // FTP Client ��ü
		try {
			ftp = new FTPClient(); // FTP Client ��ü ����
			// ftp.setControlEncoding("UTF-8"); // ���� �ڵ带 UTF-8�� ���ڵ�
			ftp.connect(ip, port); // �������� �����ּ�,��Ʈ��ȣ
			ftp.login(id, pw); // FTP �α��� ID, PASSWORLD �Է�
			if (passive)
				ftp.enterLocalPassiveMode(); // Passive Mode �����϶�
			if (!StringUtil.isNullTrim(dir)) {
				boolean ischanged = ftp.changeWorkingDirectory(dir); // �۾����丮����
				if(!ischanged){
					String[] dirs = dir.split("/");
					for(int i=0; i < dirs.length; i++){
						ftp.makeDirectory(dirs[i]);
						ftp.changeWorkingDirectory(dirs[i]);
					}
				}
//				if (!ischanged) {
//					ftp.makeDirectory(dir);
//					ftp.changeWorkingDirectory(dir); // �ٽ� �۾����丮����
//					Log.debug("ftp server directory after false : " + dir);
//				} else {
//					Log.debug("ftp server directory after success : " + dir);
//				}
			}
			ftp.setFileType(FTP.BINARY_FILE_TYPE); // ���ε� ���� Ÿ�� ����
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConnectException("Connection timed out: connect");
		}
		return ftp;
	}

	/**
	 * ��������
	 */
	private static boolean setUploadFile(FTPClient ftp, String filename)
			throws Exception {
		boolean issend = false;
		FileInputStream fis = null; // File Input Stream
		File uploadfile = new File(filename); // File ��ü
		try {
			fis = new FileInputStream(uploadfile); // ���ε��� File ����
			boolean sf = ftp.storeFile(uploadfile.getName(), fis); // File���ε�

			if (sf == true) {
				Log.debug("���� ���ε� ����");
				issend = true;
			} else {
				Log.debug("���� ���ε� ����");
				throw new Exception("File Upload Fail...");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (fis != null) {
				try {
					fis.close(); // Stream �ݱ�
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
					ftp.disconnect(); // ���� ����
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return issend;
	}

	/**
	 * ���ϴٿ�ε�
	 */
	private static boolean getDownloadFile(FTPClient ftp, String filename)
			throws Exception {
		boolean isreceive = false;

		FileOutputStream fos = null; // File Output Stream
		File downloadfile = new File(filename); // File ��ü
		try {
			fos = new FileOutputStream(downloadfile); // �ٿ�ε��� File ����
			ftp.retrieveFile(downloadfile.getName(), fos);
			isreceive = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (fos != null) {
				try {
					fos.close(); // Stream �ݱ�
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
					ftp.disconnect(); // ���� ����
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return isreceive;
	}
}
