package my.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import my.dao.*;

public class ListModel implements Model {

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		
//		List<BoardVO> temp=BoardDAO.getboardAllData();	// �����Ͱ�������		
//		List<BoardVO> list=new ArrayList<BoardVO>();	// list��� �����ֱ� ���ؼ� Ʋ�� ��������
//		
		List<BoardVO> list = BoardDAO.getboardAllData();
		
		
		
//		for(int i=0;i<temp.size();i++)
//		{
//			BoardVO vo=temp.get(i);			// �����ͼ��� ���ڵ� �ϳ��� vo�� ��Ƽ�
//			list.add(vo);			// ���ο� ���ڵ忡 ��´�...
//		}
		
		
		request.setAttribute("list", list);
		
		
		
		
		
		return "inogard";
	}

}
