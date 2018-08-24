package com.melody.common.utils;

/**
 * 
 * Created by zhanggt on 2016-1-3.
 * 
 */
public class ThreadUtil {

	public static void sleep(int interval) {
		try {
			Thread.sleep(interval);
		} catch (InterruptedException e) {
		}
	}

}
