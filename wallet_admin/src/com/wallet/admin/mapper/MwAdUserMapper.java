package com.wallet.admin.mapper;


import java.util.HashMap;
import java.util.List;

import com.wallet.admin.model.MwAdUser;


public interface MwAdUserMapper {
	
	List<MwAdUser> selectUserList(HashMap<String, Object> params);
	
	int insertUserReg(HashMap<String, Object> params);

	int deleteUserDtl(HashMap<String, Object> params);

	int updateUserDtl(HashMap<String, Object> params);
	
	int updateUserPasswd(HashMap<String, Object> params);
	
}