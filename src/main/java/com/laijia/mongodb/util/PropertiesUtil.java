package com.laijia.mongodb.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class PropertiesUtil {

	private static final String encoding = "UTF-8";
	private static PropertiesConfiguration mongodbConfig ;
	private static PropertiesConfiguration activemqConfig ;
	static{
		mongodbConfigInit();
		activemqConfigInit();
	}
	public static void mongodbConfigInit() {
		try {
			mongodbConfig = new PropertiesConfiguration("config/mongodb.properties");
			mongodbConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
			mongodbConfig.setEncoding(encoding);
		} catch (ConfigurationException e) {
		}
	}
	public static void activemqConfigInit() {
		try {
			activemqConfig = new PropertiesConfiguration("config/activemq.properties");
			activemqConfig.setReloadingStrategy(new FileChangedReloadingStrategy());
			activemqConfig.setEncoding(encoding);
		} catch (ConfigurationException e) {
		}
	}
	public static String getMongodbString(String key) {
		String str = "";
		try {
			str = mongodbConfig.getString(key);
		} catch (Exception ex) {
			str = "";
		}
		return str;
	}
	public static Integer getMongodbInteger(String key) {
		Integer str = 0;
		try {
			str =Integer.parseInt(mongodbConfig.getString(key));
		} catch (Exception ex) {
			str = 0;
		}
		return str;
	}
	
	public static String getActivemqString(String key) {
		String str = "";
		try {
			str = activemqConfig.getString(key);
		} catch (Exception ex) {
			str = "";
		}
		return str;
	}
	public static Integer getActivemqInteger(String key) {
		Integer str = 0;
		try {
			str =Integer.parseInt(activemqConfig.getString(key));
		} catch (Exception ex) {
			str = 0;
		}
		return str;
	}
}
