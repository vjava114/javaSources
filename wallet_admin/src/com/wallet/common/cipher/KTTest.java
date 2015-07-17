package com.wallet.common.cipher;


public class KTTest {
	public static void main(String[] args) {
		/**
		 * �˰��� ���̵�
		 * 1: SEED,2 : 3DES, 3 : DES, 4 : AES128
		 * */
		KTSSOCipher ssoCipher = new KTSSOCipher();
		ssoCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		String EncodingTest = ssoCipher.encoding("���ڵ� �׽�Ʈ");
		String DecodingTest = ssoCipher.decoding(EncodingTest, ssoCipher.getEnc_key());
		String HashTest = ssoCipher.hash("�ؽ� �׽�Ʈ");
		
		System.out.println("=====================================");
		System.out.println("KTSSOCipher �׽�Ʈ");
		System.out.println("=====================================");
		System.out.println("EncodingTest : " + EncodingTest);
		System.out.println("DecodingTest : " + DecodingTest);
		System.out.println("HashTest : " + HashTest);
		System.out.println("=====================================");
		System.out.println("");
		
		KTDBCipher dbCipher = new KTDBCipher();
		dbCipher.setAlgoID("1");//�˰��� ���̵� SEED�� ���� ����Ʈ SEED
		String EncodingTest2 = dbCipher.encoding("���ڵ� �׽�Ʈ");
		String DecodingTest2 = dbCipher.decoding("v+iAwtBOktVCc5nVBmUQcg==");
		String HashTest2 = dbCipher.hash("�ؽ� �׽�Ʈ");
		
		System.out.println("=====================================");
		System.out.println("KTDBCipher �׽�Ʈ");
		System.out.println("=====================================");
		System.out.println("EncodingTest : " + EncodingTest2);
		System.out.println("DecodingTest : " + DecodingTest2);
		System.out.println("HashTest : " + HashTest2);
	}
}
