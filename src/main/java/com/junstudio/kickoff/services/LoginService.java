package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.exceptions.LoginFailed;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.UserRepository;
import com.junstudio.kickoff.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResultDto login(String identification, String password) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(LoginFailed::new);

        String accessToken = jwtUtil.encode(identification);

        if (!user.authenticate(password, passwordEncoder)) {
            throw new LoginFailed();
        }

        return new LoginResultDto(
            accessToken,
            user.name(),
            user.profileImage(),
            user.grade().name()
        );
    }

    public LoginResultDto kakaoLogin(HashMap<String, Object> userInfo) {
        String name = String.valueOf(userInfo.get("nickname"));
        String identification = String.valueOf(userInfo.get("email"));

        if (!userRepository.existsByIdentification(identification)) {
            User user = new User(name, identification);

            user.changePassword(identification, passwordEncoder);

            userRepository.save(user);

            String accessToken = jwtUtil.encode(identification);

            return new LoginResultDto(accessToken, name, null, null);
        }

        User user = userRepository.findByIdentification(identification)
            .orElseThrow(UserNotFound::new);

        String accessToken = jwtUtil.encode(identification);

        return new LoginResultDto(accessToken, user.name(), user.profileImage(), null);
    }
}
