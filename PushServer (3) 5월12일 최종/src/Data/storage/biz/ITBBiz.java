package Data.storage.biz;

import java.util.ArrayList;
import java.util.HashMap;

public interface ITBBiz {

	public Object insert(HashMap<String, String> map) throws Exception;

	public Object update(HashMap<String, String> map) throws Exception;

	public Object delete(HashMap<String, String> map) throws Exception;
 
	public ArrayList<HashMap<String, String>> select(HashMap<String, String> map)
			throws Exception;
	
}
