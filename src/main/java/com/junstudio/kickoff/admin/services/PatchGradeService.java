package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.exceptions.ApplicationPostNotFound;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PatchGradeService {
    private final UserRepository userRepository;
    private final ApplicationPostRepository applicationPostRepository;

    public PatchGradeService(UserRepository userRepository,
                             ApplicationPostRepository applicationPostRepository) {
        this.userRepository = userRepository;
        this.applicationPostRepository = applicationPostRepository;
    }

    public void patch(Long applicationPostId, String grade, String userName) {
        User user = userRepository.findByName(userName).orElseThrow(UserNotFound::new);

        user.changeGrade(grade);

        ApplicationPost applicationPost = applicationPostRepository
            .findById(applicationPostId)
            .orElseThrow(ApplicationPostNotFound::new);

        applicationPost.changeState("success");
    }
}
