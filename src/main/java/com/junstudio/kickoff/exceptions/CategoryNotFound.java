package com.junstudio.kickoff.exceptions;

public class CategoryNotFound extends RuntimeException {
    public CategoryNotFound() {
        super("카테고리를 찾지 못했습니다.");
    }
}
