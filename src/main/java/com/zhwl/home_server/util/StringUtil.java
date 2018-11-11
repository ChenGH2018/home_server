package com.zhwl.home_server.util;

import java.util.Date;

/**
 * 字符串相关方法
 *
 */
public class StringUtil {

	private static final char strLetter[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char strNumber[] = "0123456789".toCharArray();

	/**
	 * 获取时段的中文
	 */
	public static String getPeriodStr(Integer period){
		switch (period) {
			case 1: return  "早餐";
			case 2: return "午餐";
			case 3: return "晚餐";
			default: return "";
		}
	}
	/**
	 * 随机生成11位字符串，由4位字符+10位数字组成
	 */
	public static String generateSendungsnummer(){
		String sendungsnummer = "";
		for(int i = 0 ;i<4;i++){
			int j = (int) (Math.random() * strLetter.length - 1);
			sendungsnummer += strLetter[j];
		}
		return sendungsnummer+new Date().getTime();
	}
	/**
	 * 将以逗号分隔的字符串转换成字符串数组
	 * @param valStr
	 * @return String[]
	 */
	public static String[] StrList(String valStr){
	    int i = 0;
	    String TempStr = valStr;
	    String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
	    valStr = valStr + ",";
	    while (valStr.indexOf(',') > 0)
	    {
	        returnStr[i] = valStr.substring(0, valStr.indexOf(','));
	        valStr = valStr.substring(valStr.indexOf(',')+1 , valStr.length());
	        
	        i++;
	    }
	    return returnStr;
	}
	
	/**获取字符串编码
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {      
	       String encode = "GB2312";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s = encode;      
	              return s;      
	           }      
	       } catch (Exception exception) {      
	       }      
	       encode = "ISO-8859-1";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s1 = encode;      
	              return s1;      
	           }      
	       } catch (Exception exception1) {      
	       }      
	       encode = "UTF-8";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s2 = encode;      
	              return s2;      
	           }      
	       } catch (Exception exception2) {      
	       }      
	       encode = "GBK";      
	      try {      
	          if (str.equals(new String(str.getBytes(encode), encode))) {      
	               String s3 = encode;      
	              return s3;      
	           }      
	       } catch (Exception exception3) {      
	       }      
	      return "";      
	   } 
	
}
