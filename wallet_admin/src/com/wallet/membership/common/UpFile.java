package com.wallet.membership.common;

import java.io.File;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.wallet.common.util.FileUtil;

public class UpFile {
	private File Files;
	public UpFile(HttpServletRequest request, String Path) {
		// TODO Auto-generated constructor stub
		String path = Path + DateTime.format("yyyyMMddHHmmssSS")+"_";//���� ���ε��� �н�(�ӽ� �����н��� �ھ� ������)
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//��� �̹���(������4,������3GS,������̵�)
		String RealPath = mpRequest.getSession().getServletContext().getRealPath(path);
		try {
			Iterator fileNameIterator = mpRequest.getFileNames();
			
	    while (fileNameIterator.hasNext()) {
	        MultipartFile multiFile = mpRequest
	                .getFile((String) fileNameIterator.next());

	       if (multiFile.getSize() > 0) {
	      	 Files = FileUtil.writeFile(multiFile, RealPath);	      	 
	        }
	    }
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	public File getFiles() {
		
		return Files;
	}
}
