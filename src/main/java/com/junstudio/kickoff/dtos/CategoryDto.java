package com.junstudio.kickoff.dtos;

public class CategoryDto {
    private final Long id;

    private final String name;

    private final Long parentId;

    public CategoryDto(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }
}
