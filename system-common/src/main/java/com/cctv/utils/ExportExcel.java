package com.cctv.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
/**
 * 导出excel表格
 *
 */
public class ExportExcel {
	 /* 
     * 导出数据 
     * */  
    public ByteArrayOutputStream export(String[] rowName,String title,List<Object[]> dataList){  
    	// 创建工作表    
    	HSSFWorkbook workbook = new HSSFWorkbook();                     // 创建工作簿对象  
        HSSFSheet sheet = workbook.createSheet(title); 
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try{    
            // 产生表格标题行  
           /* HSSFRow rowm = sheet.createRow(0);  
            HSSFCell cellTiltle = rowm.createCell(0);  */
              
            //sheet样式定义
            HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);//获取列头样式对象  
            HSSFCellStyle style = this.getStyle(workbook);                  //单元格样式对象  
              
           /* sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (this.rowName.length-1)));    
            cellTiltle.setCellStyle(columnTopStyle);  
            cellTiltle.setCellValue(title);  */
              
            // 定义所需列数  
            int columnNum = rowName.length;  
            HSSFRow rowRowName = sheet.createRow(0);                // 在索引2的位置创建行(最顶端的行开始的第二行)  
              
            // 将列头设置到sheet的单元格中  
            for(int n=0;n<columnNum;n++){  
                HSSFCell  cellRowName = rowRowName.createCell(n);               //创建列头对应个数的单元格  
                cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);             //设置列头单元格的数据类型  
                HSSFRichTextString text = new HSSFRichTextString(rowName[n]);  
                cellRowName.setCellValue(text);                                 //设置列头单元格的值  
                cellRowName.setCellStyle(columnTopStyle);                       //设置列头单元格样式  
            }  
              
