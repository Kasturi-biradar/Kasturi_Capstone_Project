package com.opencart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	private static final String EXCEL_FILE = "src/test/resources/TestData.xlsx";

    /**
     * Reads Excel data from given row number and returns as Map
     * Keys: firstname, lastname, mail, phone, password
     */
    public static Map<String, String> getRegisterData(int rowNum) {
        Map<String, String> data = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(new File(EXCEL_FILE));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Sheet1");
            if (sheet == null) {
                throw new RuntimeException("Sheet1 not found in Excel file!");
            }

            Row header = sheet.getRow(0);
            Row row = sheet.getRow(rowNum);

            if (row == null) {
                throw new RuntimeException("Row " + rowNum + " not found in Excel file!");
            }

            for (int i = 0; i < header.getLastCellNum(); i++) {
                Cell headerCell = header.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                Cell dataCell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                String key = headerCell.getStringCellValue().trim().toLowerCase();
                String value = "";

                switch (dataCell.getCellType()) {
                    case STRING:
                        value = dataCell.getStringCellValue().trim();
                        break;
                    case NUMERIC:
                        // Convert numeric to string (for phone numbers)
                        value = String.valueOf((long) dataCell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        value = String.valueOf(dataCell.getBooleanCellValue());
                        break;
                    case FORMULA:
                        value = dataCell.getCellFormula();
                        break;
                    default:
                        value = "";
                }

                data.put(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read Excel file: " + e.getMessage());
        }

        return data;
    }
}
