package com.recommender.xlsxFileManager.services.auxuliaryServices.nestedServices;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XlsxConverter {


    public void convertFileToJsonFiles(File uploadedFile, String languageCode) {
        List<JSONObject> jsonObjectList = createMultipleJSON(getSheet(uploadedFile), languageCode);

        JsonFileCreator jsonFileCreator = new JsonFileCreator();
        jsonFileCreator.createMultipleJSON(jsonObjectList, languageCode);


    }

    private List<JSONObject> createMultipleJSON(Sheet sheet, String languageCode) {
        RowGrouper rowGrouper = new RowGrouper();
        List<List<Row>> groupsOfRows = rowGrouper.getListOfRowGroups(sheet);

        List<JSONObject> groupAsJSONObjectList = new ArrayList<>();

        for (List<Row> rowGroup : groupsOfRows) {
            JSONObject groupAsJsonObject = getRowGroupAsAJSONObject(rowGroup, languageCode);
            groupAsJSONObjectList.add(groupAsJsonObject);
        }

        return groupAsJSONObjectList;
    }

    private Sheet getSheet(File file) {

        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(file);
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
        return workbook.getSheetAt(0);
    }

    private JSONObject getRowGroupAsAJSONObject(List<Row> rowGroup, String languageCode) {
        JSONObject groupAsJsonObject = new JSONObject();
        JSONArray groupAsJsonDataArray = new JSONArray();

        String horizontalAxis = rowGroup.get(0).getCell(0).getStringCellValue();
        String verticalAxis = rowGroup.get(0).getCell(1).getStringCellValue();
        String group = rowGroup.get(0).getCell(2).getStringCellValue();

        for (Row row : rowGroup) {
            JSONObject rowAsJson = createRowJsonObject(row);
            groupAsJsonDataArray.add(rowAsJson);
        }

        Date date = new Date();
        long timestamp = date.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        groupAsJsonObject.put("timestamp", sdf.format(timestamp));
        groupAsJsonObject.put("languageCode", languageCode);
        groupAsJsonObject.put("horizontalAxis", horizontalAxis);
        groupAsJsonObject.put("verticalAxis", verticalAxis);
        groupAsJsonObject.put("group", group);
        groupAsJsonObject.put("data", groupAsJsonDataArray);

        return groupAsJsonObject;
    }

    private JSONObject createRowJsonObject(Row row) {

        Row headersRow = row.getSheet().getRow(row.getSheet().getFirstRowNum());

        CellValueExtractor cellValueExtractor = new CellValueExtractor();

        JSONObject rowJSON = new JSONObject();

        boolean isNextHeader = true;
        int cellNumber = 0;

        while (isNextHeader) {
            Cell headerCell = headersRow.getCell(cellNumber);
            String headerCellContent = cellValueExtractor.getString(headerCell);
            Cell cell = row.getCell(cellNumber);
            String cellContent = cellValueExtractor.getString(cell);

            rowJSON.put(headerCellContent, cellContent);

            Cell nextHeaderCell = headersRow.getCell(cellNumber + 1);
            if (cellValueExtractor.getString(nextHeaderCell).equals("")) {
                isNextHeader = false;
            }
            cellNumber += 1;
        }

        return rowJSON;

    }

}
