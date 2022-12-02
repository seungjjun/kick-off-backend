package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PatchUserAdminService {
    private final UserRepository userRepository;

    public PatchUserAdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void patch(List<Long> usersId, String grade) {
        for (Long userId : usersId) {
            User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

            user.changeGrade(grade);
        }
    }
}
