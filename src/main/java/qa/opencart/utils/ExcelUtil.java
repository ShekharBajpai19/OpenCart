package qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static final String DATA_SHEET_PATH = "./src/test/resources/testdata/OpenCartTestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName)
	{
		System.out.println("Reading data from :: "+sheetName); 
		
		Object data [][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(DATA_SHEET_PATH);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			int rows = sheet.getLastRowNum();
			int cols = sheet.getRow(0).getLastCellNum();
			
			data = new Object[rows][cols];
			
			for (int i=0;i<rows;i++) {
				for (int j=0;j<cols;j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return data;
	}
	
}
