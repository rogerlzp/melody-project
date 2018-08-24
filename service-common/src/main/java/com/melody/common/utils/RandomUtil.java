package com.melody.common.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author wangdejun
 * @version 1.0
 * 随机数生成器 tt_exchange_code兑奖码表
 */
public class RandomUtil {
	private static String[] chars = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private static int bound = 36;//区间
	private static int length = 10;//获取字符串长度，默认10
	
	/**
	 * 获取随机字符串
	 * @return code 随机字符串
	 */
	public static String generateCode(){
		String code = "";
		for (int i=0; i<length; i++) {
			Random random = new Random();
			int m = random.nextInt(bound);
			code += chars[m];
		}
		return code;
	}
	/**
	 * 获取随机字符串
	 * @param len 字符串长度
	 * @return code 随机字符串
	 */
	public static String generateCode(int len){
		String code = "";
		for (int i=0; i<len; i++) {
			Random random = new Random();
			int m = random.nextInt(bound);
			code += chars[m];
		}
		return code;
	}
	/**
	 * 获取随机兑奖码
	 * @param num 个数
	 * @return codes 兑奖码
	 */
	public static Set<String> getCodes(int num) {
		Set<String> codes = new HashSet<String>();
		for (int i = 0; i < num; i++) {
			String code = generateCode();
			codes.add(code);
		}
		return codes;
	}
	
	/**
	 * 获取随机兑奖码
	 * @param num 个数
	 * @param len 每个长度
	 * @return codes 兑奖码
	 */
	public static Set<String> getCodes(int num, int len) {
		Set<String> codes = new HashSet<String>();
		for (int i = 0; i < num; i++) {
			String code = generateCode(len);
			codes.add(code);
		}
		return codes;
	}
	/**
	 * 主方法
	 * @param args
	 */
	public static void main(String[] args) {
		//验证码规则：通道编号4为+前缀3位+随机码9为 共16位 如：DXTD000111222333
		String td = "JRTT";//通道编号：[JRTT今日头条][DXTD短信通道] 
		String prefix = "HJD";//前缀
		Set<String> prefixs = new HashSet<String>();
		prefixs.add("000");//已用编号
		prefixs.add("AAA");
		boolean flag = true;
		while (flag) {
			String prefixTmp = RandomUtil.generateCode(3);
			if (!prefixs.contains(prefixTmp)) {
				flag = false;
				prefix = prefixTmp;
			}
		}
		int len = 3000;//生成个数
		Set<String> codes = RandomUtil.getCodes(len,9);
		//System.out.println(codes.size());
		int id = 1000001;//起始ID
		int couponId = 1000006;
		/*
		for (String code:codes) {
			System.out.println("insert into TT_EXCHANGE_CODE(ID,USER_ID,CASHBACK_COUPON_ID,C_CODE,C_ISUSE,C_STATUS,C_MEMO,CREATE_DATE,UPDATE_DATE) values ");
			System.out.println("("+ id +",null," + couponId +",'" + td+prefix+code + "','WD','YX',null,'2016/04/20 12:00:00',null);");
			id++;
		}
		*/
		System.out.println("insert into TT_EXCHANGE_CODE(ID,USER_ID,CASHBACK_COUPON_ID,C_CODE,C_ISUSE,C_STATUS,C_MEMO,CREATE_DATE,UPDATE_DATE) values ");
		for (String code:codes) {
			System.out.println("("+ id +",null," + couponId +",'" + td+prefix+code + "','WD','YX',null,'2016/04/20 12:00:00',null),");
			id++;
		}
	}
	
}
