package com.recommender.xlsxFileManager.services.auxuliaryServices.nestedServices;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class RowGrouper {

    private CellValueExtractor cellValueExtractor;

    public RowGrouper() {
        cellValueExtractor = new CellValueExtractor();
    }

    public List<List<Row>> getListOfRowGroups(Sheet sheet) {
        List<List<Row>> listOfRowGroups = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == sheet.getFirstRowNum()) {
                continue;
            }

            boolean isRowClassified = false;

            for (List<Row> groupOfRows : listOfRowGroups) {
                if (groupOfRows.isEmpty()) {
                    break;
                }

                Row firstRow = groupOfRows.get(0);

                if (cellValueExtractor.getString(row.getCell(0)).equals(cellValueExtractor.getString(firstRow.getCell(0)))
                        && cellValueExtractor.getString(row.getCell(1)).equals(cellValueExtractor.getString(firstRow.getCell(1)))
                        && cellValueExtractor.getString(row.getCell(2)).equals(cellValueExtractor.getString(firstRow.getCell(2)))) {
                    groupOfRows.add(row);
                    isRowClassified = true;
                    break;
                }
            }

            if (!isRowClassified) {
                List<Row> newRowGroup = new ArrayList<>();
                newRowGroup.add(row);
                listOfRowGroups.add(newRowGroup);
            }

        }

        return listOfRowGroups;
    }

}
