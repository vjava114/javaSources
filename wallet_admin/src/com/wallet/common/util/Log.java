package com.wallet.common.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log {
	private static Logger SYS_LOG;
	
	static{
		PropertyConfigurator.configure(PropertiesUtil.get("log"));
		SYS_LOG = Logger.getLogger("logs");
	}

	
	public static Logger getLogger(String name) {
		PropertyConfigurator.configure(PropertiesUtil.get("log"));
		return Logger.getLogger(name);
	}
	
	public static void debug(String msg) {
		SYS_LOG.debug(msg);
	}
	public static void info(String msg) {
		SYS_LOG.info(msg);
	}
	public static void error(String msg) {
		SYS_LOG.error(msg);
	}
	public static void warn(String msg) {
		SYS_LOG.warn(msg);
	}
	public static void fatal(String msg) {
		SYS_LOG.fatal(msg);
	}
}
