package com.kj.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.kj.domain.User;
import com.system.util.EndecryptUtils;

public class ExcelInput {
	
    /**
     * Read the Excel 2010
     * @param path the path of the excel file
     * @return
     * @throws IOException
     */
    public static List<User> readXlsx(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        User user = null;
        List<User> list = new ArrayList<User>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                    user = new User();
                    user.setEmployeeID(getValue(xssfRow.getCell(0)));
                    user.setUsername(getValue(xssfRow.getCell(1)));
                    //默认密码为手机号
                    user.setPassword(EndecryptUtils.md5(getValue(xssfRow.getCell(2))));
                    user.setPhone(getValue(xssfRow.getCell(2)));
                    user.setEmail(getValue(xssfRow.getCell(3)));
                    user.setSubsidiary(getValue(xssfRow.getCell(4)));
                    user.setUnit(getValue(xssfRow.getCell(5)));
                    user.setPosition(getValue(xssfRow.getCell(6)));
                    user.setProtitle(getValue(xssfRow.getCell(7)));
                    user.setDegree(getValue(xssfRow.getCell(8)));
                    user.setResearchField(getValue(xssfRow.getCell(9)));
                    list.add(user);
                }
            }
        }
        return list;
    }

    /**
     * Read the Excel 2003-2007
     * @param path the path of the Excel
     * @return
     * @throws IOException
     */
    public static List<User> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        User user = null;
        List<User> list = new ArrayList<User>();
        // Read the Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // Read the Row
            for (int rowNum = 2; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                	user = new User();
                    user.setEmployeeID(getValue(hssfRow.getCell(0)));
                    user.setUsername(getValue(hssfRow.getCell(1)));
                    //默认密码为手机号
                    user.setPassword(EndecryptUtils.md5(getValue(hssfRow.getCell(2))));
                    user.setPhone(getValue(hssfRow.getCell(2)));
                    user.setEmail(getValue(hssfRow.getCell(3)));
                    user.setSubsidiary(getValue(hssfRow.getCell(4)));
                    user.setUnit(getValue(hssfRow.getCell(5)));
                    user.setPosition(getValue(hssfRow.getCell(6)));
                    user.setProtitle(getValue(hssfRow.getCell(7)));
                    user.setDegree(getValue(hssfRow.getCell(8)));
                    user.setResearchField(getValue(hssfRow.getCell(9)));
                    list.add(user);
                }
            }
        }
        return list;
    }

    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell xssfRow) {
    	if(xssfRow==null){
    		return "";
    	}
        if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfRow.getBooleanCellValue());
        } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
            return String.valueOf(xssfRow.getNumericCellValue());
        } else {
            return String.valueOf(xssfRow.getStringCellValue());
        }
    }

    @SuppressWarnings("static-access")
    private static String getValue(HSSFCell hssfCell) {
    	if(hssfCell==null){
    		return "";
    	}
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            return String.valueOf(hssfCell.getNumericCellValue());
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
}
