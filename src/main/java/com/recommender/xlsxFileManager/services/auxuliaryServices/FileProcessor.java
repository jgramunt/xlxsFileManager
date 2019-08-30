package com.recommender.xlsxFileManager.services.auxuliaryServices;

import com.recommender.xlsxFileManager.services.auxuliaryServices.nestedServices.FolderManager;
import com.recommender.xlsxFileManager.services.auxuliaryServices.nestedServices.XlsxConverter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class FileProcessor {

    private FolderManager folderManager = new FolderManager();

    public String process(MultipartFile xlxsFile, String languageCode) {

        String destinyFolder = folderManager.setLanguageCodeFolder(languageCode);

        String xlxsFilePath = destinyFolder + "/" + xlxsFile.getOriginalFilename();
        transferFile(xlxsFile, xlxsFilePath);

        //TODO get the File from MultipartFile, create Workbook, get first sheet
        File uploadedFile = folderManager.openFile(xlxsFilePath);
        XlsxConverter xlsxConverter = new XlsxConverter();
        xlsxConverter.convertFileToJsonFiles(uploadedFile, languageCode);

        return "File converted successfully.";
    }

    private void transferFile(MultipartFile xlxsFile, String xlxsFileDestinyPath) {
        try {
            xlxsFile.transferTo(Paths.get(xlxsFileDestinyPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
