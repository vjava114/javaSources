/**
 * @author trkim@2bsolution.com
 * @since 2013.02.21
 * @version 1.0
 * 
 * Program		: CaffebeneAllInOne
 * Description	: KT MOCA에서 카드번호가 잘못 나간 회원의 DB를 추출하여 파일로 만드는 프로그램
 * Environment	: JRE 1.6 or more
 * File			: CaffebeneAllInOne.java
 * Notes		: 임시 사용을 위해 만듦.
 * History		: [YYYY-MM-DD][hh:mm:ss][Programmer][Description]
 * 				: [2013-02-21][14:49:00][trkim@2bsoltuion.com][CREATE: Initial release]
 */
import java.io.*;
import java.util.*;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.*;
import java.sql.*;
import com.wallet.common.cipher.*;

public class CaffebeneAllInOne {
	//-- 디렉토리 경로 설정
	public static final String ROOT_DIR = (File.separator.equals("/")) ? "/data/2b/logs/" : "D:\\TEST\\logs\\";
	public static final String LOG_DIR = (File.separator.equals("/")) ? "/data/2b/logs/" : "D:\\TEST\\logs\\";
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	private static String curTime = ""; //-- 파일명으로 사용할 프로그램 실행일시 정보
	private static String dumpFileName = "";
	private static HashMap<String, Object> criterionMap;
	
	public void run(){
		//makeDumpFile(); //-- DB 에서 대상 고객을 추출하여 File로 만든다.
		
		dumpFileName = this.ROOT_DIR + "CaffebeneAllInOne_20130225_061836.txt"; //@@
		makeCriterionDataMap();
		
		if(!dumpFileName.equals("")){
			compareCustomData(dumpFileName); //--  DB로 dump 받은 데이터와 카페베테 엑셀 정제된 데이터 txt 파일을 비교 
		}
		else{
			System.out.println("##@@ 파일이 생성되지 않아, 비교하지 못함.");
		}
	}
	
