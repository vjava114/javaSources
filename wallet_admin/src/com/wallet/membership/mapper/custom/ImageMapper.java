package com.wallet.membership.mapper.custom;

import java.util.HashMap;
import java.util.List;

import com.wallet.membership.model.custom.Image;

public interface ImageMapper {
	
	/**
	 * @Method Name : insertImage
	 * @Description : �����(ī������) ���
	 * @param : HashMap<String ,Object>
	 * @return : Integer
	 * @author ���¸�
	 * @since 2012.09.10
	 */
	Integer insertImage(HashMap<String ,Object> params);

}
