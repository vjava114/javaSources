/**
 * @author trkim@2bsolution.com
 * @since 2013.02.21
 * @version 1.0
 * 
 * Program		: CaffebeneAllInOne
 * Description	: KT MOCA���� ī���ȣ�� �߸� ���� ȸ���� DB�� �����Ͽ� ���Ϸ� ����� ���α׷�
 * Environment	: JRE 1.6 or more
 * File			: CaffebeneAllInOne.java
 * Notes		: �ӽ� ����� ���� ����.
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
	//-- ���丮 ��� ����
	public static final String ROOT_DIR = (File.separator.equals("/")) ? "/data/2b/logs/" : "D:\\TEST\\logs\\";
	public static final String LOG_DIR = (File.separator.equals("/")) ? "/data/2b/logs/" : "D:\\TEST\\logs\\";
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	private static String curTime = ""; //-- ���ϸ����� ����� ���α׷� �����Ͻ� ����
	private static String dumpFileName = "";
	private static HashMap<String, Object> criterionMap;
	
	public void run(){
		//makeDumpFile(); //-- DB ���� ��� ���� �����Ͽ� File�� �����.
		
		dumpFileName = this.ROOT_DIR + "CaffebeneAllInOne_20130225_061836.txt"; //@@
		makeCriterionDataMap();
		
		if(!dumpFileName.equals("")){
			compareCustomData(dumpFileName); //--  DB�� dump ���� �����Ϳ� ī�亣�� ���� ������ ������ txt ������ �� 
		}
		else{
			System.out.println("##@@ ������ �������� �ʾ�, ������ ����.");
		}
	}
	
	public String makeDumpFile(){
		String result = "none"; //-- ������ ���� ����.
		
		try{
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
			
			curTime = sdf.format(cal.getTime());

			//-- �ۼ��� ���� �̸� ���� : CaffebeneAllInOne_yyyyMMdd_hhmmss.txt
			BufferedWriter out = new BufferedWriter(new FileWriter(ROOT_DIR + "CaffebeneAllInOne_" + curTime + ".txt"));
			dumpFileName = ROOT_DIR + "CaffebeneAllInOne_"+ curTime + ".txt"; //-- ���ϸ��� �޸𸮿� ������ ��.
			
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
			
			//-- DB�� �����Ͽ� �����͸� ��ȸ
			connectDB();
			
			System.out.println("## This File Name :" + dumpFileName + "\n"); //##
			//out.write("## This File Name :"  + dumpFileName);
			
			rs = stmt.executeQuery(sql.toString());
			int inx = 0; //-- ��ȸ�� �� �� ��. �ʱ�ȭ
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
			
			System.out.println("##" + dumpFileName + "���� �ۼ��Ϸ�~!!"); //##
			out.close();
			
			disconnectDB();
			
			result = "success"; //-- ���� �弮 �Ϸ�~!
		}
		catch(Exception e){
			e.printStackTrace();
			result = "fail"; // ����
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
			BufferedReader inJehyu = new BufferedReader(new FileReader(jehyuFileFullPath)); //-- caffebene ���� ���� ȸ�� DATA ����
			
			//-- ī�亣�׿��� ���� ȸ��  DATA ������ Map���� ���� (������ ���Ϸ� ���� �ʱ� ���ؼ�...)
			String jehyuFileLine = "";
			String caffeMDI = "";
			String caffeCustName = "";
			String caffeGender = "";
			String caffeBirth = "";
			String caffeCellphone = "";
			String caffeRegDate = "";
				
			while((jehyuFileLine = inJehyu.readLine()) != null){
				if(jehyuFileLine.indexOf('#') != 0){ //-- #�� �ּ����� �����.
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
								caffeGender = tmpGender.equals("��") ? "Y" : "N";
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
	
	//-- fileName : ��α��� ���ԵǾ� ����
	public String compareCustomData(String fileName){
		String cmpResult = "none";
		
		try{
			//-- ���� ������ ��� �� �̸�
			BufferedReader inDump = new BufferedReader(new FileReader(fileName)); //-- db���� dump ���� MOCA ȸ�� DATA ����
			
			//-- �ۼ��� ������ ��� �� �̸� ����
			BufferedWriter out = new BufferedWriter(new FileWriter(LOG_DIR + "CaffebeneAllInOne_" + curTime + "_compared.txt"));
			
			
			String dumpFileLine = ""; //-- ������ 1���ξ� �о����.
			int diffCnt = 0; //-- Ű ���� �����ѵ� ī���ȣ�� �ٸ� �������� ��
			int dumpFileLineCnt = 0;
			
			out.write("USER_ID | BARCODE(DB ȸ����ȣ) | caffeMDI(ī�亣�� ȸ����ȣ)  | MOCA_REGDATE | caffeREGDATE | cmpResult \n"); //-- �α����� �ۼ���.(���� �� ����)
			
			while ((dumpFileLine = inDump.readLine()) != null) {
				if(dumpFileLine.indexOf('#') != 0){ //-- #�� �ּ����� �����.
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
						String tmpCaffeMDI = tmpA.toString().substring(tmpA.toString().lastIndexOf(" ")+1); //-- ���޻��� ī���ȣ
						String tmpCaffeRegDate = tmpA.toString().substring(0, tmpA.toString().indexOf(" ")); //-- ���޻��� ����Ͻ�
						
						if(tmpA!=null && !tmpA.equals("")){ 
							if(tmpCaffeMDI.equals(BARCODE)){ //-- ī���ȣ�� �����ϸ�
								cmpResult = "same";
							}
							else{ //-- ī���ȣ�� �ٸ���
								cmpResult = "different";
								diffCnt++;
							}
							
							out.write(USER_ID  + " | " + BARCODE + " | " + tmpCaffeMDI + " | "+ REG_DT + " " + REG_TM + " | "+ tmpCaffeRegDate + " | " + cmpResult + "\n"); //-- �α����� �ۼ���.
						}
					}
					dumpFileLineCnt++;
				}
			}	
			
			System.out.println(); //##
			
			out.write("====================================================================================\n");
			out.write("���� �� �� :" + dumpFileLineCnt + "��!!,\tī���ȣ�� �ٸ� ������ �Ǽ� : " + diffCnt + "��\n");
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
	
	//-- KT ��/��ȣ�� ����� �̿��� ��ȣȭ
	public String encodingKT(String src){
		KTDBCipher dbCipher = new KTDBCipher();
		String encodingData = "";
		
		try{
			dbCipher.setAlgoID("1"); //-- �˰��� ���̵� SEED�� ���� ����Ʈ SEED
			encodingData = dbCipher.encoding(src);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return encodingData;
	}
	
	//-- KT ��/��ȣ�� ����� �̿��� ��ȣȭ
	public String decodingKT(String src){
		KTDBCipher dbCipher = new KTDBCipher();
		String decodingData = "";
		
		try{
			dbCipher.setAlgoID("1"); //-- �˰��� ���̵� SEED�� ���� ����Ʈ SEED
			decodingData = dbCipher.decoding(src);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return decodingData;
	}
	
	public Connection connectDB() {
		String driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //-- MSSQL JDBC ����̹� �ε�
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
		System.out.println("# DB���� ��ȸ�� ���� ������ File�� �ۼ��մϴ�. ");
		System.out.println("======================================================");
		System.out.println("# ���۽ð� : " + cao.printTime());
		
		cao.run(); //-- ���α׷� ����
		
		System.out.println("# ����ð� : " + cao.printTime());
		System.out.println("======================================================");
		
	}

}
