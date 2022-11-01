package com.junstudio.kickoff.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.junstudio.kickoff.dtos.PostDetailDto;
import com.junstudio.kickoff.dtos.PostDto;
import com.junstudio.kickoff.dtos.PostWrittenDto;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
  @GeneratedValue
  @Id
  @Column(name = "post_id")
  private Long id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @JsonManagedReference
  @OneToMany(mappedBy = "post")
  private List<Comment> comments = new ArrayList<>();

  @JsonManagedReference
  @OneToMany(mappedBy = "post")
  private List<Like> likes = new ArrayList<>();

  private String title;

  private String content;

  private Long hit;

  @Column(name = "imageUrl", length = 2048)
  private String imageUrl;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public Post() {
  }

  public Post(Long id, User user, Category category,
              List<Comment> comments, List<Like> likes, String title,
              String content, Long hit, String imageUrl, LocalDateTime createdAt) {
    this.id = id;
    this.user = user;
    this.category = category;
    this.comments = comments;
    this.likes = likes;
    this.title = title;
    this.content = content;
    this.hit = hit;
    this.imageUrl = imageUrl;
    this.createdAt = createdAt;
  }

  public Post(String title, String content, Long hit,
              String imageUrl, User user, Category category) {
    this.title = title;
    this.content = content;
    this.hit = hit;
    this.imageUrl = imageUrl;
    this.user = user;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Category getCategory() {
    return category;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public List<Like> getLikes() {
    return likes;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public Long getHit() {
    return hit;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public PostDto toDto() {
    return new PostDto(id, title, comments, category, user, hit, likes,
        createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), imageUrl);
  }

  public PostDetailDto toDetailDto() {
    return new PostDetailDto(id, title, content, category, user, hit, comments, likes,
        createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), imageUrl);
  }

  public void updateHit(Long hit) {
    this.hit = hit + 1L;
  }

  public PostWrittenDto postWrittenDto() {
    return new PostWrittenDto(id);
  }
}
