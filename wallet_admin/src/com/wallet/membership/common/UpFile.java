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
		String path = Path + DateTime.format("yyyyMMddHHmmssSS")+"_";//파일 업로드할 패스(임시 리얼패스를 박아 놓았음)
		MultipartHttpServletRequest mpRequest = (MultipartHttpServletRequest) request;	//목록 이미지(아이폰4,아이폰3GS,만드로이드)
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
