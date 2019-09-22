package com.youfan.util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class PropertyRead {
	private static Config config = ConfigFactory.load();
	
	public static String getkey(String key){
		return config.getString(key).trim();
	}
}
