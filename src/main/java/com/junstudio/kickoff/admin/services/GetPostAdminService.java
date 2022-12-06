package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.StatisticsPostDto;
import com.junstudio.kickoff.dtos.StatisticsPostsDto;
import com.junstudio.kickoff.dtos.TodayCreatePostsDto;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Post;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.PostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetPostAdminService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public GetPostAdminService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public StatisticsPostsDto mostViewedPosts() {
        List<StatisticsPostDto> posts = postRepository.findTop3ByOrderByHitDesc()
            .stream().map(Post::toStatisticsDto)
            .collect(Collectors.toList());

        List<User> users = new ArrayList<>();

        posts.forEach(post -> users.add(
            userRepository.findById(post.getUserId().getUserId())
                .orElseThrow(UserNotFound::new)
        ));

        return new StatisticsPostsDto(posts, users);
    }

    public TodayCreatePostsDto todayCreatedPosts() {
        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

        List<Post> posts = postRepository.findByCreatedAtBetween(startDatetime, endDatetime);
        return new TodayCreatePostsDto(posts);
    }
}
