package com.cctv.utils;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cctv.entity.ResultValue;
import com.cctv.service.SysDictionariesService;



@SuppressWarnings("deprecation")
public class HttpClientUtils {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class.getName());
	private static SysDictionariesService sysDictionariesService=(SysDictionariesService)SpringContextUtils.getBean("sysDictionariesService");
	
	/**
	 * get 发送http请求
	 * @param url
	 * @return
	 */
    public static ResultValue requestByGetMethod(String url){
    	ResultValue rv = new ResultValue();
        //创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        try {
            //用get方法发送http请求
            HttpGet get = new HttpGet(url);
            get.setConfig(getRequestConfig());
            logger.info("执行get请求:....{}", url);
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
            try{
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                	rv.setStatusCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    rv.setStrResult(EntityUtils.toString(entity));
                }
            }
            finally{
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            rv.setStrResult(e.getMessage());
        } finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        logger.info("请求状态：{}---请求返回值：{}",rv.getStatusCode(),rv.getStrResult());
        return rv;
    }
	/**
	 * post发送http请求
	 * @param url
	 * @param data
	 * @return
	 */
	/*
	 * 获取接口地址
	 */
    public static ResultValue requestByPostMethod(String url,String data){
        CloseableHttpClient httpClient = getHttpClient();
        ResultValue rv = new ResultValue();
        try {
            HttpPost httpPost = new HttpPost(url); 
            httpPost.setConfig(getRequestConfig());
            //utf-8格式编码
            StringEntity requestEntity = new StringEntity(data,"UTF-8");  
            requestEntity.setContentEncoding("UTF-8");                
            httpPost.setHeader("Content-type", "application/json");  
            httpPost.setEntity(requestEntity); 
            logger.info("POST 请求....{}----请求参数：{}", url, data);
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                	rv.setStatusCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    rv.setStrResult(EntityUtils.toString(entity));
                }
            } finally{
                httpResponse.close();
            }
             
        } catch(Exception e){
            e.printStackTrace();
            rv.setStrResult(e.getMessage());
        }finally{
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
       logger.info("请求状态：{}---请求返回值：{}",rv.getStatusCode(),rv.getStrResult());
       return rv;  
    }
     
    /**
	 * post发送http请求,xml格式请求，这里使用了UTF-8
	 * @param url
	 * @param data
	 * @return
	 */
    public static ResultValue requestByPostMethodWithUTFXml(String url,String data){
    	System.out.println("data是"+data);
        CloseableHttpClient httpClient = getHttpClient();
        ResultValue rv = new ResultValue();
        try {
            HttpPost httpPost = new HttpPost(url);   
            httpPost.setConfig(getRequestConfig());
            System.out.println("data数据是"+data);
            //GB2312格式编码
            StringEntity requestEntity = new StringEntity(data,"UTF-8");  
            requestEntity.setContentEncoding("UTF-8");                
            httpPost.setHeader("Content-type", "application/xml");  
            httpPost.setEntity(requestEntity); 
            System.out.println("requestEntitys是"+requestEntity.toString());
            logger.info("POST 请求....{}----参数：{}", httpPost.getURI(),data);
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            try{
                HttpEntity entity = httpResponse.getEntity();
                System.out.println("响应的entity是"+entity);
                if (null != entity){
                	rv.setStatusCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    rv.setStrResult(EntityUtils.toString(entity));
                    System.out.println("resultvalue是"+rv.toString());
                }
            } finally{
                httpResponse.close();
            }
             
        } catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        logger.info("请求状态：{}---请求返回值：{}",rv.getStatusCode(),rv.getStrResult());
        return rv;  
    }  
    /**
     * 
    * @Description: 该函数的功能描述，这里使用了GB2312
    * @author: Xiaoming Guan
    * @date: 2018年6月24日 下午12:56:32
     */
    public static ResultValue requestByPostMethodWithXml(String url,String data){
    	System.out.println("data是"+data);
        CloseableHttpClient httpClient = getHttpClient();
        ResultValue rv = new ResultValue();
        try {
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(getRequestConfig());
            System.out.println("data数据是"+data);
            //GB2312格式编码
            StringEntity requestEntity = new StringEntity(data,"GB2312");  
            requestEntity.setContentEncoding("GB2312");                
            httpPost.setHeader("Content-type", "application/xml");  
            httpPost.setEntity(requestEntity); 
            System.out.println("requestEntitys是"+requestEntity.toString());
            logger.info("POST 请求....{}----参数：{}", httpPost.getURI(),data);
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            try{
                HttpEntity entity = httpResponse.getEntity();
                System.out.println("响应的entity是"+entity);
                if (null != entity){
                	rv.setStatusCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    rv.setStrResult(EntityUtils.toString(entity));
                    System.out.println("resultvalue是"+rv.toString());
                }
            } finally{
                httpResponse.close();
            }
             
        } catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        logger.info("请求状态：{}---请求返回值：{}",rv.getStatusCode(),rv.getStrResult());
        return rv;  
    }  
    
    public static RequestConfig getRequestConfig() {
		Integer delay = Integer.valueOf(sysDictionariesService.getValueByCode("HTTP_TIMEOUT")) * 1000;
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(delay).setConnectTimeout(delay).setConnectionRequestTimeout(delay).build();
        return requestConfig;
	}
    
    private static CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }
     
    private static void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }
    /**
     * 
    * @Description: 
    *        display8.0版本发请求要求没有ContentEnoding，不支持utf-8，否则会报415错误，Unsupported Media Type 
    * @author: Xiaoming Guan
    * @date: 2018年3月15日 下午2:02:43
     */
    public static ResultValue requestByPostWithoutContentEncoding(String url,String data){
        CloseableHttpClient httpClient = getHttpClient();
        ResultValue rv = new ResultValue();
        try {
            HttpPost httpPost = new HttpPost(url);    
            httpPost.setConfig(getRequestConfig());
            //utf-8格式编码
            StringEntity requestEntity = new StringEntity(data,"UTF-8"); 
            httpPost.setHeader("Content-type", "application/json");  
            httpPost.setEntity(requestEntity); 
            logger.info("POST 请求....{}----请求参数：{}", url, data);
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                	rv.setStatusCode(String.valueOf(httpResponse.getStatusLine().getStatusCode()));
                    rv.setStrResult(EntityUtils.toString(entity));
                }
            } finally{
                httpResponse.close();
            }
             
        } catch(Exception e){
            e.printStackTrace();
            rv.setStrResult(e.getMessage());
        }finally{
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
       logger.info("请求状态：{}---请求返回值：{}",rv.getStatusCode(),rv.getStrResult());
       return rv;  
    }

}
