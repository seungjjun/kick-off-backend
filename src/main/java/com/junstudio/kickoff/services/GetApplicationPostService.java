package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.ApplicationPostDto;
import com.junstudio.kickoff.dtos.ApplicationPostsDto;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetApplicationPostService {
    private final ApplicationPostRepository applicationPostRepository;
    private final UserRepository userRepository;

    public GetApplicationPostService(ApplicationPostRepository applicationPostRepository,
                                     UserRepository userRepository) {
        this.applicationPostRepository = applicationPostRepository;
        this.userRepository = userRepository;
    }

    public ApplicationPostsDto posts(String identification) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        List<ApplicationPostDto> applicationPosts = applicationPostRepository
            .findAllByApplicant_Name(user.name())
            .stream().map(ApplicationPost::toDto)
            .collect(Collectors.toList());

        return new ApplicationPostsDto(applicationPosts);
    }
}
