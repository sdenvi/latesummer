package com.others;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {
	/**
	 * 1：先获得文件句柄 
	 * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 * 3：读取到输入流后，需要读取生成字节流 
	 * 4：一行一行的输出
	 * @param filePath
	 */
	public static void readTxtFile(String filePath) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("统计表");
		createTitle(workbook, sheet);
		// 设置日期格式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		// 新增数据行，并且设置单元格数据
		int rowNum = 1;
		String encoding = "GBK";
		File file = new File(filePath);
		try {
			if (file.isFile() && file.exists()) {
				// 编码格式
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					HSSFRow row = sheet.createRow(rowNum);
					lineTxt = lineTxt.substring(1);
					String[] str = lineTxt.split("\\s+");
					
					for (int i = 0; i < str.length; i++) {
						System.out.println(str[i]);
						row.createCell(i).setCellValue(str[i]);
					}
					//HSSFCell cell = row.createCell(14);
					//cell.setCellValue(new Date());
					//cell.setCellStyle(style);
					rowNum++;
				}
				// 拼装文件路径和文件名
				String fileName = "测试数据统计表.xls";
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
				String dateTime = dateFormat.format(new Date());
				String blobName = dateTime + "/" + UUID.randomUUID().toString().replaceAll("-", "") + "/" + fileName;
				
				//ByteArrayOutputStream out = new ByteArrayOutputStream();
				FileOutputStream outputStream = new FileOutputStream("e://" + fileName);
				workbook.write(outputStream);
				outputStream.flush();
			    outputStream.close();
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}
	
	/**
	 * Remove a row by its index
	 * 
	 * @param sheet a Excel sheet
	 * @param rowIndex a 0 based index of removing row
	 */
	public static void removeRow(HSSFSheet sheet, int rowIndex) {
		int lastRowNum = sheet.getLastRowNum();
		if (rowIndex >= 0 && rowIndex < lastRowNum)
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);// 将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
		if (rowIndex == lastRowNum) {
			HSSFRow removingRow = sheet.getRow(rowIndex);
			if (removingRow != null)
				sheet.removeRow(removingRow);
		}
	}
	
	
	/**
	 * 创建表头
	 * @param workbook
	 * @param sheet
	 */
	private static void createTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
		HSSFRow row = sheet.createRow(0);
		// 设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
		sheet.setColumnWidth(2, 12 * 256);
		sheet.setColumnWidth(3, 17 * 256);

		// 设置为居中加粗
		HSSFCellStyle style = workbook.createCellStyle();
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFont(font);

		HSSFCell cell;
		cell = row.createCell(0);
		cell.setCellValue("S");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("Wave1");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("Wave2");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("Wavcen");
		cell.setCellStyle(style);
		
		cell = row.createCell(4);
		cell.setCellValue("IndexRe");
		cell.setCellStyle(style);
		
		cell = row.createCell(5);
		cell.setCellValue("IndexIm");
		cell.setCellStyle(style);
		
		cell = row.createCell(6);
		cell.setCellValue("SolarWt");
		cell.setCellStyle(style);
		
		cell = row.createCell(7);
		cell.setCellValue("ParLength");
		cell.setCellStyle(style);
		
		cell = row.createCell(8);
		cell.setCellValue("ParDarea");
		cell.setCellStyle(style);
		
		cell = row.createCell(9);
		cell.setCellValue("ParDvol");
		cell.setCellStyle(style);
		
		cell = row.createCell(10);
		cell.setCellValue("Qext");
		cell.setCellStyle(style);
		
		cell = row.createCell(11);
		cell.setCellValue("SSalb");
		cell.setCellStyle(style);

		cell = row.createCell(12);
		cell.setCellValue("Asym");
		cell.setCellStyle(style);

		cell = row.createCell(13);
		cell.setCellValue("Phasefunc(log, in 3 digit base 95)");
		cell.setCellStyle(style);
	}
      
	public static void main(String argv[]) {
		String filePath = "D:\\eclipse-workspace\\sw_ice_scatter.db";
		readTxtFile(filePath);
	}

}