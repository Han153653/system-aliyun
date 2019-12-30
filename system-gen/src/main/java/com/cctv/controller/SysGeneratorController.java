package com.cctv.controller;

import com.alibaba.fastjson.JSON;
import com.cctv.service.SysGeneratorService;
import com.cctv.utils.PageUtils;
import com.cctv.utils.Query;
import com.cctv.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *  
 */
@Controller
@RequestMapping("/sys/generator")
@Api(value="/sys/generator", tags="根据数据库中的表生成代码")
public class SysGeneratorController {
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
//	@RequiresPermissions("sys:generator:list")
	@ApiOperation(value="列表查询", notes="列表显示数据库中所有表", httpMethod="POST", produces=MediaType.APPLICATION_JSON_VALUE,response=R.class)
	public R list(@ApiParam(value="请求参数(Map<String, Object>)", required=true) @RequestParam Map<String, Object> params){
		//查询列表数据
		Query query = new Query(params);
		List<Map<String, Object>> list = sysGeneratorService.queryList(query);
		int total = sysGeneratorService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(list, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
//	@RequiresPermissions("sys:generator:code")
	@ApiOperation(value="生成代码", notes="根据表名生成相应代码", httpMethod="GET", produces=MediaType.APPLICATION_JSON_VALUE,response=R.class)
	public void code(@ApiParam(value="数据库表名",required=true) @RequestParam(value="tables", required=true)String tables, HttpServletResponse response) throws IOException{
//		String[] tableNames = new String[]{};
//		tableNames = JSON.parseArray(tables).toArray(tableNames);
//		byte[] data = sysGeneratorService.generatorCode(tableNames);
		String[] table = tables.split(",");
		byte[] data = sysGeneratorService.generatorCode(table);
		response.reset();  
        response.setHeader("Content-Disposition", "attachment; filename=\"nsdm.zip\"");  
        response.addHeader("Content-Length", "" + data.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
  
        IOUtils.write(data, response.getOutputStream());  
	}
	@ResponseBody
	@GetMapping("/viewTable")
	@ApiOperation(value="查看表信息", notes="根据表名查看表相应信息", httpMethod="GET", produces=MediaType.APPLICATION_JSON_VALUE,response=R.class)
	public R viewTable(@ApiParam(value="数据库表名",required=true) @RequestParam(value="tableName", required=true) String tableName){
		return R.ok().put("data", sysGeneratorService.queryColumnsDetail(tableName));
	}
}
