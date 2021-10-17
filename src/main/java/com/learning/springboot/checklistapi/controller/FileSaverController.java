package com.learning.springboot.checklistapi.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/v1/api/save-documents")
public class FileSaverController {

    @GetMapping
    public @ResponseBody byte[] exportToPdf() throws IOException {
        InputStream inputStream = new ClassPathResource("sample-pdf.pdf").getInputStream();
        return FileCopyUtils.copyToByteArray(inputStream);
    }
}
