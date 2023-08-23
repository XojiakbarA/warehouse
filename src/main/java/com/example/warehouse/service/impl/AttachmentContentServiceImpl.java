package com.example.warehouse.service.impl;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.entity.AttachmentContent;
import com.example.warehouse.exception.ResourceNotFoundException;
import com.example.warehouse.repository.AttachmentContentRepository;
import com.example.warehouse.service.AttachmentContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentContentServiceImpl implements AttachmentContentService {

    @Autowired
    private AttachmentContentRepository attachmentContentRepository;

    @Override
    public AttachmentContent findByAttachmentId(Long attachmentId) {
        return attachmentContentRepository.findByAttachmentId(attachmentId).orElseThrow(ResourceNotFoundException::new);
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
    public void setAttributes(Attachment attachment, byte[] bytes, AttachmentContent attachmentContent) {
        if (attachment != null) {
            attachmentContent.setBytes(bytes);
        }
        if (bytes.length != 0) {
            attachmentContent.setAttachment(attachment);
        }
    }
}
