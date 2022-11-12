package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CreatePostsDto;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostWriteDto;
import com.junstudio.kickoff.dtos.PostWrittenDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.services.CreatePostService;
import com.junstudio.kickoff.services.DeletePostService;
import com.junstudio.kickoff.services.GetPostService;
import com.junstudio.kickoff.services.PatchPostService;
import com.junstudio.kickoff.utils.S3Uploader;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@CrossOrigin
public class PostsController {
    private final S3Uploader s3Uploader;
    private final GetPostService getPostService;
    private final CreatePostService createPostService;
    private final PatchPostService patchPostService;
    private final DeletePostService deletePostService;

    public PostsController(S3Uploader s3Uploader,
                           GetPostService getPostService,
                           CreatePostService createPostService,
                           PatchPostService patchPostService,
                           DeletePostService deletePostService) {
        this.s3Uploader = s3Uploader;
        this.getPostService = getPostService;
        this.createPostService = createPostService;
        this.patchPostService = patchPostService;
        this.deletePostService = deletePostService;
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

    @PatchMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(
        @Valid @RequestBody PostWriteDto postWriteDto,
        @PathVariable Long postId
    ) {
        patchPostService.patch(postWriteDto, postId);
    }

    @DeleteMapping("/posts/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
        @PathVariable Long postId
    ) {
        deletePostService.delete(postId);
    }

    @PostMapping("/upload")
    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "kickoffproject");
    }
}
