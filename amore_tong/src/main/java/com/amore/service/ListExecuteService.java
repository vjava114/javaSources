package com.amore.service;

import javax.servlet.http.HttpServletRequest;


public interface ListExecuteService {
	// 컨트롤러의 명령을 받아 리스트 출력을 실행하게 해주는 클래스
	public String Execute(HttpServletRequest request) throws Exception;
	
}
