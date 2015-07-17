package my.model;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import my.dao.*;

public class ListModel implements Model {

	@Override
	public String execute(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
		
//		List<BoardVO> temp=BoardDAO.getboardAllData();	// 데이터가져오자		
//		List<BoardVO> list=new ArrayList<BoardVO>();	// list라고 던져주기 위해서 틀을 만들어놓자
//		
		List<BoardVO> list = BoardDAO.getboardAllData();
		
		
		
//		for(int i=0;i<temp.size();i++)
//		{
//			BoardVO vo=temp.get(i);			// 데이터셋의 레코드 하나식 vo에 담아서
//			list.add(vo);			// 새로운 레코드에 담는다...
//		}
		
		
		request.setAttribute("list", list);
		
		
		
		
		
		return "inogard";
	}

}
