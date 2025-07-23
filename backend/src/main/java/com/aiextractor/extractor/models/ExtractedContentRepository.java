package com.aiextractor.extractor.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExtractedContentRepository extends JpaRepository<ExtractedContent, Long> {
    // Custom queries if needed
    Page<ExtractedContent> findByUrlContainingIgnoreCaseOrContentContainingIgnoreCase(String url, String content, Pageable pageable);
} 