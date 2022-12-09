package com.junstudio.kickoff.admin.services;

import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.exceptions.LoginFailed;
import com.junstudio.kickoff.models.Admin;
import com.junstudio.kickoff.repositories.AdminRepository;
import com.junstudio.kickoff.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminLoginService {
    private final AdminRepository adminRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AdminLoginService(AdminRepository adminRepository,
                             JwtUtil jwtUtil,
                             PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResultDto login(String identification, String password) {
        Admin admin = adminRepository.findByIdentification(identification)
            .orElseThrow(LoginFailed::new);

        String accessToken = jwtUtil.encode(identification);

        if (!admin.authenticate(password, passwordEncoder)) {
            throw new LoginFailed();
        }

        return new LoginResultDto(accessToken, admin.name());
    }
}
