package utilities;


	
	
	
	
	
	import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
	import java.io.IOException;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	public class XLUtils {
		
		public static FileInputStream fi;
		public static FileOutputStream fo;
		public static XSSFWorkbook wb;
		public static XSSFSheet ws;
		public static XSSFRow row;
		public static XSSFCell cell;

		
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
				wb = new XSSFWorkbook(ExcelFile);
				ws = wb.getSheet(SheetName);
			} catch (Exception e) {
				throw (e);
			}
		}
		
		public static void createExcelFile(String Path, String SheetName)
				throws Exception {
			try {
				// Open the Excel file
				FileOutputStream fo=new FileOutputStream(Path);
				

				// Access the excel data sheet
				wb = new XSSFWorkbook();
				ws = wb.createSheet(SheetName);
			} catch (Exception e) {
				throw (e);
			}
		}

		public static int getRowCount(String xlfile, String xlsheet) throws IOException 
		{
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			int rowcount=ws.getLastRowNum();
			wb.close();
			fi.close();
			return rowcount;		
		}
		
		/*public static int getRowCount(String sheetName){
			
			
			
			int index = wb.getSheetIndex(sheetName);
			if(index==-1)
				return 0;
			else{
		ws = wb.getSheetAt(index);
			int number=ws.getLastRowNum()+1;
			return number;
			}
			
		}*/

		/*// returns number of columns in a sheet	
				public static int getColumnCount(String sheetName){
					// check if sheet exists
					if(!isSheetExist(sheetName))
					 return -1;
					
					ws = wb.getSheet(sheetName);
					row =ws.getRow(0);
					
					if(row==null)
						return -1;
					
					return row.getLastCellNum();
					
					
					
				}*/
			
		
		public static int getCellCount(String xlfile,String xlsheet,int rownum) throws Exception 
		{
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			int cellcount=row.getLastCellNum();
			wb.close();
			fi.close();
			return cellcount;
		}
		
		/*public static String getCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException
		{
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cell=row.getCell(colnum);
			String data;
			try 
			{
				DataFormatter formatter = new DataFormatter();
	            String cellData = formatter.formatCellValue(cell);
	            return cellData;
			}
			catch (Exception e) 
			{
				data="";
			}
			wb.close();
			fi.close();
			return data;
		}
		*/
		
		// returns the data from a cell
		
		
				public String getCellData(String sheetName,String colName,int rowNum){
					try{
						if(rowNum <=0)
							return "";
					
					int index = wb.getSheetIndex(sheetName);
					int col_Num=-1;
					if(index==-1)
						return "";
					
				ws = wb.getSheetAt(index);
					row=ws.getRow(0);
					for(int i=0;i<row.getLastCellNum();i++){
						//System.out.println(row.getCell(i).getStringCellValue().trim());
						if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
							col_Num=i;
					}
					if(col_Num==-1)
						return "";
					
					ws = wb.getSheetAt(index);
					row = ws.getRow(rowNum-1);
					if(row==null)
						return "";
					cell = row.getCell(col_Num);
					
					if(cell==null)
						return "";
					//System.out.println(cell.getCellType());
					if(cell.getCellType()==Cell.CELL_TYPE_STRING)
						  return cell.getStringCellValue();
					else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
						  
						  String cellText  = String.valueOf(cell.getNumericCellValue());
						  if (HSSFDateUtil.isCellDateFormatted(cell)) {
					           // format in form of M/D/YY
							  double d = cell.getNumericCellValue();

							  Calendar cal =Calendar.getInstance();
							  cal.setTime(HSSFDateUtil.getJavaDate(d));
					            cellText =
					             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
					                      cal.get(Calendar.MONTH)+1 + "/" + 
					                      cellText;
					           
					           //System.out.println(cellText);

					         }

						  
						  
						  return cellText;
					  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
					      return ""; 
					  else 
						  return String.valueOf(cell.getBooleanCellValue());
					
					}
					catch(Exception e){
						
						e.printStackTrace();
						return "row "+rowNum+" or column "+colName +" does not exist in xls";
					}
				}
				
						
				
				
			
				

				// returns the data from a cell
				public static String getCellData(String sheetName,int colNum,int rowNum){
					try{
						if(rowNum <=0)
							return "";
					
					int index = wb.getSheetIndex(sheetName);

					if(index==-1)
						return "";
					
				
					ws = wb.getSheetAt(index);
					row = ws.getRow(rowNum-1);
					if(row==null)
						return "";
					cell = row.getCell(colNum);
					if(cell==null)
						return "";
					
				  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
					  return cell.getStringCellValue();
				  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
					  
					  String cellText  = String.valueOf(cell.getNumericCellValue());
					  if (HSSFDateUtil.isCellDateFormatted(cell)) {
				           // format in form of M/D/YY
						  double d = cell.getNumericCellValue();

						  Calendar cal =Calendar.getInstance();
						  cal.setTime(HSSFDateUtil.getJavaDate(d));
				            cellText =
				             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
				           cellText = cal.get(Calendar.MONTH)+1 + "/" +
				                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
				                      cellText;
				           
				          // System.out.println(cellText);

				         }

					  
					  
					  return cellText;
				  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
				      return "";
				  else 
					  return String.valueOf(cell.getBooleanCellValue());
					}
					catch(Exception e){
						
						e.printStackTrace();
						return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
					}
				}

		public static boolean setCellData(String path,String sheetName,String colName,int rowNum, String data){
			try{
			fi = new FileInputStream(path); 
			wb = new XSSFWorkbook(fi);

			if(rowNum<=0)
				return false;
			
			int index = wb.getSheetIndex(sheetName);
			int colNum=-1;
			if(index==-1)
				return false;
			
			
			ws = wb.getSheetAt(index);
			

			row=ws.getRow(0);
			for(int i=0;i<row.getLastCellNum();i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if(row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum=i;
			}
			if(colNum==-1)
				return false;

			ws.autoSizeColumn(colNum); 
			row = ws.getRow(rowNum-1);
			if (row == null)
				row = ws.createRow(rowNum-1);
			
			cell = row.getCell(colNum);	
			if (cell == null)
		        cell = row.createCell(colNum);

		    // cell style
		    CellStyle cs = wb.createCellStyle();
		    cs.setWrapText(true);
		    cell.setCellStyle(cs);
		    cell.setCellValue(data);

		    fo = new FileOutputStream(path);

			wb.write(fo);

		    fo.close();	

			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
			return true;
		}

		
		/*	
		public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
		{
			fi=new FileInputStream(xlfile);
			wb=new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cell=row.createCell(colnum);
			cell.setCellValue(data);
			fo=new FileOutputStream(xlfile);
			wb.write(fo);		
			wb.close();
			fi.close();
			fo.close();
		}
		 */
		// returns true if column is created successfully
				public static boolean addColumn(String path ,String sheetName,String colName){
					//System.out.println("**************addColumn*********************");
					
					try{				
						fi= new FileInputStream(path); 
						wb= new XSSFWorkbook(fi);
						int index = wb.getSheetIndex(ws);
						if(index==-1)
							return false;
						
					XSSFCellStyle style = wb.createCellStyle();
				//style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				
				//	A good explanation about style.setFillForegroundColor( IndexedColors.GREY_25_PERCENT.getIndex() ); style.setFillPattern( FillPatternType.SOLID_FOREGROUND );		
					
				//https://stackoverflow.com/questions/2803841/setting-foreground-color-for-hssfcellstyle-is-always-coming-out-black	
					style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
				
					//style.setFillForegroundColor(new HSSFColor.BLUE().getIndex());
					// style.setFillBackgroundColor(new HSSFColor.RED().getIndex());
					 style.setFillPattern(FillPatternType.THICK_BACKWARD_DIAG);
				
		
					ws=wb.getSheetAt(index);
					
					row = ws.getRow(0);
					if (row == null)
						row = ws.createRow(0);
					
					/*cell = row.getCell(index);	
					if (cell == null)
					System.out.println(row.getLastCellNum());*/
					if(row.getLastCellNum() == -1)
						cell = row.createCell(0);
					else
						cell = row.createCell(row.getLastCellNum());
				        
				        cell.setCellValue(colName);
				        cell.setCellStyle(style);
				        
				        fo = new FileOutputStream(path);
						wb.write(fo);
					    fo.close();		    

					}catch(Exception e){
						e.printStackTrace();
						return false;
					}
					
					return true;
					
					
				}

			
		 
		 
	
	}


