package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.ApplicationPostDto;
import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetApplicationPostService {
    private final ApplicationPostRepository applicationPostRepository;

    public GetApplicationPostService(ApplicationPostRepository applicationPostRepository) {
        this.applicationPostRepository = applicationPostRepository;
    }

    public ApplicationPostsDto applicationPosts() {
        List<ApplicationPostDto> applicationPosts = applicationPostRepository.findAll()
            .stream().map(ApplicationPost::toDto).collect(Collectors.toList());

        return new ApplicationPostsDto(applicationPosts);
    }
}
