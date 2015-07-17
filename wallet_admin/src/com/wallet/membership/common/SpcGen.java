package com.wallet.membership.common;

public class SpcGen {
	
	public static void main(String[] args) {
		String aaaa = SPCBarcodeGenMake("548346423073421");
		System.out.println(aaaa);
	}
	
	public static String SPCBarcodeGenMake(String Barcode) {
		// TODO Auto-generated constructor stub
		String mappingStr = "2121212121212121";
		int sum = 0;
		
		for(int i=0;i<Barcode.length();i++){
			long m = 0;
			
			m = Integer.parseInt(String.valueOf(Barcode.charAt(i)))*Integer.parseInt(String.valueOf(mappingStr.charAt(i)));
			if(m>=10){
				sum += ((m/10)+(m%10));
			}else{
				sum += m;
			}
		}
		sum = 10-(sum%10);
		if(sum==0){
			sum=10;
		}
		String specialChar = "#)&$@%!^~(";
		String checkChar = "";
		
		checkChar = String.valueOf(specialChar.charAt(sum-1));
		
		if(checkChar.equals("~"))checkChar = "~~";
		String newCode = Barcode + checkChar;
		return newCode;
	}
}