            //将查询出的数据设置到sheet对应的单元格中  
            for(int i=0;i<dataList.size();i++){  
                  
                Object[] obj = dataList.get(i);//遍历每个对象  
                HSSFRow row = sheet.createRow(i+1);//创建所需的行数     
                for(int j=0; j<obj.length; j++){  
                    HSSFCell  cell = null;   //设置单元格的数据类型  
                    if(j == 0){  
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_NUMERIC);  
                        cell.setCellValue(obj[j].toString());   
                    }else{  
                        cell = row.createCell(j,HSSFCell.CELL_TYPE_STRING);  
                        if(!"".equals(obj[j]) && obj[j] != null){  
                            cell.setCellValue(obj[j].toString());                       //设置单元格的值  
                        }else{
                        	cell.setCellValue("");
                        }
                    }  
                    cell.setCellStyle(style);                                   //设置单元格样式  
                }  
            }  
            //让列宽随着导出的列长自动适应  
            for (int colNum = 0; colNum < columnNum; colNum++) {  
                int columnWidth = sheet.getColumnWidth(colNum) / 256;  
                for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {  
                    HSSFRow currentRow;  
                    //当前行未被使用过  
                    if (sheet.getRow(rowNum) == null) {  
                        currentRow = sheet.createRow(rowNum);  
                    } else {  
                        currentRow = sheet.getRow(rowNum);  
                    }  
                    if (sheet.getRow(rowNum).getCell(colNum) != null) {  
                        HSSFCell currentCell = currentRow.getCell(colNum,HSSFRow.RETURN_BLANK_AS_NULL);  
                        if (currentCell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        	int length = currentCell.getStringCellValue().getBytes().length;
                        	
                            if (columnWidth < length) {  
                                columnWidth = length;  
                            }  
                        }  
                    }  
                }  
                if(colNum == 0){  
                    sheet.setColumnWidth(colNum, (columnWidth-2) * 256);  
                }else{  
                    sheet.setColumnWidth(colNum, (columnWidth+4) * 256);  
                }  
            }  
            
        	workbook.write(baos);
    		baos.flush();
    		baos.close();
        }catch(Exception e){  
            e.printStackTrace();  
        } 
    	
        return baos;
          
    }  
      
    /*  
     * 列头单元格样式 
     */      
    public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {  
          
          // 设置字体  
          HSSFFont font = workbook.createFont();  
          //设置字体大小  
          font.setFontHeightInPoints((short)11);  
          //字体加粗  
          font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
          //设置字体名字   
          font.setFontName("Courier New");  
          //设置样式;   
          HSSFCellStyle style = workbook.createCellStyle();  
          //设置底边框;   
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
          //设置底边框颜色;    
          style.setBottomBorderColor(HSSFColor.BLACK.index);  
          //设置左边框;     
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          //设置左边框颜色;   
          style.setLeftBorderColor(HSSFColor.BLACK.index);  
          //设置右边框;   
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
          //设置右边框颜色;   
          style.setRightBorderColor(HSSFColor.BLACK.index);  
          //设置顶边框;   
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          //设置顶边框颜色;    
          style.setTopBorderColor(HSSFColor.BLACK.index);  
          //在样式用应用设置的字体;    
          style.setFont(font);  
          //设置自动换行;   
          style.setWrapText(false);  
          //设置水平对齐的样式为居中对齐;    
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
          //设置垂直对齐的样式为居中对齐;   
          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
            
          return style;  
            
    }  
      
    /*   
     * 列数据信息单元格样式 
     */    
    public HSSFCellStyle getStyle(HSSFWorkbook workbook) {  
          // 设置字体  
          HSSFFont font = workbook.createFont();  
          //设置字体大小  
          //font.setFontHeightInPoints((short)10);  
          //字体加粗  
          //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
          //设置字体名字   
          font.setFontName("Courier New");  
          //设置样式;   
          HSSFCellStyle style = workbook.createCellStyle();  
          //设置底边框;   
          style.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
          //设置底边框颜色;    
          style.setBottomBorderColor(HSSFColor.BLACK.index);  
          //设置左边框;     
          style.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
          //设置左边框颜色;   
          style.setLeftBorderColor(HSSFColor.BLACK.index);  
          //设置右边框;   
          style.setBorderRight(HSSFCellStyle.BORDER_THIN);  
          //设置右边框颜色;   
          style.setRightBorderColor(HSSFColor.BLACK.index);  
          //设置顶边框;   
          style.setBorderTop(HSSFCellStyle.BORDER_THIN);  
          //设置顶边框颜色;    
          style.setTopBorderColor(HSSFColor.BLACK.index);  
          //在样式用应用设置的字体;    
          style.setFont(font);  
          //设置自动换行;   
          style.setWrapText(false);  
          //设置水平对齐的样式为居中对齐;    
          style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
          //设置垂直对齐的样式为居中对齐;   
          style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
           
          return style;  
      
    } 
    /**
     * 下载Excel
     */
    public void downLoadExcel(HttpServletRequest request,HttpServletResponse response,ByteArrayOutputStream byteArrayOutputStream,String fileName){
        try  
        {  
        	response.setContentType("APPLICATION/OCTET-STREAM");
    		//获取请求消息头
    		 String userAgent= request.getHeader("User-Agent");
    		 if(userAgent.toLowerCase().contains("firefox")){//火狐浏览器
    				fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
    		 }else{//其他浏览器
    			 fileName  = URLEncoder.encode(fileName, "UTF-8");//W3C推出的标准
    			  }
    		//告知客户端以下载的方式
    		response.setHeader("Content-Disposition", "attachment;filename="+fileName+".xls");
            //response.setContentType("APPLICATION/OCTET-STREAM");  
    		response.setContentLength(byteArrayOutputStream.size());    		
    		ServletOutputStream outputstream = response.getOutputStream();	//取得输出流
    		byteArrayOutputStream.writeTo(outputstream);					//写到输出流
    		byteArrayOutputStream.close();									//关闭
    		outputstream.flush();//刷数据
    		outputstream.close();
        }catch (IOException e){  
            e.printStackTrace();  
        }   

    }
    
  /**
   * @Description:拼接总控导出历史
   * @author:Guan XiaoMing
   * @time:2018年7月21日 下午3:31:36
   */
    public List<Object[]> getObjectArrayForESB(String[] rowName,List<Map<String,Object>> dataList){
    	List<Object[]> objectList = new ArrayList<Object[]>();
    	Object[] objs = null;
    	for(int i=0;i < dataList.size();i++){
    		Map<String,Object> m  = dataList.get(i);
    		objs = new Object[rowName.length];	
    		//0.序号
    		objs[0] = i+1;
    		//1.任务Id  taskId
    		objs[1] = m.get("prodTaskID");
    		//2.任务名称 
    		objs[2] = m.get("taskName");
    		//3.开始时间
    		objs[3] = m.get("startTime");
    		//4.结束时间
    		objs[4] = m.get("endTime");
    		//5.组播地址
    		objs[5] = m.get("multicastIP");
    		//6.端口
    		objs[6] = m.get("port");
    		//7.开始状态
			if(m.get("startStatus")!=null) {
				String cellvalue = m.get("startStatus").toString().substring(0,2);
				if(cellvalue == "00"){
					m.put("startStatus", "未执行");
				}else if(cellvalue == "01"){
					m.put("startStatus", "备执行");
				}else if(cellvalue == "10"){
					m.put("startStatus", "主执行");
				}else if(cellvalue == "11"){
					m.put("startStatus", "执行");
				}  
			}
    		objs[7] = m.get("startStatus");
    		//8.结束状态
    		if(m.get("endStatus")!=null) {
				String cellvalue = m.get("endStatus").toString().substring(0,2);
				if(cellvalue == "00"){
					m.put("endStatus", "未执行");
				}else if(cellvalue == "01"){
					m.put("endStatus", "备执行");
				}else if(cellvalue == "10"){
					m.put("endStatus", "主执行");
				}else if(cellvalue == "11"){
					m.put("endStatus", "执行");
				}  
			}
    		objs[8] = m.get("endStatus");
    		//9.创建时间
    		objs[9] = m.get("createTime");
    		//9.结束时间
    		objs[10] = m.get("updateTime");
    		
    		objectList.add(objs);
    		
    		
    	}
    	return objectList;
    }
    /**
     * 拼装数据申请结果数据
     * @return
     */
    public List<Object[]> getObjectArrayForOrder(String[] rowName,List<Map<String,Object>> dataList){
    	List<Object[]> objectList = new ArrayList<Object[]>();
    	Object[] objs = null;
    	
    	for(int i=0;i < dataList.size();i++){
    		Map<String,Object> m  = dataList.get(i);
    		objs = new Object[rowName.length];	
    		//0.序号
    		objs[0] = i+1;
    		//1.申请单编号
    		objs[1] = m.get("applyNum");
    		
    		//2.任务名称 
    		objs[2] = m.get("streamName");
    		//3.节目源
    		objs[3] = m.get("sourceName");
    		//4.节目源交换机
    		objs[4] = m.get("switchName");
    		//5.节目源交换机IP
    		objs[5] = m.get("portName");
    		//6.目的端
    		objs[6] = m.get("destName");
    		//7.目的端交换机
    		objs[7] = m.get("destSwitchName");
    		//8.目的端交换机IP
    		objs[8] = m.get("destPortName");
    		if(m.get("idType")==null||!m.get("idType").equals("4")){
    			//9.主组播地址
        		objs[9] = m.get("mainMulticast") + ":" + m.get("port");
        		//10备组播地址
        		objs[10] = m.get("spareMulticast") + ":" + m.get("port");
    		}else{
    			//9.主组播地址
        		objs[9] = m.get("streamUrl");
        		//10备组播地址
        		objs[10] = m.get("spareStreamUrl");
    		}   	
    		
    		//11.时间
    		objs[11] = m.get("arrangeStart") + "~" + m.get("arrangeEnd");
    		
    		objectList.add(objs);
    		
    		
    	}
    	return objectList;
    }
    /**
     * 拼装数据 cogent设备数据
     * @return
     */
    public List<Object[]> getObjectArrayForLiveUser(String[] rowName,List<Map<String,Object>> dataList){
    	List<Object[]> objectList = new ArrayList<Object[]>();
    	Object[] objs = null;
    	for(int i=0;i < dataList.size();i++){
    		//设备","状态","在线时长(min)","上线次数","电话","创建类型","部门"
    		Map<String,Object> m  = dataList.get(i);
    		objs = new Object[rowName.length];	
    		//0.序号
    		objs[0] = i+1;
    		//1.设备序列号
    		objs[1] = m.get("sn");
    		//2.状态
    		Integer status = Integer.parseInt(m.get("status").toString());
    		if(status == 0){
    			objs[2] =  "离线";
			}else if(status == 1){
				objs[2] =  "待命";
			}else if(status == 2){
				objs[2] = "工作中";
			}else if(status == 3){
				objs[2] = "正在启动";
			}else if(status == 4){
				objs[2] = "正在停止";
			}
    		//3.在线时长(min,主)
    		objs[3] = m.get("onlineTimeMain");
    		//4.上线次数(主)
    		objs[4] = m.get("onlineCountMain");
    		//5.在线时长(min,备)
    		objs[5] = m.get("onlineTimeSpare");
    		//6.上线次数(备)
    		objs[6] = m.get("onlineCountSpare");
    		//7.电话
    		objs[7] = m.get("mobile");
    		//8.创建类型
    		if(m.get("createType")!=null){
    			String type = m.get("createType").toString();
        		if(type.equals("0")){
        			objs[8] = "app";
    			}else if(type.equals("1")){
    				objs[8] = "nsdm";
    			}else if(type.equals("2")){
    				objs[8] = "cogent";
    			}else{
    				objs[8] = "未知来源";
    			}
    		}else{
    			objs[8] = "未知来源";
    		}
    		//9.部门
    		objs[9] = m.get("depName");
    		objectList.add(objs);
    	}
    	return objectList;
    }
    /**
     * @Description:统计信息数据拼接
     * @author:Guan Xiaoming
     */
	public List<Object[]> getObjectArrayForStatics(String[] rowName, List<Map<String,Object>> dataList) {
		List<Object[]> objectList = new ArrayList<Object[]>();
		Object[] objs = null;
		for(int i=0;i < dataList.size();i++){
    		Map<String,Object> staticsMap  = dataList.get(i);
    		objs = new Object[rowName.length];	
    		//0.任务类型
    		objs[0] = staticsMap.get("taskType");
    		//1.任务调度总数
    		objs[1] = staticsMap.get("dispatchTotal");
    		
    		//2.平均每天调度任务数
    		objs[2] = staticsMap.get("avgDispatchDaysCount");
    		//3.调度总时长
    		objs[3] = staticsMap.get("totalDispatchDuration");
    		//4.平均每天调度任务时长
    		objs[4] = staticsMap.get("avgDispatchDurationDay");
    		//5.平均每天每条调度时长
    		objs[5] = staticsMap.get("avgDurationDispatchDay");
    		objectList.add(objs);
    	}
    		
		return objectList;
	}

	public List<Object[]> getListForStatics(String[] rowName, List<Map<String,Object>> dataList) {
		// TODO Auto-generated method stub
		List<Object[]> objectList = new ArrayList<Object[]>();
		Object[] objs = null;
		for(int i=0;i < dataList.size();i++){
    		Map<String,Object> staticsMap  = dataList.get(i);
    		objs = new Object[rowName.length];	
    		//0.序号
    		objs[0] = i+1;
    		//1.任务单号
    		if(staticsMap.get("src_id")==null){
    			objs[1] = staticsMap.get("prod_task_id");
    		}else{
    			objs[1] = staticsMap.get("src_id");
    		}
    		//2.申请单编号
    		objs[2] = staticsMap.get("sub_apply_num");
    		//3.任务名称
    		if(staticsMap.get("stream_name")!=null){
    			objs[3] = staticsMap.get("stream_name");
    		}else{
    			objs[3] = staticsMap.get("task_name");
    		}
    		//4.节目源
    		objs[4] = staticsMap.get("name");
    		//5.目的地
    		objs[5] = staticsMap.get("destination_name").toString().replaceAll("\\<.*?>", "").replaceAll("\\{&nbsp;", "").replaceAll("\\&nbsp;}", "");
    		//6.总控ip/port
    		if(staticsMap.get("multicastIP")!=null){
    			objs[6] = staticsMap.get("multicastIP");
    		}else{
    		//7,8手机背包密码
    			objs[7] = staticsMap.get("sn");
        		objs[8] = staticsMap.get("passPhrase");
    		}
    		//9.RTMP地址/udp地址
    		if(staticsMap.get("stream_url")!=null){
    			objs[9] = staticsMap.get("stream_url");
    		}
    		//10.开始年
    		objs[10] = staticsMap.get("sYtime");
    		//11.开始时
    		objs[11] = staticsMap.get("sHtime");
    		//12.结束年
    		objs[12] = staticsMap.get("eYtime");
    		//13.结束时
    		objs[13] = staticsMap.get("eHtime");
    		//14.单个时长
    		objs[14] = staticsMap.get("times");
    		//15.栏目名称
    		objs[15] = staticsMap.get("channel_name");
    		objectList.add(objs);
    	}
		return objectList;
	}
}
