package com.melody.config;

import com.melody.redis.RedisCache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * Created by zhanggt on 2016-1-3.
 * 
 */
@Component
public class ConfigurationManager {

	@Autowired
	RedisCache redisCache;

	private static final String CONFIG_KEY = "config";

	public String get(String name) {
		return getValue(name);
	}

	public int getInt(String name, int defaultValue) {
		String value = getValue(name);
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		return Integer.parseInt(value);
	}

	public double getDouble(String name, double defaultValue) {
		String value = getValue(name);
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		return Double.parseDouble(value);
	}

	public boolean getBoolean(String name, boolean defaultValue) {
		String value = getValue(name);
		if (value == null || "".equals(value)) {
			return defaultValue;
		}
		return Boolean.parseBoolean(value);
	}
	
	private String getValue(String name) {
		String value = redisCache.hashGet(CONFIG_KEY, name);
		return value;
	}
}
