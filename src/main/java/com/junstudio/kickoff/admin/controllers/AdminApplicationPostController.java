package com.junstudio.kickoff.admin.controllers;

import com.junstudio.kickoff.admin.services.DeleteApplicationPostAdminService;
import com.junstudio.kickoff.dtos.ApplicationFormDto;
import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.admin.services.GetApplicationPostAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AdminApplicationPostController {
    private final GetApplicationPostAdminService getApplicationPostAdminService;
    private final DeleteApplicationPostAdminService deleteApplicationPostAdminService;

    public AdminApplicationPostController(GetApplicationPostAdminService getApplicationPostAdminService,
                                          DeleteApplicationPostAdminService deleteApplicationPostAdminService) {
        this.getApplicationPostAdminService = getApplicationPostAdminService;
        this.deleteApplicationPostAdminService = deleteApplicationPostAdminService;
    }

    @GetMapping("/admin-posts")
    private ApplicationPostsDto applicationPosts() {
        return getApplicationPostAdminService.applicationPosts();
    }

    @GetMapping("/admin-processing-posts")
    private int processingPosts() {
        return getApplicationPostAdminService.processingPosts();
    }

    @DeleteMapping("/admin-posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deletePost(
        @PathVariable Long postId
        ) {
        deleteApplicationPostAdminService.delete(postId);
    }
}
