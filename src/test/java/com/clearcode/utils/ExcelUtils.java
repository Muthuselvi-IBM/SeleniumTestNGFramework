package com.clearcode.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static Object[][] getData(String book, String sheet) {
		File f = new File("src/test/resources/data/"+book);
		Workbook wb=null;
		if(f.getName().endsWith("xls"))
		{
			try {
				wb = new HSSFWorkbook(new FileInputStream(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			try {
				wb = new XSSFWorkbook(new FileInputStream(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Sheet sh = wb.getSheet(sheet);
		int rowCount = sh.getPhysicalNumberOfRows();
		Row row1 = sh.getRow(0);
		int cellCount = row1.getPhysicalNumberOfCells();
		Object[][] data = new Object[rowCount-1][cellCount];
		Object d;
		for(int r = 1;r<rowCount;r++)
		{
			Row row = sh.getRow(r);
			for(int c = 0;c<cellCount;c++)
			{
				Cell cell = row.getCell(c);
				try
				{
				cell.setCellType(CellType.STRING);
				d = cell.getStringCellValue();
				}
				catch(NullPointerException e)
				{
					d = "";
				}
				data[r-1][c] = d; 
			}
		}
		/*{{1,2,3}
		 * {4,5,6}
		 * 
		 */
		//for(datatypeofcollection obj:collection obj)
//		for(Object[] x:data)
//		{
//			for(Object y:x)
//				System.out.print(y+"\t\t");
//			System.out.println("");
//		}
		return data;
	}
	
	public static Object[][] getData(String book, String sheet, int rowCount) {
		File f = new File("src/test/resources/data/"+book);
		Workbook wb=null;
		if(f.getName().endsWith("xls") || f.getName().endsWith("xlsx"))
		{
			try {
				wb = new HSSFWorkbook(new FileInputStream(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			try {
				wb = new XSSFWorkbook(new FileInputStream(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Sheet sh = wb.getSheet(sheet);
		Row row1 = sh.getRow(0);
		int cellCount = row1.getPhysicalNumberOfCells();
		Object[][] data = new Object[rowCount-1][cellCount];
		Object d;
		for(int r = 1;r<rowCount;r++)
		{
			Row row = sh.getRow(r);
			for(int c = 0;c<cellCount;c++)
			{
				Cell cell = row.getCell(c);
				try
				{
				cell.setCellType(CellType.STRING);
				d = cell.getStringCellValue();
				}
				catch(NullPointerException e)
				{
					d = "";
				}
				data[r-1][c] = d; 
			}
		}
		/*{{1,2,3}
		 * {4,5,6}
		 * 
		 */
		//for(datatypeofcollection obj:collection obj)
//		for(Object[] x:data)
//		{
//			for(Object y:x)
//				System.out.print(y+"\t\t");
//			System.out.println("");
//		}
		data.equals(data);
		return data;
	}
	
	public static Object[] getDataTestCaseData(String book, String sheet, String caseName) {
		File f = new File("src/test/resources/data/"+book);
		Workbook wb=null;
		if(f.getName().endsWith("xls"))
		{
			try {
				wb = new HSSFWorkbook(new FileInputStream(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			try {
				wb = new XSSFWorkbook(new FileInputStream(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Sheet sh = wb.getSheet(sheet);
		int rowCount = sh.getPhysicalNumberOfRows();
		Row row1 = sh.getRow(0);
		int cellCount = row1.getPhysicalNumberOfCells();
		Object[][] data = new Object[rowCount-1][cellCount];
		Object d;
		for(int r = 1;r<rowCount;r++)
		{
			Row row = sh.getRow(r);
			for(int c = 0;c<cellCount;c++)
			{
				Cell cell = row.getCell(c);
				try
				{
				cell.setCellType(CellType.STRING);
				d = cell.getStringCellValue();
				}
				catch(NullPointerException e)
				{
					d = "";
				}
				data[r-1][c] = d; 
			}
		}
		int r=0;
		for(;r<rowCount;r++)
		{
			if(data[r][0].toString().equals(caseName))
			{
				break;
			}
		}
		return data[r];
		/*{{1,2,3}
		 * {4,5,6}
		 * 
		 */
		//for(datatypeofcollection obj:collection obj)
//		for(Object[] x:data)
//		{
//			for(Object y:x)
//				System.out.print(y+"\t\t");
//			System.out.println("");
//		}
		
	}
	
	public static HashMap<String,List<String>> readDataToMap(String book, String sheet){
		return null;
	}
	
	public static List<String> data(String book, String sheet, String testName){
		File f = new File("src/test/resources/data/"+book);
		Workbook wb=null;
		if(f.getName().endsWith("xls"))
		{
			try {
				wb = new HSSFWorkbook(new FileInputStream(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else 
		{
			try {
				wb = new XSSFWorkbook(new FileInputStream(f));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Sheet sh = wb.getSheet(sheet);
		int rowCount = sh.getPhysicalNumberOfRows();
		int i=1;
		for(;i<rowCount;i++)
		{
			Cell cell = sh.getRow(i).getCell(0);
			cell.setCellType(CellType.STRING);
			if(cell.getStringCellValue().equals(testName))
			{
				break;
			}
		}
		List<String> data = new ArrayList<String>();
		Row row = sh.getRow(i);
		for(int k=1;k<row.getPhysicalNumberOfCells();k++)
		{
			row.getCell(k).setCellType(CellType.STRING);
			data.add(row.getCell(k).getStringCellValue());
		}
		return data;
		
	}

}
