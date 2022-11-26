package com.junstudio.kickoff.dtos;

import javax.validation.constraints.Pattern;

public class RegistrationRequestDto {
    @Pattern(regexp = "^[가-힣a-z0-9]{2,10}$",
        message = "닉네임은 특수문자를 제외한 2 ~ 10자리여야 합니다.")
    private String name;

    @Pattern(regexp = "^[a-z0-9]{4,16}$",
        message = "아이디는 4 ~ 16자 영문 소문자, 숫자를 사용해야 합니다.")
    private String identification;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,}",
        message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    private String confirmPassword;

    public RegistrationRequestDto() {
    }

    public RegistrationRequestDto(String name,
                                  String identification,
                                  String password,
                                  String confirmPassword) {
        this.name = name;
        this.identification = identification;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public String getIdentification() {
        return identification;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
