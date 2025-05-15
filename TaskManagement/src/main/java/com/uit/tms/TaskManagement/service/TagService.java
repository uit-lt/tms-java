package com.uit.tms.TaskManagement.service;

import com.uit.tms.TaskManagement.entity.TagEntity;

import java.util.List;

public interface TagService {
    List<TagEntity> getAllTags();
    TagEntity getTagById(Long id);
    TagEntity createTag(String title);
    void deleteTag(Long id);
}