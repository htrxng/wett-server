package com.wett.wettserver.common.files.interactors;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MultipleFileUploader {
    private Cloudinary cloudinary;

    public MultipleFileUploader(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public List<String> uploadFiles(MultipartFile[] files) {
        if (files == null) {
            return new ArrayList<>();
        }

        List<String> photos = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Map<String, String> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                photos.add(result.get("secure_url"));
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload photo: " + file.getOriginalFilename());
            }
        }

        return photos;
    }
}
