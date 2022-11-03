package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.CategoryDto;
import com.junstudio.kickoff.dtos.CommentDto;
import com.junstudio.kickoff.dtos.LikeDto;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostWriteDto;
import com.junstudio.kickoff.dtos.PostWrittenDto;
import com.junstudio.kickoff.dtos.PostsDto;
import com.junstudio.kickoff.dtos.ReCommentDto;
import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.models.Category;
import com.junstudio.kickoff.models.Comment;
import com.junstudio.kickoff.models.Like;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.Recomment;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.services.CategoryService;
import com.junstudio.kickoff.services.CommentService;
import com.junstudio.kickoff.services.LikeService;
import com.junstudio.kickoff.services.PostService;
import com.junstudio.kickoff.services.RecommentService;
import com.junstudio.kickoff.services.UserService;
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
  private final LikeService likeService;
  private final S3Uploader s3Uploader;
  private final CommentService commentService;
  private final UserService userService;
  private final CategoryService categoryService;
  private final RecommentService recommentService;

  public PostsController(PostService postService, LikeService likeService,
                         S3Uploader s3Uploader, CommentService commentService,
                         UserService userService, CategoryService categoryService,
                         RecommentService recommentService) {
    this.postService = postService;
    this.likeService = likeService;
    this.s3Uploader = s3Uploader;
    this.commentService = commentService;
    this.userService = userService;
    this.categoryService = categoryService;
    this.recommentService = recommentService;
  }

  @GetMapping("/posts")
  public PostsDto posts() {
    List<PostDto> posts = postService.posts()
        .stream().map(Post::toDto)
        .collect(Collectors.toList());

    List<CommentDto> comments = commentService.comments()
        .stream().map(Comment::toDto)
        .collect(Collectors.toList());

    List<ReCommentDto> recomments = recommentService.recomments()
        .stream().map(Recomment::toDto)
        .collect(Collectors.toList());

    List<LikeDto> likes = likeService.likes()
        .stream().map(Like::toDto)
        .collect(Collectors.toList());

    List<UserDto> users = userService.users()
        .stream().map(User::toDto)
        .collect(Collectors.toList());

    List<CategoryDto> categories = categoryService.categories()
        .stream().map(Category::toDto)
        .collect(Collectors.toList());

    return new PostsDto(posts, comments, recomments, likes, users, categories);
  }

  @GetMapping("/posts/{id}")
  public PostDetailDto post(
      @PathVariable Long id
  ) {
    Post post = postService.findPost(id);
    User user = postService.findUser(id);
    Category category = postService.findCategory(id);
    List<Like> likes = likeService.findLike(post.id());

    return post.toDetailDto(user, category, likes);
  }

  @PostMapping("/post")
  @ResponseStatus(HttpStatus.CREATED)
  public PostWrittenDto write(
      @Valid @RequestBody PostWriteDto postWriteDto) throws IOException {

    Post post = postService.write(
        postWriteDto.getTitle(),
        postWriteDto.getContent(),
        postWriteDto.getImageUrl(),
        postWriteDto.getUserId(),
        postWriteDto.getCategoryId());

    return post.postWrittenDto();
  }

  @PostMapping("/like")
  public String like(
      @RequestBody LikeDto likeDto
  ) {
    likeService.countLike(likeDto.getPostId(), likeDto.getUserId());
    return "ok";
  }

  @PostMapping("/upload")
  public String upload(MultipartFile multipartFile) throws IOException {
    return s3Uploader.uploadFiles(multipartFile, "kickoffproject");
  }
}
