package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.dtos.WriteDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostsController {
  private final PostService postService;

  public PostsController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/posts")
  public PostsDto posts() {
    List<PostDto> posts = postService.posts()
        .stream().map(Post::toDto)
        .collect(Collectors.toList());
    return new PostsDto(posts);
  }

  @PostMapping("/post")
  @ResponseStatus(HttpStatus.CREATED)
  public String write(
      @Valid @RequestBody WriteDto writeDto
  ) {
    postService.write(
        writeDto.getTitle(),
        writeDto.getContent(),
        writeDto.getCategory());
    return "created";
  }
}
