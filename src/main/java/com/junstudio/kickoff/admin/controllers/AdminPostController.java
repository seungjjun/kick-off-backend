package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetPostAdminService;
import com.junstudio.kickoff.dtos.PostsByDateDto;
import com.junstudio.kickoff.dtos.StatisticsPostsDto;
import com.junstudio.kickoff.dtos.TodayCreatePostsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminPostController {
    private final GetPostAdminService getPostAdminService;

    public AdminPostController(GetPostAdminService getPostAdminService) {
        this.getPostAdminService = getPostAdminService;
    }

    @GetMapping("/admin-most-hit-posts")
    private StatisticsPostsDto mostViewedPosts() {
        return getPostAdminService.mostViewedPosts();
    }

    @GetMapping("/admin-today-posts")
    private TodayCreatePostsDto todayCreatedPosts() {
        return getPostAdminService.todayCreatedPosts();
    }

    @GetMapping("/admin-week-posts")
    private PostsByDateDto weekPosts() {
        return getPostAdminService.weekPosts();
    }
}
