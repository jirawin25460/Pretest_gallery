package com.example.pre_test.service;

import com.example.pre_test.model.Image;
import com.example.pre_test.repository.ImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Page<Image> getImages(List<String> tags, String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            var query = search.trim();

            if (query.startsWith("#")) {
                var tagName = query.substring(1);
                return tagName.isEmpty() ? imageRepository.findAll(pageable)
                        : imageRepository.findDistinctByTags_NameContainingIgnoreCase(tagName, pageable);
            }

            if (tags != null && !tags.isEmpty()) {
                return imageRepository.findDistinctByNameContainingIgnoreCaseAndTags_NameIn(query, tags, pageable);
            }

            return imageRepository.findByNameContainingIgnoreCase(query, pageable);
        }

        if (tags != null && !tags.isEmpty()) {
            return imageRepository.findDistinctByTags_NameIn(tags, pageable);
        }

        return imageRepository.findAll(pageable);
    }
}