package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.exceptions.ApplicationPostNotFound;
import com.junstudio.kickoff.models.ApplicationPost;
import com.junstudio.kickoff.repositories.ApplicationPostRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class DeleteApplicationPostAdminService {
    private final ApplicationPostRepository applicationPostRepository;

    public DeleteApplicationPostAdminService(ApplicationPostRepository applicationPostRepository) {
        this.applicationPostRepository = applicationPostRepository;
    }

    public void delete(Long applicationPostId) {
        ApplicationPost applicationPost =
            applicationPostRepository.findById(applicationPostId)
                .orElseThrow(ApplicationPostNotFound::new);

        applicationPost.changeState("refuse");
    }
}
