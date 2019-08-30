package com.recommender.xlsxFileManager.services.auxuliaryServices.nestedServices;

import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.util.List;

public class JsonFileCreator {


    public void createMultipleJSON(List<JSONObject> JSONObjectList, String languageCode) {
        FolderManager folderManager = new FolderManager();
        String savingDirectory = folderManager.rootFolderPath + "/" + languageCode + "/JSON/";

        for (JSONObject jsonObject : JSONObjectList) {
            try (FileWriter fileWriter = new FileWriter(savingDirectory + createFileName(jsonObject))) {
                fileWriter.write(jsonObject.toJSONString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    public void createSingleJSON(JSONObject jsonObject, String savingDirectoryAndName) {
        try (FileWriter fileWriter = new FileWriter(savingDirectoryAndName)) {
            fileWriter.write(jsonObject.toJSONString());
            System.out.println("Converted successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    private String createFileName(JSONObject jsonObject) {
        StringBuilder stringBuilder = new StringBuilder();

        String horizontalAxis = getShortCode(jsonObject.get("horizontalAxis"));
        String verticalAxis = getShortCode(jsonObject.get("verticalAxis"));
        String group = String.valueOf(jsonObject.get("group"));

        stringBuilder.append(jsonObject.get("languageCode"));
        stringBuilder.append("_");
        stringBuilder.append("recommender");
        stringBuilder.append("_");
        stringBuilder.append(horizontalAxis);
        stringBuilder.append("_");
        stringBuilder.append(verticalAxis);
        stringBuilder.append("_");
        stringBuilder.append(group);
        stringBuilder.append(".json");
        return stringBuilder.toString();
    }

    private String getShortCode(Object jsonObject) {
        String longCode = String.valueOf(jsonObject);
        switch (longCode) {
            case "STYLE&TECH":
                return "ST";
            case "CONVENIENCE":
                return "C";
            case "HIGH PERFORMANCE":
                return "HP";
            case "VERSATILITY":
                return "V";
            default:
                return "empty";
        }
    }


}
