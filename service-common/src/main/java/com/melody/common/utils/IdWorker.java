package com.melody.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyw on 2015/12/15.
 */
public class IdWorker {

	private static Map<String, Integer> orderNumMap = new HashMap<String, Integer>();

	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	public static synchronized String getOrderNo(String orderPrefix) {
		Date currentDate = new Date();
		String str = new SimpleDateFormat("yyMMddHHmmss").format(currentDate);
		String hour = new SimpleDateFormat("yyMMddHH").format(currentDate);
		String orderKey = buildKey(orderPrefix, hour);
		Integer orderNum = orderNumMap.get(orderKey);
		if (orderNum == null) {
			orderNum = 1;
			orderNumMap.put(orderKey, orderNum);
		} else {
			orderNum++;
			orderNumMap.put(orderKey, orderNum);
		}
		return formatOrderNum(orderPrefix, str, orderNum);
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getOrderNo("PO"));
		}
	}

	private static String formatOrderNum(String orderPrefix, String str, Integer orderNum) {
		DecimalFormat format = new DecimalFormat("0000");
		String orderNumStr = format.format(orderNum);
		return orderPrefix + str + orderNumStr;
	}

	private static String buildKey(String orderPrefix, String hour) {
		return orderPrefix + "_" + hour;
	}

	/**
	 * 获取商户下单时间
	 * 
	 * @return
	 */
	public static synchronized String getMerDate() {
		String str = new SimpleDateFormat("yyyyMMdd").format(new Date());

		return str;
	}

}
