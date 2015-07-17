package com.wallet.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public class FileUtil {

	/**
	 * 파일 읽기 (fileReader)
	 */
	public static String fileReader(String fileName) throws Exception {
		
		String str = "";
		
		try {
			
			FileReader fr = new FileReader(fileName);

			int data =0;

			// FileReader를 이용해서 파일내용을 읽어 화면에 출력한다.
			while((data=fr.read())!=-1) {
				//System.out.print((char)data);
				str += (char)data;
			}
			
			fr.close();				

		} catch (IOException e) {
				e.printStackTrace();		
		}
		return str;
	}
	
	/**
	 * 파일읽기 (Line 구분 없음)
	 */
	public static StringBuilder readFile(String filename) throws Exception {
		StringBuilder sb = new StringBuilder();
		List<String> list = readFileLine(filename);
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
		}
		return sb;
	}

	/**
	 * 파일 읽기 (Line 구분 있음)
	 */
	public static List<String> readFileLine(String filename) throws Exception {
		String str = null;
		BufferedReader in = null;
		List<String> list = new ArrayList<String>();
		try {
			in = new BufferedReader(new FileReader(filename));
			while ((str = in.readLine()) != null) {
				list.add(new String(str.getBytes(), "ISO_8859_1"));
				Log.debug(str);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}
		Log.debug("file read ok [" + filename + "]");
		return list;
	}

	/**
	 * 파일 읽기 (Stream)
	 */
	public static StringBuilder readFileStream(String filename)
			throws Exception {
		int pos = 0;
		int size = 10;
		byte[] b = new byte[4096];
		FileInputStream fis = null;
		StringBuilder sb = new StringBuilder();
		try {
			// 파일 이름을 초기 인자로 주어 FileInputStream 생성
			fis = new FileInputStream(filename);
			// read() 메서드를 통하여 읽기
			while ((size = fis.read(b, pos, size)) > 0) {
				sb.append(new String(b));
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				fis.close();
			} catch (Exception e) {
			}
		}
		Log.debug("file read ok [" + filename + "]");
		return sb;
	}
 

	/**
	 * 파일쓰기
	 */
	public static String writeFile(String filename, List<String> list)
			throws Exception {
		return writeFileMain(filename, list, false);
	}

	/**
	 * 파일 쓰기
	 */
	public static String writeFileLine(String filename, List<String> list)
			throws Exception {
		return writeFileMain(filename, list, true);
	}

	/**
	 * 파일 쓰기 공통
	 */
	private static String writeFileMain(String filename, List<String> list,
			boolean isLine) throws Exception {
		if (!isFile(filename)) {
			createFile(filename);
		}
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < list.size(); i++) {
				out.write(list.get(i));
				if (isLine)
					out.newLine();
				Log.debug(list.get(i));
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				out.close();
			} catch (Exception e) {
			}
		}
		Log.debug("file write ok [" + filename + "]");
		return filename;

	}

	/**
	 * 디렉토리 생성 및 파일 생성
	 */
	private static boolean createFile(String filename) throws Exception {
		if (filename == null || filename.length() == 0)
			throw new Exception("File create error... filename=" + filename);
		Log.debug(filename);
		File file = new File(filename);
		
		try {
			File makePath = new File(file.getParent());
			makePath.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file.createNewFile();
	}

	/**
	 * 파일 존재 여부 판단
	 */
	public static boolean isFile(String filename) {
		if (filename == null || filename.length() == 0)
			return false;
		File f = new File(filename);
		if (f.isFile()) {
			if (f.isDirectory()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	/**
	 * source에서 target으로의 파일 복사
	 * 
	 * @param source
	 *            복사할 파일명을 포함한 절대 경로
	 * @param target
	 *            복사될 파일명을 포함한 절대경로
	 */
	public static void copyFile(String source, String target) throws Exception {
		// 복사 대상이 되는 파일 생성
		File sourceFile = new File(source);
		// 스트림, 채널 선언
		FileInputStream inputStream = null;
		FileOutputStream outputStream = null;
		FileChannel fcin = null;
		FileChannel fcout = null;
		try {
			// 스트림 생성
			inputStream = new FileInputStream(sourceFile);
			outputStream = new FileOutputStream(target);
			// 채널 생성
			fcin = inputStream.getChannel();
			fcout = outputStream.getChannel();
			// 채널을 통한 스트림 전송
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 자원 해제
			try {
				fcout.close();
			} catch (IOException ioe) {
			}
			try {
				fcin.close();
			} catch (IOException ioe) {
			}
			try {
				outputStream.close();
			} catch (IOException ioe) {
			}
			try {
				inputStream.close();
			} catch (IOException ioe) {
			}
		}
		Log.debug("file copy ok [" + source + "]->[" + target + "]");
	}

	/**
	 * 멀티파트 submit 으로 전송된 파일 저장
	 */
	public static List<File> writeFile(MultipartHttpServletRequest request)
			throws IOException {
		Log.info("[MultiPartFileUtil saveFile]");
		List<File> list = new ArrayList<File>();
		try {
			List<MultipartFile> files = request.getFiles("files");
			// Log.info("files: " + files.toString());
			// Log.info("size: " + String.valueOf(files.size()));
			String[] filepath = request.getParameterValues("filepath");
			for (int i = 0; i < files.size(); i++) {
				MultipartFile f = files.get(i);
				File saveFile = writeFile(f, filepath[i]);
				if (saveFile != null)
					list.add(saveFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static File writeFile(MultipartFile f, String filepath)
	throws Exception {
		Log.info("[saveFile] : [" + f.getOriginalFilename() + "] : "
				+ f.getSize());
		
		// 파일이 입력되지 않으면
		if (f.getOriginalFilename() == null
				|| "".equals(f.getOriginalFilename()) || f.getSize() == 0) {
			return null;
		}
		
		StringBuilder saveName = new StringBuilder();
		saveName.append(f.getOriginalFilename());
		StringBuilder savePath = new StringBuilder();
		savePath.append(filepath);
		
		// file path making
		try {
			File makePath = new File(savePath.toString());
			makePath.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// merge file path + file name
		savePath.append(saveName.toString());
		
		Log.info("[saveFile] saveFilePath=" + savePath);
		
		// save file
		File saveFile = new File(savePath.toString());
		f.transferTo(saveFile);
		
		return saveFile;
}


	public static File writeFileNewHtmlName(MultipartFile f, String filepath)
	throws Exception {
		Log.info("[saveFile] : [" + f.getOriginalFilename() + "] : "
				+ f.getSize());
		
		// 파일이 입력되지 않으면
		if (f.getOriginalFilename() == null
				|| "".equals(f.getOriginalFilename()) || f.getSize() == 0) {
			return null;
		}
		
		StringBuilder saveName = new StringBuilder();
		if(f.getOriginalFilename().indexOf(".html") >= 0){
			saveName.append(f.getOriginalFilename()+".htm");
			//saveName.append(f.getOriginalFilename()+"_webAddExt");
			//saveName.append(f.getOriginalFilename().replace(".html", ".htext"));
		}else{
			saveName.append(f.getOriginalFilename());
		}
		
		StringBuilder savePath = new StringBuilder();
		savePath.append(filepath);
		
		// file path making
		try {
			File makePath = new File(savePath.toString());
			makePath.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// merge file path + file name
		savePath.append(saveName.toString());
		
		Log.info("[saveFile] saveFilePath=" + savePath);
		
		// save file
		File saveFile = new File(savePath.toString());
		f.transferTo(saveFile);
		
		return saveFile;
	}
	/**
	 * f : 파일
	 * filepath : 파일경로
	 * fileName : 저장할 파일명
	 */
	public static File writeFile(MultipartFile f, String filepath, String fileName)
	throws Exception {
		Log.info("[saveFile] : [" + f.getOriginalFilename() + "] : "
				+ f.getSize());
		
		// 파일이 입력되지 않으면
		if (f.getOriginalFilename() == null
				|| "".equals(f.getOriginalFilename()) || f.getSize() == 0) {
			return null;
		}
		
		StringBuilder saveName = new StringBuilder();
		saveName.append(fileName);
		StringBuilder savePath = new StringBuilder();
		savePath.append(filepath);
		
		// file path making
		try {
			File makePath = new File(savePath.toString());
			makePath.mkdirs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// merge file path + file name
		savePath.append(saveName.toString());
		
		Log.info("[saveFile] saveFilePath=" + savePath);
		
		// save file
		File saveFile = new File(savePath.toString());
		f.transferTo(saveFile);
		
		return saveFile;
}

	public static boolean deleteFile(File file) throws Exception {
		Log.info("[deleteFile] pathname=" + file.getCanonicalPath());
		return file.delete();
	}

	public static boolean deleteFile(String pathname) throws Exception {
		return deleteFile(new File(pathname));
	}

	public static boolean deleteFile(List<File> list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			deleteFile(list.get(i));
		}
		return true;
	}
}
