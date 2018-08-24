package com.melody.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 
 * Created by zhanggt on 2015-12-24.
 * 
 */
public class NumberUtil {

	public static final short ADD = 1;

	public static final short SUBTRACT = 2;

	public static final short MULTIPLY = 3;

	public static final short DIVIDE = 4;

	private static final int DEFAULT_DIV_SCALE = 2;

	public static Double calc(Double v1, Double v2, short operation) {
		if (v1 == null) {
			throw new RuntimeException("v1 is null.");
		}
		if (v2 == null) {
			throw new RuntimeException("v2 is null.");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		switch (operation) {
		case NumberUtil.ADD:
			return new Double(b1.add(b2).doubleValue());
		case NumberUtil.SUBTRACT:
			return new Double(b1.subtract(b2).doubleValue());
		case NumberUtil.MULTIPLY:
			return new Double(b1.multiply(b2).doubleValue());
		case NumberUtil.DIVIDE:
			return new Double(b1.divide(b2, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue());
		default:
			throw new RuntimeException("operation error");
		}
	}

	public static Double divide(Double v1, Double v2, int scale) {
		if (v1 == null) {
			throw new RuntimeException("v1 is null.");
		}
		if (v2 == null) {
			throw new RuntimeException("v2 is null.");
		}
		BigDecimal b1 = new BigDecimal(v1.toString());
		BigDecimal b2 = new BigDecimal(v2.toString());
		return new Double(b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue());

	}

	public static void main(String[] args) {
		double v1 = 1200;
		double v2 = 0.68;
		System.out.println(divide(v1, v2, 0));
	}

	public static String double2String(Double d) {
		if (null == d)
			return "";
		DecimalFormat decimalFormat = new DecimalFormat("#0.00");
		return decimalFormat.format(d);
	}


}
