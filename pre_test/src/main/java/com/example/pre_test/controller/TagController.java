package com.example.pre_test.controller;

import com.example.pre_test.model.Tag;
import com.example.pre_test.repository.TagRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin("*")
public class TagController {

    private final TagRepository tagRepository;

    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }
}