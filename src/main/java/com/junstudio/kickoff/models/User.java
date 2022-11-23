package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String identification;

    private String encodedPassword;

    private String name;

    private String profileImage;

    private Long gradeId;

    @Column(name = "is_my_token", columnDefinition = "boolean default false")
    private boolean isMyToken = false;

    private User() {
    }

    public User(Long id, String identification, String encodedPassword,
                String name, String profileImage, Long gradeId, boolean isMyToken) {
        this.id = id;
        this.identification = identification;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.profileImage = profileImage;
        this.gradeId = gradeId;
        this.isMyToken = isMyToken;
    }

    public Long id() {
        return id;
    }

    public String identification() {
        return identification;
    }

    public String encodedPassword() {
        return encodedPassword;
    }

    public String name() {
        return name;
    }

    public String profileImage() {
        return profileImage;
    }

    public Long gradeId() {
        return gradeId;
    }

    public UserDto toDto() {
        return new UserDto(id, identification, name, profileImage, isMyToken);
    }

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        encodedPassword = passwordEncoder.encode(password);
    }

    public void changeTokenState() {
        isMyToken = true;
    }
}
