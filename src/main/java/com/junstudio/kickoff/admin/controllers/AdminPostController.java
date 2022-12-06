package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.GetPostAdminService;
import com.junstudio.kickoff.dtos.StatisticsPostsDto;
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
}
