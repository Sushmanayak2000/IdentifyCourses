package com.cognizant.hackathon.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFunctionality {

	// Method to get data from Excel File
	public static String getInput(int row, int col) throws IOException {

		File file = new File(System.getProperty("user.dir") + "\\data-repository\\input_data.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row2 = sheet.getRow(row);
		XSSFCell cell = row2.getCell(col);
		String inputData = (String) cell.getStringCellValue();
		return inputData;
	}

	// Method to store data to a Excel File
	public static void outputExcel(String sheetName, String fileName, List<String> items) throws IOException {
		FileOutputStream fi = new FileOutputStream(
				System.getProperty("user.dir") + "\\data-repository\\" + fileName + ".xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet(sheetName);
		for (int i = 0; i < items.size(); i++) {
			XSSFRow row = sheet.createRow(i);
			XSSFCell cell = row.createCell(0);
			String value = items.get(i);
			cell.setCellValue(value);
		}
		wb.write(fi);
	}
}
