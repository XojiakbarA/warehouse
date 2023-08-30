package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.entity.AttachmentContent;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.AttachmentContentRepository;
import com.example.warehouse.service.AttachmentContentService;
import com.example.warehouse.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class AttachmentContentServiceImpl implements AttachmentContentService {

    @Autowired
    private AttachmentContentRepository attachmentContentRepository;

    @Override
    public AttachmentContent findByAttachmentId(Long attachmentId) {
        Supplier<ResourceNotFoundException> supplier = () -> {
            String message = Message.createNotFound(AttachmentContent.class.getSimpleName(), attachmentId, "attachment_id");
            return new ResourceNotFoundException(message);
        };
        return attachmentContentRepository.findByAttachmentId(attachmentId).orElseThrow(supplier);
    }

    @Override
    public AttachmentContent save(AttachmentContent attachmentContent) {
        return attachmentContentRepository.save(attachmentContent);
    }

    @Override
    public void deleteById(Long id) {
        attachmentContentRepository.deleteById(id);
    }

    @Override
    public void deleteByAttachmentId(Long id) {
        attachmentContentRepository.deleteByAttachmentId(id);
    }

    @Override
    public void setAttributes(Attachment attachment, byte[] bytes, AttachmentContent attachmentContent) {
        if (attachment != null) {
            attachmentContent.setBytes(bytes);
        }
        if (bytes.length != 0) {
            attachmentContent.setAttachment(attachment);
        }
    }
}
