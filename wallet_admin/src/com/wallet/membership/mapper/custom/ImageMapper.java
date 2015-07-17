package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Image;

public interface ImageMapper {
	
	/**
	 * @Method Name : insertImage
	 * @Description : 멤버십(카드정보) 등록
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author 김태리
	 * @since 2012.09.10
	 */
	Integer insertImage(HashMap<String ,Object> params);

}
