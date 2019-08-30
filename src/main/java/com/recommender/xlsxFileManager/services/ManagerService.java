package com.recommender.xlsxFileManager.services;

import com.recommender.xlsxFileManager.services.auxuliaryServices.FolderManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ManagerService {


    public String uploadFile(MultipartFile xlxsFile, String languageCode) {

        FolderManager
        //TODO check if directory for language code exists, create if not (with jsons folder)
        //TODO store the file in the directory root
        //TODO get the File from MultipartFile, create Workbook, get first sheet
        //TODO create JSON with language code
        //TODO store them in the appropiate folder

        return xlxsFile.getOriginalFilename() + " " + languageCode;
    }

    public String getAll() {
        return null;
    }

    public String getLanguage(String languageCode) {
        return null;
    }
}
