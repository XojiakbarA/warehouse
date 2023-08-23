package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.service.AttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    @Override
    public void setAttributes(MultipartFile file, Attachment attachment) {
        if (file.getOriginalFilename() != null) {
            attachment.setName(file.getOriginalFilename());
        }
        if (file.getContentType() != null) {
            attachment.setContentType(file.getContentType());
        }
        if (file.getSize() != 0) {
            attachment.setSize(file.getSize());
        }
    }
}
