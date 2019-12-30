package com.cctv.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 查询参数
 *
 */
public class Page extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	private static final int PAGE_SIZE = 4;
	//当前页码
    private int page;
    //每页条数
    private int limit;

    public Page(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("currPage").toString());
        this.limit = Integer.parseInt(params.get("pageSize").toString());
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }
    
    public Page(String currPage,String pageSize){
    	
    	//分页参数
    	if(currPage==null || currPage.isEmpty()){
    		
    		this.page = 1;
    	}else{
            this.page = Integer.parseInt(currPage);
    	}
    	if(pageSize==null || pageSize.isEmpty()){
    		this.limit = PAGE_SIZE;
    	}else{
    		this.limit = Integer.parseInt(pageSize);
    	}
       
        this.put("offset", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
