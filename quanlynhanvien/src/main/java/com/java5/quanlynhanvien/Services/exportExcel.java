package com.java5.quanlynhanvien.Services;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.java5.quanlynhanvien.DAO.DeapartsDAO;
import com.java5.quanlynhanvien.model.Departs;
public class exportExcel {
	DeapartsDAO dao ;
	public static void main(String[] args) {
		
		exportExcel excelWriter = new exportExcel();
		 
		List<Departs> listDeparts = excelWriter.getListDeparts();
		String excelFilePath = "C:/test.xlsx";
		 
		try {
			excelWriter.writeExcel(listDeparts, excelFilePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Workbook getWorkbook(String excelFilePath)
	        throws IOException {
	    Workbook workbook = null;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook();
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook();
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }
	 
	    return workbook;
	}
	
	public void writeExcel(List<Departs> listDeparts, String excelFilePath) throws IOException {
	    Workbook workbook = getWorkbook(excelFilePath);
	    Sheet sheet = workbook.createSheet();
	    createHeaderRow(sheet);
	    int rowCount = 0;
	 
	    for (Departs aBook : listDeparts) {
	        Row row = sheet.createRow(++rowCount);
	        writeBook(aBook, row);
	    }
	 
	    try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)) {
	        workbook.write(outputStream);
	    }
	}
	
	private void writeBook(Departs aBook, Row row) {
	    Cell cell = row.createCell(1);
	    cell.setCellValue(aBook.getId_departs());
	 
	    cell = row.createCell(2);
	    cell.setCellValue(aBook.getName_departs());
	 
	}
	
    //Có thể format được như in đậm, set font
	private void createHeaderRow(Sheet sheet) {
		 
	    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	    Font font = sheet.getWorkbook().createFont();
	   // font.setBold(true);
	    font.setFontHeightInPoints((short) 16);
	    cellStyle.setFont(font);
	 
	    Row row = sheet.createRow(0);
	    Cell cellTitle = row.createCell(1);
	 
	    cellTitle.setCellStyle(cellStyle);
	    cellTitle.setCellValue("Title");
	 
	    Cell cellAuthor = row.createCell(2);
	    cellAuthor.setCellStyle(cellStyle);
	    cellAuthor.setCellValue("Author");
	 
	    Cell cellPrice = row.createCell(3);
	    cellPrice.setCellStyle(cellStyle);
	    cellPrice.setCellValue("Price");
	}
	
	public List<Departs> getListDeparts() {
		
	 
	    return dao.listDeparts() ;
	}

}