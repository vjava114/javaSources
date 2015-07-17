package com.wallet.common.cipher;


public class KTTest {
	public static void main(String[] args) {
		/**
		 * 알고리즘 아이디
		 * 1: SEED,2 : 3DES, 3 : DES, 4 : AES128
		 * */
		KTSSOCipher ssoCipher = new KTSSOCipher();
		ssoCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		String EncodingTest = ssoCipher.encoding("디코딩 테스트");
		String DecodingTest = ssoCipher.decoding(EncodingTest, ssoCipher.getEnc_key());
		String HashTest = ssoCipher.hash("해쉬 테스트");
		
		System.out.println("=====================================");
		System.out.println("KTSSOCipher 테스트");
		System.out.println("=====================================");
		System.out.println("EncodingTest : " + EncodingTest);
		System.out.println("DecodingTest : " + DecodingTest);
		System.out.println("HashTest : " + HashTest);
		System.out.println("=====================================");
		System.out.println("");
		
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//알고리즘 아이디를 SEED로 지정 디폴트 SEED
		String EncodingTest2 = dbCipher.encoding("디코딩 테스트");
		String DecodingTest2 = dbCipher.decoding("v+iAwtBOktVCc5nVBmUQcg==");
		String HashTest2 = dbCipher.hash("해쉬 테스트");
		
		System.out.println("=====================================");
		System.out.println("KTDBCipher 테스트");
		System.out.println("=====================================");
		System.out.println("EncodingTest : " + EncodingTest2);
		System.out.println("DecodingTest : " + DecodingTest2);
		System.out.println("HashTest : " + HashTest2);
	}
}
