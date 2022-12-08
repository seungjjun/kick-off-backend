package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.ApplicationPostDto;
import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetApplicationPostAdminService {
    private final ApplicationPostRepository applicationPostRepository;

    public GetApplicationPostAdminService(ApplicationPostRepository applicationPostRepository) {
        this.applicationPostRepository = applicationPostRepository;
    }

    public ApplicationPostsDto applicationPosts() {
        List<ApplicationPostDto> applicationPosts = applicationPostRepository.findAll()
            .stream().map(ApplicationPost::toDto).collect(Collectors.toList());

        return new ApplicationPostsDto(applicationPosts);
    }

    public int processingPosts() {
        return (int) applicationPostRepository.findAll().stream()
            .filter(applicationPost -> Objects.equals(applicationPost.state(), "processing")).count();
    }
}
