package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.DeleteApplicationPostAdminService;
import com.junstudio.kickoff.dtos.ApplicationFormDto;
import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.admin.services.GetApplicationPostService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminApplicationPostController {
    private final GetApplicationPostService getApplicationPostService;
    private final DeleteApplicationPostAdminService deleteApplicationPostAdminService;

    public AdminApplicationPostController(GetApplicationPostService getApplicationPostService,
                                          DeleteApplicationPostAdminService deleteApplicationPostAdminService) {
        this.getApplicationPostService = getApplicationPostService;
        this.deleteApplicationPostAdminService = deleteApplicationPostAdminService;
    }

    @GetMapping("/posts")
    private ApplicationPostsDto applicationPosts() {
        return getApplicationPostService.applicationPosts();
    }

    @DeleteMapping("/post")
    private void deletePost(
        @RequestBody ApplicationFormDto applicationFormDto
        ) {
        deleteApplicationPostAdminService.delete(applicationFormDto.getApplicationPostId());
    }
}
