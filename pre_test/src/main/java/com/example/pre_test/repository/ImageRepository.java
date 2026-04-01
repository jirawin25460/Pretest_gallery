package com.example.pre_test.repository;

import com.example.pre_test.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {

    Page<Image> findDistinctByTags_NameIn(List<String> tags, Pageable pageable);

    Page<Image> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Image> findDistinctByTags_NameContainingIgnoreCase(String tagName, Pageable pageable);

    Page<Image> findDistinctByNameContainingIgnoreCaseAndTags_NameIn(String name, List<String> tags, Pageable pageable);
}