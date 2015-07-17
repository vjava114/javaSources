package com.Modules;

import java.util.HashMap;

public interface Sender {
 
		
	public Object input (HashMap<String, String> map) throws Exception;
	public Object output () throws Exception;
	public Object run () throws Exception;
}
