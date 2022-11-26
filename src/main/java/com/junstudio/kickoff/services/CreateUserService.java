package com.junstudio.kickoff.services;

import com.junstudio.kickoff.exceptions.AlreadyExistingIdentification;
import com.junstudio.kickoff.exceptions.PasswordNotMatched;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CreateUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncode;

    public CreateUserService(UserRepository userRepository,
                             PasswordEncoder passwordEncode) {
        this.userRepository = userRepository;
        this.passwordEncode = passwordEncode;
    }

    public User register(String name, String identification, String password, String confirmPassword) {
        if (userRepository.existsByIdentification(identification)) {
            throw new AlreadyExistingIdentification();
        }

        if(!password.equals(confirmPassword)) {
            throw new PasswordNotMatched();
        }

        User user = new User(name, identification);
        user.changePassword(password, passwordEncode);

        return userRepository.save(user);
    }
}
