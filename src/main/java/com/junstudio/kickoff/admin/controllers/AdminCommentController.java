package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetCommentAdminService;
import com.junstudio.kickoff.dtos.CommentsByDateDto;
import com.junstudio.kickoff.dtos.TodayWrittenCommentsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCommentController {
    private final GetCommentAdminService getCommentAdminService;

    public AdminCommentController(GetCommentAdminService getCommentAdminService) {
        this.getCommentAdminService = getCommentAdminService;
    }

    @GetMapping("/admin-today-comments")
    private TodayWrittenCommentsDto todayWrittenCommentsDto() {
        return getCommentAdminService.todayWrittenComments();
    }

    @GetMapping("/admin-week-comments")
    private CommentsByDateDto weekComments() {
        return getCommentAdminService.weekComments();
    }
}
