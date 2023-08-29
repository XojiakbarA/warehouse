package com.example.warehouse.repository;

import com.example.warehouse.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, Long> {
    Optional<AttachmentContent> findByAttachmentId(Long attachmentId);
    void deleteByAttachmentId(Long attachmentId);
}
