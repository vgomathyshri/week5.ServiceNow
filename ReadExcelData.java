package week5.day2.servicenow;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class ReadExcelData {

	public static String[][] readSheetData(String excelName, String sheetName) throws IOException {
		
		// Step 1: Go to the workbook
		XSSFWorkbook workbook = new XSSFWorkbook("Data/"+excelName+".xlsx");
		
		// Step 2: Go to the worksheet
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		// You need to find how many rows --> 
		int rowcount = sheet.getLastRowNum();// this will remove the header
//		sheet.getPhysicalNumberOfRows();// this will not remove the header
		
		// Column Count 
		short columnCount = sheet.getRow(0).getLastCellNum();
		
		String[][] data = new String[rowcount][columnCount];
		
		for (int i = 1; i <= rowcount; i++) {
			
			// Step 3: Go to the row
			XSSFRow dataRow = sheet.getRow(i); // first data row
			// Step 4: Go to the column (cell)
			
			for (int j = 0; j < columnCount; j++) {
				
				XSSFCell coloumnValue = dataRow.getCell(j);
				// Step 5: Get the content
				String dataValue = coloumnValue.getStringCellValue();
				System.out.println(dataValue);
				data[i-1][j] = dataValue;
			}
			
		}
		
		// Close the workbook
		workbook.close();
		
		// return back
		return data;

	}

}
