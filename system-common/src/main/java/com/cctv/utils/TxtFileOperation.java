package com.cctv.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;  
  
public class TxtFileOperation {  
   
	 /** 
	  * 创建文件 
	  * @param fileName 
	  * @return 
	  */  
	 public static boolean createFile(File fileName)throws Exception{  
		  boolean flag=false;  
		  try{  
			   if(!fileName.exists()){  
				   fileName.createNewFile();  
				   flag=true;  
			   }  
		  }catch(Exception e){  
			  e.printStackTrace();  
		  }  
		  return true;  
	 }   
   
	 /** 
	  * 读TXT文件内容 
	  * @param fileName 
	  * @return 
	  */  
	 public static String readTxtFile(File fileName)throws Exception{  
		  String result="";  
		  FileReader fileReader=null;  
		  BufferedReader bufferedReader=null;  
		  try{  
			   fileReader=new FileReader(fileName);  
			   bufferedReader=new BufferedReader(fileReader);  
			   try{  
				    String read=null;  
				    while((read=bufferedReader.readLine())!=null){  
				    	result=result+read+"\r\n";  
				    }  
			   }catch(Exception e){  
				   e.printStackTrace();  
			   }  
		  }catch(Exception e){  
			  e.printStackTrace();  
		  }finally{  
			   if(bufferedReader!=null){  
				   bufferedReader.close();  
			   }  
			   if(fileReader!=null){  
				   fileReader.close();  
			   }  
		  }  
		  System.out.println("读取出来的文件内容是："+"\r\n"+result);  
		  return result;  
	 }  
   
	 /**
	  * 生成txt
	  * @param content
	  * @param fileName
	  * @return
	  * @throws Exception
	  */
	 public static boolean writeTxtFile(String content,File  fileName)throws Exception{  
		  RandomAccessFile mm=null;  
		  boolean flag=false;  
		  FileOutputStream o=null;  
		  try {  
			  o = new FileOutputStream(fileName);  
		      o.write(content.getBytes("UTF-8"));  
		      o.close();  
		//   mm=new RandomAccessFile(fileName,"rw");  
		//   mm.writeBytes(content);  
		      flag=true;  
		  } catch (Exception e) {  
			   // TODO: handle exception  
			   e.printStackTrace();  
		  }finally{  
			   if(mm!=null){  
				   mm.close();  
			   }  
		  }  
		  return flag;  
	 }  
	  
  
  
 	public static void contentToTxt(String filePath, String content) {  
        String str = new String(); //原有txt内容  
        String s1 = new String();//内容更新  
        try {  
            File f = new File(filePath);  
            if (f.exists()) {  
                System.out.print("文件存在");  
            } else {  
                System.out.print("文件不存在");  
                f.createNewFile();// 不存在则创建  
            }  
            BufferedReader input = new BufferedReader(new FileReader(f));  
  
            while ((str = input.readLine()) != null) {  
                s1 += str + "\n";  
            }  
            System.out.println(s1);  
            input.close();  
            s1 += content;  
  
            BufferedWriter output = new BufferedWriter(new FileWriter(f));  
            output.write(s1);  
            output.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
  
        }  
    }
 	
 	public static String readTxtFile(String fileName){
 		StringBuffer sBuffer = new StringBuffer();
 		FileInputStream fInputStream=null;  
		BufferedReader in =null;
		try {
			String code = codeString(fileName);
			fInputStream = new FileInputStream(fileName);  
	 		InputStreamReader inputStreamReader = new InputStreamReader(fInputStream, code);  
	 		in = new BufferedReader(inputStreamReader);    
	 		String strTmp = "";  
	 		//按行读取  
	 		while (( strTmp = in.readLine()) != null) {  
	 		    sBuffer.append(strTmp + "\r\n");  
	 		}  
		} catch (Exception e) {
			e.printStackTrace();
		} finally{  
			   if(in!=null){  
				   try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
			   }  
			   if(fInputStream!=null){  
				   try {
					fInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}  
			   }  
		  }
 		
 		return sBuffer.toString(); 
 	}
 	
 	/** 
 	 * 判断文件的编码格式 
 	 * @param fileName :file 
 	 * @return 文件编码格式 
 	 * @throws Exception 
 	 */  
 	public static String codeString(String fileName) throws Exception{  
 	    BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));  
 	    int p = (bin.read() << 8) + bin.read();  
 	    String code = null;  
 	      
 	    switch (p) {  
 	        case 0xefbb:  
 	            code = "UTF-8";  
 	            break;  
 	        case 0xfffe:  
 	            code = "Unicode";  
 	            break;  
 	        case 0xfeff:  
 	            code = "UTF-16BE";  
 	            break;  
 	        default:  
 	            code = "GBK";  
 	    }  
 	      
 	    return code;  
 	}  
 	
 	/*public static void main(String[] args) {
		String content = "{\"result\":\"true\",\"list\":[{\"TAG_NAME\":\"体育\",\"TAG_ID\":\"TagSport\",\"PARENTTAG\":\"Tag\"},{\"TAG_NAME\":\"电视剧\",\"TAG_ID\":\"TagTv\",\"PARENTTAG\":\"Tag\"},{\"TAG_NAME\":\"戏曲\",\"TAG_ID\":\"TagOpera\",\"PARENTTAG\":\"Tag\"},{\"TAG_NAME\":\"音乐\",\"TAG_ID\":\"TagMusic\",\"PARENTTAG\":\"Tag\"},{\"TAG_NAME\":\"社会与法\",\"TAG_ID\":\"TagSocietyAndLaw\",\"PARENTTAG\":\"Tag\"},{\"TAG_NAME\":\"财经\",\"TAG_ID\":\"TagEconomics\",\"PARENTTAG\":\"Tag\"},{\"TAG_NAME\":\"体育新闻\",\"TAG_ID\":\"TagNewsSport\",\"PARENTTAG\":\"TagSport\"},{\"TAG_NAME\":\"专题栏目\",\"TAG_ID\":\"TagZoneN\",\"PARENTTAG\":\"TagSport\"},{\"TAG_NAME\":\"赛事集锦\",\"TAG_ID\":\"TagWondN\",\"PARENTTAG\":\"TagSport\"},{\"TAG_NAME\":\"中国骄傲\",\"TAG_ID\":\"TagProud\",\"PARENTTAG\":\"TagSport\"},{\"TAG_NAME\":\"欧洲杯集锦\",\"TAG_ID\":\"TagWond\",\"PARENTTAG\":\"TagSport\"}]}";
		String filePath="D:\\Tag.txt";
		File f = new File(filePath);
		try {
			writeTxtFile(content,f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}*/
  
}  
