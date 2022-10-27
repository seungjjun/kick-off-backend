package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.services.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
