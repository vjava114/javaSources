package my.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {
	public String execute(HttpServletRequest request)	// �𵨸� �޾Ƽ� �׸� �����ų�� �ִ�.
	throws Exception;

}



// listmodel, insertmodel ���� �����Ű�� ���� �������̽�!!
