package utilities;




import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Please change the extension of the file from .txt to .java
// It looks like udemy stopped supporting uploading of .java files, this is why I have to change the  extension of the file

public class  ExcelUtilityUdemy {
	/*
	 * URL to get the binary - http://poi.apache.org/download.html
	 * Binary Name - poi-bin-3.11-beta2-20140822.zip
	 * Extract the binary
	 * Add all the jars from the location you extracted
	 * Also add all the jars from lib, do not add the jar file of log4j
	 * Also add all the jars from ooxml-lib
	 */
	
	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static XSSFCell Cell;
	public static XSSFRow Row;
	public static FileInputStream fi;
	public static FileOutputStream fo;
	/*
	 * Set the File path, open Excel file
	 * @params - Excel Path and Sheet Name
	 */
	public static void setExcelFile(String Path, String SheetName)
			throws Exception {
		try {
			// Open the Excel file
			FileInputStream ExcelFile = new FileInputStream(Path);

			// Access the excel data sheet
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		} catch (Exception e) {
			throw (e);
		}
	}

	public static int getRowCount(String xlfile, String xlsheet) throws IOException 
	
	{
		fi=new FileInputStream(xlfile);
		ExcelWBook=new XSSFWorkbook(fi);
		ExcelWSheet=ExcelWBook.getSheet(xlsheet);
		int rowcount=ExcelWSheet.getLastRowNum();
		/*wb.close();
		fi.close();*/
		return rowcount;		
	}
	
	
	
	
	/*
	 * Read the test data from the Excel cell
	 * @params - Row num and Col num
	 */
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			String cellData = Cell.getStringCellValue();
			return cellData;
		} catch (Exception e) {
			return "";
		}
	}
	
	/*
	 * Read the test data of date type from the Excel cell
	 * @params - Row num and Col num
	 */
	public static String getDateCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		    Date dateValue = Cell.getDateCellValue();
		    String dateStringFormat = df.format(dateValue);
		    
			return dateStringFormat;
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * Write in the Excel cell, String Result
	 * @params - Row num and Col num
	 */
	public static void setCellData(String Result, int RowNum, int ColNum,String path)
			throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			// This should handle null pointer exception if Row does not exist
			if (Row == null) {
				Row = ExcelWSheet.createRow(RowNum);
			}
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			// Open the file to write the results
			FileOutputStream fileOut = new FileOutputStream(path);

			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
	
	/*
	 * Write in the Excel cell, double Result
	 * @params - Row num and Col num
	 */
	public static void setCellData(double Result, int RowNum, int ColNum,String path)
			throws Exception {
		try {
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if (Cell == null) {
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} else {
				Cell.setCellValue(Result);
			}

			// Open the file to write the results
			FileOutputStream fileOut = new FileOutputStream(path);

			ExcelWBook.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}
}