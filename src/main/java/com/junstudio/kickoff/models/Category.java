package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.CategoryDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    private Long parentId;

    public Category() {
    }

    public Category(Long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    public Long id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public CategoryDto toDto() {
        return new CategoryDto(id, name, parentId);
    }
}
