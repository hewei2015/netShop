package cn.hw.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IDGeneratorUtil {
	public static String genPrimaryKey(){
		return UUID.randomUUID().toString();
	}
	
	//生成与当前时间有关系的订单号，20150302+毫秒值
	public static String genOrdersNum(){
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String str = df.format(now)+System.currentTimeMillis();
		return str;
	}
}
