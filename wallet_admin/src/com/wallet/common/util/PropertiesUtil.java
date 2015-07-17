package com.wallet.common.util;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;

public class PropertiesUtil {

	public static Properties props = new Properties();

	private static String url;
	private static String server;

	public static void init(String url, String server, String fileName) {
		PropertiesUtil.url = url;
		PropertiesUtil.server = server;
		try {
			props.load(new FileInputStream(url + fileName + ".properties"));
			initBaseProperties();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void init(String url) {
		try {
			props.load(new FileInputStream(url));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void initBaseProperties() {
		String properties = props.getProperty("properties");
		String[] propertie = properties.split(",");
		for (int i = 0; i < propertie.length; i++) {
			String prefix = props.getProperty(propertie[i].trim() + "_prefix");
			String suffix = props.getProperty(propertie[i].trim() + "_suffix");
			if (prefix != null && prefix.length() > 0) {
				props.setProperty(propertie[i].trim(), url + prefix + "_" + server
						+ suffix);
			} else {
				props.setProperty(propertie[i].trim(), url);
			}
		}

		String loads = props.getProperty("loads");
		String[] load = loads.split(",");
		for (int i = 0; i < load.length; i++) {
			try {
				Properties subprop = new Properties();
				subprop.load(new FileInputStream(props.getProperty(load[i].trim())));
				props.putAll(subprop);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (server.equals("local"))
			test();
	}

	private static void test() {
		Iterator<Object> iterator = props.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			System.out.println("Properties : " + key + "="
					+ props.getProperty(key));
		}

	}

	public static String get(String Key) {
		return props.getProperty(Key);
	}
}
