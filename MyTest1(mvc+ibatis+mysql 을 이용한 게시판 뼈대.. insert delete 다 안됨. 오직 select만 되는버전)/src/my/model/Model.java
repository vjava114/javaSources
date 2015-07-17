package my.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {
	public String execute(HttpServletRequest request)	// 모델명 받아서 그모델 실행시킬수 있다.
	throws Exception;

}



// listmodel, insertmodel 등을 실행시키기 위한 인터페이스!!
