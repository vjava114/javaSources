package com.wallet.common.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;




public class ExcelDownloadView extends AbstractExcelView {

	 @Override
	 protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
	   throws Exception {


	  // controller���� ModelAndView ��ü�� addObject�Ͽ� ������ �����͸� ������.
	  List<Map<String, String>> excelList = (List<Map<String, String>>)model.get("excelList ");


	  // ù ��Ʈ�� �����.

	  HSSFSheet sheet = creatFirstSheet(workbook);
	  creatColum(sheet);
	  
	  int rowNum = 1;
	  

	  for(Map<String, String> excelInfo : excelList ){
	  createRow(sheet, excelInfo , rowNum++);
	   
	  }
	 }


	 // ù��° ��Ʈ�� �����
	 private HSSFSheet creatFirstSheet(HSSFWorkbook workbook){
	 

	 HSSFSheet sheet = workbook.createSheet();
	  // setSheetName(��Ʈ����, ��Ʈ�̸�)
	  workbook.setSheetName(0,"APP����Ʈ ��Ȳ");
	  
	  return sheet;
	 }
	 
	 // ��Ʈ�� ���� �÷��� �����
	 private void creatColum(HSSFSheet sheet) {
	 

	  HSSFRow header = sheet.createRow((short)0);
	  
	  HSSFCell cell = header.createCell((short)0);
	  cell.setCellValue("���̵�");

	 

	  cell = header.createCell((short)1);
	  cell.setCellValue("�̸�");
	  
	  cell = header.createCell((short)2);
	  cell.setCellValue("��й�ȣ");
	  
	 }  


	 // �� �ο쿡 ���� �� �Է�
	 private void createRow(HSSFSheet sheet, Map<String, String> excelInfo, int rowNum){
	 

	  HSSFRow row = sheet.createRow(rowNum);
	  

	  HSSFCell cell = row.createCell((short)0);
	  cell.setCellValue(excelInfo .get("testID"));
	  
	  cell = row.createCell((short)1);
	  cell.setCellValue(excelInfo .get("testName"));
	  
	  cell = row.createCell((short)2);
	  cell.setCellValue(excelInfo .get("testPW"));
	     
	 }
	 
	} 
	 
