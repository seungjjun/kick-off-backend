package com.junstudio.kickoff.services;

import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.exceptions.LoginFailed;
import com.junstudio.kickoff.exceptions.UserNotFound;
import com.junstudio.kickoff.models.Grade;
import com.junstudio.kickoff.models.User;
import com.junstudio.kickoff.repositories.GradeRepository;
import com.junstudio.kickoff.repositories.UserRepository;
import com.junstudio.kickoff.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final GradeRepository gradeRepository;
    private final JwtUtil jwtUtil;

    public LoginService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        GradeRepository gradeRepository,
                        JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.gradeRepository = gradeRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResultDto login(String identification, String password) {
        User user = userRepository.findByIdentification(identification)
            .orElseThrow(LoginFailed::new);

        String accessToken = jwtUtil.encode(identification);

        Grade grade = gradeRepository.getReferenceById(user.gradeId());

        if (!user.authenticate(password, passwordEncoder)) {
            throw new LoginFailed();
        }

        return new LoginResultDto(accessToken, user.name(), user.profileImage(), grade.getName());
    }

    public LoginResultDto kakaoLogin(HashMap<String, Object> userInfo) {
        String name = String.valueOf(userInfo.get("nickname"));
        String identification = String.valueOf(userInfo.get("nickname"));

        if(!userRepository.existsByIdentification(identification)) {
            User user = new User(name, identification);

            user.changePassword(name, passwordEncoder);

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
