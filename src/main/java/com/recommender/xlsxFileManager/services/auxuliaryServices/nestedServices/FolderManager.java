package com.recommender.xlsxFileManager.services.auxuliaryServices.nestedServices;

import java.io.File;
import java.util.Objects;

public class FolderManager {

    public final String rootFolderPath = "src/main/resources/recommender-results";

    public String setLanguageCodeFolder(String languageCode) {
        File rootFolder = openFile(rootFolderPath);

        for (File file : Objects.requireNonNull(rootFolder.listFiles())) {
            if (file.getName().equals(languageCode)) {
                purgeDirectory(languageCode);
                return file.getPath();
            }
        }

        File newFolder = new File(rootFolderPath+ "/" + languageCode);
        newFolder.mkdir();
        File JSONFolder = new File(rootFolderPath + "/" + languageCode + "/JSON");
        JSONFolder.mkdir();
        if (newFolder.exists()) {
            System.out.println("Folder created for language code " + languageCode + ".");
        }
        return newFolder.getPath();

    }

    public File openFile(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    public void purgeDirectory(File languageCodeFolder) {
        for (File file: Objects.requireNonNull(languageCodeFolder.listFiles())) {
            if (file.isDirectory())
                purgeDirectory(file);
            file.delete();
        }
    }

    public void purgeDirectory(String languageCode) {
        File rootFolder = openFile(rootFolderPath + "/" + languageCode);
        purgeDirectory(rootFolder);
    }

}
