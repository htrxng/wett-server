package com.wett.wettserver.common.files.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class FileUploadController {
    private final Cloudinary cloudinary;

    @Autowired
    public FileUploadController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(
        @RequestParam("file") MultipartFile file
    ) throws IOException {
        Map<String, String> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
