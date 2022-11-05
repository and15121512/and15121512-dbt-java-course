package ru.sber.javacourse.report.impl;

import ru.sber.javacourse.report.Report;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportImpl implements Report {
    XSSFWorkbook workbook;
    ArrayList<ArrayList<String>> reportRepr;

    public ArrayList<ArrayList<String>> getReportData() {
        return reportRepr;
    }

    public ReportImpl(ArrayList<ArrayList<String>> reportRepr, String sheetName) {
        this.reportRepr = reportRepr;
        workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        fillSheet(sheet, reportRepr);
    }

    private void fillSheet(XSSFSheet sheet, ArrayList<ArrayList<String>> sheetRepr) {
        int rowNum = 0;
        for (var rowRepr : sheetRepr) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (String cellRepr : rowRepr) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(cellRepr);
            }
        }
    }

    @Override
    public byte[] asBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        return bos.toByteArray();
    }

    @Override
    public void writeTo(OutputStream os) throws IOException {
        workbook.write(os);
        //workbook.close();
    }
}
