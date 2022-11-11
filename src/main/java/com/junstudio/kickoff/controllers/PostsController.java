package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CreatePostsDto;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostWriteDto;
import com.junstudio.kickoff.dtos.PostWrittenDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.services.CreatePostService;
import com.junstudio.kickoff.services.GetPostService;
import com.junstudio.kickoff.utils.S3Uploader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class PostsController {
    private final GetPostService getPostService;
    private final S3Uploader s3Uploader;
    private final CreatePostService createPostService;

    public PostsController(GetPostService getPostService,
                           S3Uploader s3Uploader,
                           CreatePostService createPostService) {
        this.getPostService = getPostService;
        this.s3Uploader = s3Uploader;
        this.createPostService = createPostService;
    }

    @GetMapping("/posts")
    public CreatePostsDto posts(
      @PageableDefault(sort = "id", size = 2) Pageable pageable
    ) {
        return getPostService.posts(pageable);
    }

    @GetMapping("/category/{categoryId}")
    public PostsDto categoryPosts(
        @PathVariable Long categoryId,
        @PageableDefault(sort = "id", direction = Sort.Direction.ASC, value = 2) Pageable pageable
    ) {
        return getPostService.findCategoryPosts(categoryId, pageable);
    }

    @GetMapping("/posts/{id}")
    public PostDetailDto post(
        @PathVariable Long id
    ) {
        return getPostService.findPost(id);
    }

    @PostMapping("/post")
    @ResponseStatus(HttpStatus.CREATED)
    public PostWrittenDto write(
        @Valid @RequestBody PostWriteDto postWriteDto) throws IOException {

        Post post = createPostService.write(
            postWriteDto.getTitle(),
            postWriteDto.getContent(),
            postWriteDto.getImageUrl(),
            postWriteDto.getUserId(),
            postWriteDto.getCategoryId());

        return post.postWrittenDto();
    }

    @PostMapping("/upload")
    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "kickoffproject");
    }
}
