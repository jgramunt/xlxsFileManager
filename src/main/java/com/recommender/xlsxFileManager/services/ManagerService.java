package com.recommender.xlsxFileManager.services;

import com.recommender.xlsxFileManager.services.auxuliaryServices.FileGetter;
import com.recommender.xlsxFileManager.services.auxuliaryServices.FileProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ManagerService {

    public String uploadFile(MultipartFile xlxsFile, String languageCode) {
        FileProcessor fileProcessor = new FileProcessor();
        return fileProcessor.process(xlxsFile, languageCode);

    }

    public String getAll() {
        return null;
    }

    public String getLanguage(String languageCode) {
        FileGetter fileGetter = new FileGetter();
        return null;
    }
}
