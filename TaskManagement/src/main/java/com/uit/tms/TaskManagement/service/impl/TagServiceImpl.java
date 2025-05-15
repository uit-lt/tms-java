package com.uit.tms.TaskManagement.service.impl;

import com.uit.tms.TaskManagement.entity.TagEntity;
import com.uit.tms.TaskManagement.repository.TagRepository;
import com.uit.tms.TaskManagement.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<TagEntity> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public TagEntity getTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tag not found with id: " + id));
    }

    @Override
    public TagEntity createTag(String title) {
        TagEntity tag = TagEntity.builder().title(title).build();
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}

