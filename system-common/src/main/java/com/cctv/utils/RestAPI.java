package com.cctv.utils;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.cctv.entity.ResultValue;

public class RestAPI {
	public static ResultValue callRestServices(String restUrl,String token) {
		ResultValue rv = new ResultValue();
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);        
        String encodedToken = Base64.encodeBase64String("sysadmin:password".getBytes());
        headers.set("Authorization",encodedToken);
        
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        RestTemplate restTemplate = new RestTemplate();
        //解决中文乱码问题
        resetCnMessageConverter(restTemplate);
        ResponseEntity<String> loginResponse = restTemplate
                .exchange(restUrl, HttpMethod.GET, entity, String.class);

	    System.out.println("HttpStatus:"+ loginResponse.getStatusCode() + "HttpStatusBody:" + loginResponse.getBody());
	    rv.setStatusCode(loginResponse.getStatusCode().toString());
	    rv.setStrResult(loginResponse.getBody());
        return rv;
	}
	/*
     *初始化RestTemplate，RestTemplate会默认添加HttpMessageConverter
     * 添加的StringHttpMessageConverter非UTF-8
     * 所以先要移除原有的StringHttpMessageConverter，
     * 再添加一个字符集为UTF-8的StringHttpMessageConvert
     * */
    private static void resetCnMessageConverter(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> converterList = restTemplate.getMessageConverters();
        HttpMessageConverter<?> converterTarget = null;
        for (HttpMessageConverter<?> item : converterList) {
            if (item.getClass() == StringHttpMessageConverter.class) {
                converterTarget = item;
                break;
            }
        }
        if (converterTarget != null) {
            converterList.remove(converterTarget);
        }
        HttpMessageConverter<?> converter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        converterList.add(converter);
    }
}
