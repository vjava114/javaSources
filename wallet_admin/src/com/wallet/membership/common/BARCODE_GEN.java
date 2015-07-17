package com.wallet.membership.common;


public class BARCODE_GEN {
	public static String LOCAL_BARCODE_NUM (String cpnId) {

		String FIRSTNUMBER = "5";//1
		String RANDOM = ""+(int)(Math.random()*9 + 1);//1
		String SS = DateTime.format("SS");//3
		
		//SS 두글자 자리 변환
		String ss = DateTime.format("ss");//2
		ss =ss.substring(1) +  ss.substring(0, 1); 
		
		String mm = DateTime.format("mm");//2
		String HH = DateTime.format("HH");//2
		
		String dd = DateTime.format("dd");//2
		String MM = DateTime.format("MM");//2
		
		String yyyy = DateTime.format("yyyy");//4
		yyyy = yyyy.substring(2);
		String getCpn = extract(cpnId);
		String CpnId = CutCpnString(getCpn);
		
		//글자 조합
		String Result = FIRSTNUMBER + RANDOM + mm + dd + CpnId + MM + yyyy;
		return Result;
	}
	
	private static String CutCpnString(String Cpn){
		String Andresult = Cpn;
		if(Andresult.length()>4){
			Andresult = Cpn.substring(Cpn.length() - 4, Cpn.length());
		}else{
			int cnt = 4-Andresult.length();
			for(int i=0;i<cnt;i++){
				Andresult = Andresult + (int)(Math.random()*9 + 1);
			}
		}
		return Andresult;
	}
	
	private static String extract(String str){
		String temp = "";
		String numeral = "";
		 for( int i = 0; i < str.length(); i++ ){       
			 		temp = str.substring(i,i+1);
          if( temp.charAt(0)>47 && temp.charAt(0)<58 ){
             numeral += temp;
          }
      }
		 return numeral;
	}
}