	public String makeDumpFile(){
		String result = "none"; //-- 실행이 되지 않음.
		
		try{
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			
			curTime = sdf.format(cal.getTime());

			//-- 작성할 파일 이름 형식 : CaffebeneAllInOne_yyyyMMdd_hhmmss.txt
			BufferedWriter out = new BufferedWriter(new FileWriter(ROOT_DIR + "CaffebeneAllInOne_" + curTime + ".txt"));
			dumpFileName = ROOT_DIR + "CaffebeneAllInOne_"+ curTime + ".txt"; //-- 파일명을 메모리에 저장해 둠.
			
			StringBuffer sql = new StringBuffer();

			sql.append("SELECT I.USER_ID, I.PHONE, D.AUTH_NAME, D.MAN ");
			sql.append("	, M.M_LEVEL, D.EMAIL, D.AGREE, D.BIRTH ");
			sql.append("	, D.IPIN_DI, I.MO ");
			sql.append("	, D.ZIPCODE, D.ADDR, D.ADDR1 ");
			sql.append("	, I.STAT, M.REG_DT, M.REG_TM ");
			sql.append("	, M.BARCODE, M.CARD_NUM ");
			sql.append("FROM USER_INFO I, USER_DESC D, MY_MEMBER M \n");
			sql.append("WHERE I.USER_ID = D.USER_ID \n");
			sql.append("AND I.USER_ID = M.USER_ID \n");
			sql.append("AND I.STAT = 'R' \n");
			sql.append("AND M.MEMB_ID = 'caffebene' \n");
			//sql.append("AND M.REG_DT BETWEEN '20121204' AND '20121214'\n");
			sql.append("ORDER BY M.REG_DT, M.REG_TM/*, D.AUTH_NAME*/, I.PHONE, I.USER_ID\n");
			
			//-- DB에 접속하여 데이터를 조회
			connectDB();
			
			System.out.println("## This File Name :" + dumpFileName + "\n"); //##
			//out.write("## This File Name :"  + dumpFileName);
			
			rs = stmt.executeQuery(sql.toString());
			int inx = 0; //-- 조회된 총 고객 수. 초기화
			while(rs.next()){
				inx++;
				
				String tmpCust = inx + "\t" + rs.getString(1) + "\t" + rs.getString(2) + "\t" + decodingKT(rs.getString(3).trim()) + "\t" + rs.getString(4)
						+ "\t" + rs.getString(5) + "\t" + rs.getString(6) + "\t" + rs.getString(7) + "\t" + rs.getString(8) 
						+ "\t" + rs.getString(9) + "\t" + rs.getString(10) 
						+ "\t" + rs.getString(11) + "\t" + rs.getString(12) + "\t" + rs.getString(13)
						+ "\t" + rs.getString(14) + "\t" + rs.getString(15) + "\t" + rs.getString(16)
						+ "\t" + rs.getString(17) + "\t" + rs.getString(18)
						;
				
				System.out.println(tmpCust); //##
				out.write(tmpCust);
				out.newLine();
			}
			
			System.out.println("##" + dumpFileName + "파일 작성완료~!!"); //##
			out.close();
			
			disconnectDB();
			
			result = "success"; //-- 파일 장석 완료~!
		}
		catch(Exception e){
			e.printStackTrace();
			result = "fail"; // 실패
		}
		finally{
			System.out.println("## makeDumpFile() : " + result);
			return result;
		}
	}
	
	
	public void makeCriterionDataMap(){
		this.criterionMap = new HashMap<String, Object>();
		//String fullFilePath = this.ROOT_DIR + "CaffebeneAllInOne_" + curTime + "_compared.txt";
		String jehyuFileFullPath = this.ROOT_DIR + "caffebene_comp_20130225.txt";
		
		try{
			BufferedReader inJehyu = new BufferedReader(new FileReader(jehyuFileFullPath)); //-- caffebene 에서 받은 회원 DATA 파일
			
			//-- 카페베테에서 받은 회원  DATA 파일을 Map으로 변경 (여러번 파일로 읽지 않기 위해서...)
			String jehyuFileLine = "";
			String caffeMDI = "";
			String caffeCustName = "";
			String caffeGender = "";
			String caffeBirth = "";
			String caffeCellphone = "";
			String caffeRegDate = "";
				
			while((jehyuFileLine = inJehyu.readLine()) != null){
				if(jehyuFileLine.indexOf('#') != 0){ //-- #은 주석으로 사용함.
					StringTokenizer st2 = new StringTokenizer(jehyuFileLine, ",");
					int cntTokens = st2.countTokens();
					for(int i=0; i<cntTokens; i++){
						String tmp = st2.nextToken();
						
						if(tmp == null){
							tmp = "";
						} else{
							tmp = tmp.trim();
						}
						
						switch(i){
							case  0: caffeMDI = tmp; break;
							case  1: caffeCustName = tmp; break;
							case  2: 
								String tmpGender = tmp;
								caffeGender = tmpGender.equals("남") ? "Y" : "N";
								break;
							case  3: caffeCellphone = tmp; break;
							case  4: caffeBirth = tmp.substring(2); break;
							case  5: caffeRegDate = tmp; break;
						}
					}
					
					criterionMap.put(caffeCellphone + caffeCustName + caffeGender + caffeBirth, caffeRegDate + " | " + caffeMDI );
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//-- fileName : 경로까지 포함되어 있음
	public String compareCustomData(String fileName){
		String cmpResult = "none";
		
		try{
			//-- 읽을 파일의 경로 및 이름
			BufferedReader inDump = new BufferedReader(new FileReader(fileName)); //-- db에서 dump 받은 MOCA 회원 DATA 파일
			
			//-- 작성할 파일의 경로 및 이름 형식
			BufferedWriter out = new BufferedWriter(new FileWriter(LOG_DIR + "CaffebeneAllInOne_" + curTime + "_compared.txt"));
			
			
			String dumpFileLine = ""; //-- 파일을 1라인씩 읽어들임.
			int diffCnt = 0; //-- 키 값은 동일한데 카드번호가 다른 데이터의 수
			int dumpFileLineCnt = 0;
			
			out.write("USER_ID | BARCODE(DB 회원번호) | caffeMDI(카페베네 회원번호)  | MOCA_REGDATE | caffeREGDATE | cmpResult \n"); //-- 로그파일 작성함.(파일 맨 윗줄)
			
			while ((dumpFileLine = inDump.readLine()) != null) {
				if(dumpFileLine.indexOf('#') != 0){ //-- #은 주석으로 사용함.
					StringTokenizer st = new StringTokenizer(dumpFileLine, "\t");
					
					String USER_ID = "";
					String PHONE = "";
					String AUTH_NAME = "";
					String MAN = "";
					String M_LEVEL = "";
					String EMAIL = "";
					String AGREE = "";
					String BIRTH = "";
					String IPIN_DI = "";
					String MO = "";
					String ZIPCODE = "";
					String ADDR = "";
					String ADDR1 = "";
					String STAT = "";
					String REG_DT = "";
					String REG_TM = "";
					String BARCODE = "";
					String CARD_NUM = "";
					
					int cntTokens = st.countTokens();
					for(int i=0; i<cntTokens; i++){
						String tmp = st.nextToken();
						
						if(tmp == null){
							tmp = "";
						} else{
							tmp = tmp.trim();
						}
						
						switch(i){
							case  0: break; //-- for index
							case  1: USER_ID = tmp; break;
							case  2: PHONE = tmp; break;
							case  3: AUTH_NAME = decodingKT(tmp); break;
							case  4: MAN = tmp; break;
							case  5: M_LEVEL = tmp; break;
							case  6: EMAIL = tmp; break;
							case  7: AGREE = tmp; break;
							case  8: BIRTH = tmp; break;
							case  9: IPIN_DI = tmp; break;
							case 10: MO = tmp; break;
							case 11: ZIPCODE = tmp; break;
							case 12: ADDR = tmp; break;
							case 13: ADDR1 = tmp; break;
							case 14: STAT = tmp; break;
							case 15: REG_DT = tmp; break;
							case 16: REG_TM = tmp; break;
							case 17: BARCODE = tmp; break;
							case 18: CARD_NUM = tmp; break;
						}
					}
					
					Object tmpA = this.criterionMap.get(PHONE + AUTH_NAME + MAN + BIRTH);
					if(tmpA != null){
						String tmpCaffeMDI = tmpA.toString().substring(tmpA.toString().lastIndexOf(" ")+1); //-- 제휴사측 카드번호
						String tmpCaffeRegDate = tmpA.toString().substring(0, tmpA.toString().indexOf(" ")); //-- 제휴사측 등록일시
						
						if(tmpA!=null && !tmpA.equals("")){ 
							if(tmpCaffeMDI.equals(BARCODE)){ //-- 카드번호가 동일하면
								cmpResult = "same";
							}
							else{ //-- 카드번호가 다르면
								cmpResult = "different";
								diffCnt++;
							}
							
							out.write(USER_ID  + " | " + BARCODE + " | " + tmpCaffeMDI + " | "+ REG_DT + " " + REG_TM + " | "+ tmpCaffeRegDate + " | " + cmpResult + "\n"); //-- 로그파일 작성함.
						}
					}
					dumpFileLineCnt++;
				}
			}	
			
			System.out.println(); //##
			
			out.write("====================================================================================\n");
			out.write("대상고객 수 총 :" + dumpFileLineCnt + "명!!,\t카드번호가 다른 데이터 건수 : " + diffCnt + "명\n");
			cmpResult = "result(diffCnt : " + diffCnt + ")";
			
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
			cmpResult = "fail";
		}
		finally{
			System.out.println("#### compareCustomData() : " + cmpResult);
			return cmpResult;
		}
	}
	
	//-- KT 암/복호와 모듈을 이용한 암호화
	public String encodingKT(String src){
		KTDBCipher dbCipher = new KTDBCipher();
		String encodingData = "";
		
		try{
			dbCipher.setAlgoID("1"); //-- 알고리즘 아이디를 SEED로 지정 디폴트 SEED
			encodingData = dbCipher.encoding(src);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return encodingData;
	}
	
	//-- KT 암/복호와 모듈을 이용한 복호화
	public String decodingKT(String src){
		KTDBCipher dbCipher = new KTDBCipher();
		String decodingData = "";
		
		try{
			dbCipher.setAlgoID("1"); //-- 알고리즘 아이디를 SEED로 지정 디폴트 SEED
			decodingData = dbCipher.decoding(src);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return decodingData;
	}
	
	public Connection connectDB() {
		String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //-- MSSQL JDBC 드라이버 로드
		String jdbcUrl = "jdbc:sqlserver://20.20.20.12:1433;databaseName=wallet"; //--TEST DB
		String dbUserId = "mwallet";
		String dbUserPw = "wallet";
		
		if(conn == null){
			try {
				Class.forName(driverClassName);
				
				conn = DriverManager.getConnection(jdbcUrl, dbUserId, dbUserPw);
				stmt = conn.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public void disconnectDB() {
		if(conn != null){
			try {
				if(!conn.isClosed()){
					stmt.close();
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		conn = null;
	}
	
	public String printTime(){

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String thisTime = sdf.format(cal.getTime());
		
		//System.out.println(thisTime); //##
		return thisTime;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CaffebeneAllInOne cao = new CaffebeneAllInOne();
		
		System.out.println("======================================================");
		System.out.println("# DB에서 조회된 고객의 정보를 File로 작성합니다. ");
		System.out.println("======================================================");
		System.out.println("# 시작시간 : " + cao.printTime());
		
		cao.run(); //-- 프로그램 수행
		
		System.out.println("# 종료시간 : " + cao.printTime());
		System.out.println("======================================================");
		
	}

}
