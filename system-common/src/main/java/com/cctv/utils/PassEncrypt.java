package com.cctv.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class PassEncrypt {
	//加密
	public static String PassAes(String pass,String keys) throws Exception{
		String Complete = "aaaaaaaaaaaaaaaa";
		keys = keys + Complete.substring(0, 16 - keys.length());	
		return Encrypt(pass,keys);		
	}
	
	public static String passwordDecrypt(String password,String key){
		String Complete = "aaaaaaaaaaaaaaaa";
		String res = "";
		try{
			key = key + Complete.substring(0, 16 - key.length());
			res = Decrypt(password,key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return res;
	}
	// 加密
    private static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/不进行补齐"
        //int blockSize = cipher.getBlockSize();
        byte[] dataBytes = sSrc.getBytes();
        /* int newDataBytesLength = dataBytes.length;
        if (newDataBytesLength % blockSize != 0) {
            newDataBytesLength = newDataBytesLength + (blockSize - (newDataBytesLength % blockSize));
        }
        byte[] newDataBytes = new byte[newDataBytesLength];
        System.arraycopy(dataBytes, 0, newDataBytes, 0, dataBytes.length);*/
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(dataBytes);

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }
    // 解密
    private static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                System.out.print("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                System.out.print("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}
