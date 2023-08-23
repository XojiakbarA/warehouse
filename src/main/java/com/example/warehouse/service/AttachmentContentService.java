package com.example.warehouse.service;

import com.example.warehouse.entity.Attachment;
import com.example.warehouse.entity.AttachmentContent;

public interface AttachmentContentService {
    AttachmentContent findByAttachmentId(Long attachmentId);
    AttachmentContent save(AttachmentContent attachmentContent);

    void deleteById(Long id);

    void setAttributes(Attachment attachment, byte[] bytes, AttachmentContent attachmentContent);
}
