package Data.Service;

import java.util.HashMap;

public interface Push {
	
	public Object input (HashMap<String, String> map) throws Exception;
	public Object output () throws Exception;
	public Object process() throws Exception;
	public Object run () throws Exception;

}
