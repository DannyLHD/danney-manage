package com.system.util;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import com.kj.domain.User;

public class ExportExcel{
	
public static boolean exportExcel(String title, String[] headers,List<Object>dataList, OutputStream out, String pattern) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException{
	// 声明一个工作薄
    HSSFWorkbook workbook = new HSSFWorkbook();
    // 生成一个表格
    HSSFSheet sheet = workbook.createSheet(title);
   // 生成一个样式
    HSSFCellStyle style = workbook.createCellStyle();
    // 设置这些样式
    style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    // 生成一个字体
    HSSFFont font = workbook.createFont();
    font.setColor(HSSFColor.VIOLET.index);
    font.setFontHeightInPoints((short) 12);
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    // 把字体应用到当前的样式
    style.setFont(font);
 // 生成并设置另一个样式
    HSSFCellStyle style2 = workbook.createCellStyle();
    style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
    style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
    style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
    style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    // 生成另一个字体
    HSSFFont font2 = workbook.createFont();
    font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
    // 把字体应用到当前的样式
    style2.setFont(font2);
 // 声明一个画图的顶级管理器
    HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
    // 定义注释的大小和位置,详见文档
    HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
    // 设置注释内容
    comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
    // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
    comment.setAuthor("leno");
  //产生表格标题行
    HSSFRow row = sheet.createRow(0);
    for (int i = 0; i < headers.length; i++) {
       HSSFCell cell = row.createCell(i);
       cell.setCellStyle(style);
       HSSFRichTextString text = new HSSFRichTextString(headers[i]);
       cell.setCellValue(text);
    }
    //遍历集合数据，产生数据行
    int index=0;
    for(int i=0;i<dataList.size();i++)
    {
    	index++;
    	//创建行
    	HSSFRow dataRow=sheet.createRow(index);
    	Object obj=dataList.get(i);
    	//获得属性
    	 Field[] fields = obj.getClass().getDeclaredFields();
    	 for(int j=0;j<fields.length;j++)
    	 {
    		 HSSFCell cell = dataRow.createCell(j);
    		 
    		 //获得属性名
    		 String fieldName=fields[j].getName();
    		 String getMethodName = "get"
                   + fieldName.substring(0, 1).toUpperCase()
                   + fieldName.substring(1);
    		 if(getMethodName.equals("getId")){
    			 continue;
    		 }
    		 cell.setCellStyle(style2);
    		 //获得属性对应值
    		 Class clazz = obj.getClass();
            Method getMethod = clazz.getMethod(getMethodName, new Class[] {});
           Object value = getMethod.invoke(obj, new Object[] {});
           String textValue=null;
           if (value instanceof Boolean) {
               boolean bValue = (Boolean) value;
               textValue = "是";
               if (!bValue) {
               textValue ="否";
               }
           }
           else if (value instanceof Date) {
               Date date = (Date) value;
               SimpleDateFormat sdf = new SimpleDateFormat(pattern);
               textValue = sdf.format(date);
           }
           else if (value instanceof User) {
               textValue =((User)value).getUsername();
           }
           else{
            	textValue=value+"";   
           }
    		 cell.setCellValue(textValue);
    	 }
   }
    	 workbook.write(out);
    	 return true;
    	 
  }  
//              //判断值的类型后进行强制类型转换
//              String textValue = null;
//              if (value instanceof Boolean) {
//                  boolean bValue = (Boolean) value;
//                  textValue = "男";
//                  if (!bValue) {
//                     textValue ="女";
//                  }
//               } else if (value instanceof Date) {
//                  Date date = (Date) value;
//                  SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//                   textValue = sdf.format(date);
//               }  else if (value instanceof byte[]) {
//                  // 有图片时，设置行高为60px;
//                  row.setHeightInPoints(60);
//                  // 设置图片所在列宽度为80px,注意这里单位的一个换算
//                  sheet.setColumnWidth(i, (short) (35.7 * 80));
//                  // sheet.autoSizeColumn(i);
//                  byte[] bsValue = (byte[]) value;
//                  HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
//                        1023, 255, (short) 6, index, (short) 6, index);
//                  anchor.setAnchorType(2);
//                  patriarch.createPicture(anchor, workbook.addPicture(
//                        bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
//               } else{
//                  //其它数据类型都当作字符串简单处理
//                  textValue = value.toString();
//               }
//               //如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
//               if(textValue!=null&&!textValue.isEmpty()){
//                  Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
//                  Matcher matcher = p.matcher(textValue);
//                  if(matcher.matches()){
//                     //是数字当作double处理
//                     cell.setCellValue(Double.parseDouble(textValue));
//                  }else{
//                     HSSFRichTextString richString = new HSSFRichTextString(textValue);
//                     HSSFFont font3 = workbook.createFont();
//                     font3.setColor(HSSFColor.BLUE.index);
//                     richString.applyFont(font3);
//                     cell.setCellValue(richString);
//                  }
//               }
//        }
//    }
    
/*public static void main(String args[]) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException
{
	 String[] headers = { "学号", "姓名", "年龄", "性别", "出生日期" };
     String title="学生信息表";
     OutputStream out = new FileOutputStream("F://b.xls");
     String date=new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
     List<Object> studentList = new ArrayList<Object>();
     studentList.add(new Student(10000001, "张三", 20, true, new Date()));
     studentList.add(new Student(20000002, "李四", 24, false, new Date()));
     studentList.add(new Student(30000003, "王五", 22, true, new Date()));
     exportExcel(title, headers, studentList, out,date);
}*/
}

