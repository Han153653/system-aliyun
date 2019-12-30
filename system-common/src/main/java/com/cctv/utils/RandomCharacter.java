package com.cctv.utils;

import java.util.Random;

public class RandomCharacter {
	/** 
	  * 生成随即密码 
	  * @param pwd_len 生成的密码的总长度 
	  * @return  密码的字符串 
	  */  
	 public static String genRandomNum(int len){  
	  //35是因为数组是从0开始的，26个字母+10个数字  
	  final int  maxNum = 36;  
	  int i;  //生成的随机数  
	  int count = 0; //生成的密码的长度  
	  char[] str = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',  
	    'K', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',  
	    'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };      
	  StringBuffer sb = new StringBuffer("");  
	  Random r = new Random();  
	  while(count < len){  
	   //生成随机数，取绝对值，防止生成负数， 
	   i = Math.abs(r.nextInt(maxNum));  //生成的数最大为36-1   
	   if (i >= 0 && i < str.length) {  
	    sb.append(str[i]);  
	    count ++;  
	   }  
	  }     
	  return sb.toString();  
	 }
	 /*public static void main(String[] args) {
		System.out.println(genRandomNum(4));
	}*/
}
