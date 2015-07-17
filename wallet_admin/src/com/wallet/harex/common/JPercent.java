package com.wallet.harex.common;

import java.text.DecimalFormat;

public class JPercent {
	
	public JPercent() {
		
	}
	
	public static String getPercentage(int now, int pre) {
		double dPre = 0d;
		double dNow = 0d;

		if (pre == 0) {
			dPre = 1d;
		} else {
			dPre = (double) pre;
		}
		
		if (now == 0) {
			dNow = 1d;
		} else {
			dNow = (double) now;
		}

		double result = 0d;
		
		if(pre != 0 && now != 0) {
			result = ((dNow - dPre) / dPre) * 100d;
		}
		
		DecimalFormat decimalFormat = new DecimalFormat("##0.0");
		String percent = decimalFormat.format(result);

		return percent;
	}
}
