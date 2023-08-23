package com.example.warehouse.service;

import com.example.warehouse.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    void setAttributes(MultipartFile file, Attachment attachment);
}
