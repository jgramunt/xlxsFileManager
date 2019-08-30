package com.recommender.xlsxFileManager.services.auxuliaryServices.nestedServices;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class CellValueExtractor {

    public String getString(Cell cell) {
        if (cell == null) { return ""; }
        String cellValue;
        switch (cell.getCellTypeEnum()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;

            case FORMULA:
                cellValue = getStringFromFormulaCell(cell);
                break;

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue().toString();
                } else {
                    cellValue = Double.toString(cell.getNumericCellValue());
                }
                break;

            case BOOLEAN:
                cellValue = Boolean.toString(cell.getBooleanCellValue());
                break;

            default:
                cellValue = "";
                break;
        }

        return cellValue;
    }

    private String getStringFromFormulaCell(Cell cell) {
        String cellValue;

        switch (cell.getCachedFormulaResultTypeEnum()) {
            case STRING:
                cellValue = cell.getStringCellValue();
                break;

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    cellValue = cell.getDateCellValue().toString();
                } else {
                    cellValue = Double.toString(cell.getNumericCellValue());
                }
                break;


            case BOOLEAN:
                cellValue = Boolean.toString(cell.getBooleanCellValue());
                break;

            default:
                cellValue = "";
                break;

        }

        return cellValue;
    }

}
