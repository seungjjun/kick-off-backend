package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.UserDto;
import com.junstudio.kickoff.exceptions.PatchFailed;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PatchUserService {
    private final UserRepository userRepository;

    public PatchUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void patch(Long userId, UserDto userDto) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFound::new);

        if (userRepository.existsByName(userDto.getName())) {
            throw new PatchFailed();
        }

        user.update(userDto.getName(), userDto.getProfileImage());
    }
}
