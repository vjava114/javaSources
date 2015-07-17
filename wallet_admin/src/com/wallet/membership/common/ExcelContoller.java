package com.wallet.membership.common;
import java.io.File;

import javax.servlet.http.HttpServletRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


public class ExcelContoller {
		
	private String RealPath = "";
	private int totalCnt = 0;
	private Workbook myWorkbook = null;
	private Sheet mySheet = null;
	private boolean result = false; 
	private String Msg = "";
	
	public ExcelContoller(HttpServletRequest request,String FilePath,int TotalCnt) {
		// TODO Auto-generated constructor stub
		RealPath = request.getSession().getServletContext().getRealPath(FilePath);
		totalCnt = TotalCnt;
		try{
			myWorkbook = Workbook.getWorkbook(new File(RealPath)); 
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public boolean ChkTitle(String Title){
		
		Cell myCell = mySheet.getCell(1,0); 
	if((myCell.getContents()==null||myCell.getContents().equals(""))){
		result = false;
	}else{
		String Titles = myCell.getContents();
		System.out.println("Titles : " + Titles);
		if(Titles.equals(Title)){
			result = true;
		}else{
			result = false;
		}
	}
	return result;
	}
	
	public ExcelContoller(File file,int TotalCnt) {
		// TODO Auto-generated constructor stub
		totalCnt = TotalCnt;
		try{
			myWorkbook = Workbook.getWorkbook(file); 
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}
	
	public void setSheet(int SheetNum){
		mySheet = myWorkbook.getSheet(SheetNum); 
	}
	
	public String[] getString(int Line){
		String[] Results = new String[totalCnt+1];
		try{
			for(int i=1;i<(totalCnt+1);i++){ 
				Cell myCell = mySheet.getCell(i,Line+3);
				Results[i] = myCell.getContents().trim();
				
				System.out.println("Results[i] : " + Results[i]);
				
				if(i==2&&(Results[i]==null||Results[i].equals("")||Results[i].length()<1)){
					Results[0] = "false";
					break;
				}else{
					Results[0] = "true";
				}
				
				if(i==3&&(myCell.getContents()==null||myCell.getContents().equals("")||Results[i].length()<1)){
					Results[0] = "false";
				}else{
					Results[0] = "true";
				}
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("¿©±â ex");
			Results[0] = "false";
		}
		return Results;		
	}
}
