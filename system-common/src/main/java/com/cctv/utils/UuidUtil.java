package com.cctv.utils;

import java.util.UUID;

public class UuidUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replace("-", "");
		return uuid;
	}
	
/*	public static void main(String[] args) {
		System.out.println(get32UUID());
	}*/
}

