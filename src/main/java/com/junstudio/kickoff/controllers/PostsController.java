package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.dtos.PostWriteDto;
import com.junstudio.kickoff.dtos.PostWrittenDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.services.PostService;
import com.junstudio.kickoff.utils.S3Uploader;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostsController {
  private final PostService postService;
  private final S3Uploader s3Uploader;

  public PostsController(PostService postService, S3Uploader s3Uploader) {
    this.postService = postService;
    this.s3Uploader = s3Uploader;
  }

  @GetMapping("/posts")
  public PostsDto posts() {
    List<PostDto> posts = postService.posts()
        .stream().map(Post::toDto)
        .collect(Collectors.toList());
    return new PostsDto(posts);
  }

  @GetMapping("/post/{id}")
  public PostDetailDto post(
      @PathVariable Long id
  ) {
    Post post = postService.findPost(id);

    return post.toDetailDto();
  }

  @PostMapping("/post")
  @ResponseStatus(HttpStatus.CREATED)
  public PostWrittenDto write(
      @Valid @RequestBody PostWriteDto postWriteDto) throws IOException {
    System.out.println("*".repeat(20 ) + postWriteDto);

    Post post = postService.write(
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
