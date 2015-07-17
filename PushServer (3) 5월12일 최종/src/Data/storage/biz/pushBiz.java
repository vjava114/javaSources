package Data.storage.biz;

import java.util.ArrayList;
import java.util.HashMap;

import Data.storage.dao.pushDAO;

public class pushBiz  implements ITBBiz { 

	private pushDAO dao = new  pushDAO();
 

	public Object insert(HashMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
	public Object update(HashMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
	public Object delete(HashMap<String, String> map) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
	public ArrayList<HashMap<String, String>> select()
			throws Exception {
		// TODO Auto-generated method stub
		return dao.select();
	}

	public ArrayList<HashMap<String, String>> select(HashMap<String, String> map)
			throws Exception {
		// TODO Auto-generated method stub
		return dao.select(map);
	}
	
}
