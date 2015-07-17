package com.wallet.membership.web.base;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wallet.common.util.PropertiesUtil;

@Controller
public class CouponSVRPropertyController {	


	@RequestMapping(value="/member/coupon_svr_property_view.ms")
	public ModelAndView fileread () throws Exception {

		String str = new String();
		try{
			File file = new File(PropertiesUtil.get("mcserver_property_path"));
			FileInputStream fls = new FileInputStream(file);

			BufferedInputStream bis = new BufferedInputStream(fls);

			byte[] buffer = new byte[64];

			int len = 0;


			while ((len = bis.read(buffer, 0, buffer.length))!= -1){

				str += new String (buffer, 0, len, "EUC-KR");

			}
		}catch(Exception e) {

			str ="파일이 존재하지 않습니다.";
		}


		return new ModelAndView("member/coupon_svr_property_view", "scn", str);


	}


}
