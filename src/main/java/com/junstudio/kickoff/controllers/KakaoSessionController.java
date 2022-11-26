package com.junstudio.kickoff.controllers;

import com.junstudio.kickoff.dtos.LoginResultDto;
import com.junstudio.kickoff.services.KakaoService;
import com.junstudio.kickoff.services.LoginService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class KakaoSessionController {
    private final KakaoService kakaoService;
    private final LoginService loginService;

    public KakaoSessionController(KakaoService kakaoService,
                                  LoginService loginService) {
        this.kakaoService = kakaoService;
        this.loginService = loginService;
    }

    @GetMapping("/auth/token")
    private LoginResultDto kakaoLogin(@RequestParam("code") String code) {
        String accessToken = kakaoService.getAccessToken(code);

        HashMap<String, Object> userInfo = kakaoService.getUser(accessToken);

        return loginService.kakaoLogin(userInfo);
    }
}
