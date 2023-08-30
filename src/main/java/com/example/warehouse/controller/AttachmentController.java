package com.example.warehouse.controller;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.entity.AttachmentContent;
import com.example.warehouse.service.AttachmentContentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentContentService attachmentContentService;

    @GetMapping("/download/{id}")
    public void download(@PathVariable Long id, HttpServletResponse response) {
        AttachmentContent attachmentContent = attachmentContentService.findByAttachmentId(id);
        Attachment attachment = attachmentContent.getAttachment();
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"");
        response.setContentType(attachment.getContentType());
        try {
            FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
