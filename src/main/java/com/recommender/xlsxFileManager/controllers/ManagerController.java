package com.recommender.xlsxFileManager.controllers;

import com.recommender.xlsxFileManager.services.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get-all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getAll() {
        return managerService.getAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/get-language/{language-code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getLanguage(@PathVariable String languageCode) {
        return managerService.getLanguage(languageCode);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(value = "/uploadFile")
    public String uploadXlxsFile(@RequestParam("file") MultipartFile xlsxFile, @RequestParam String languageCode) throws IOException {
        return managerService.uploadFile(xlsxFile, languageCode);
    }
}
