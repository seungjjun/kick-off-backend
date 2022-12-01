package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

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

    @Embedded
    private Grade grade;

    @Transient
    private boolean isMyToken = false;

    public User() {
    }

    public User(String name, String identification) {
        this.name = name;
        this.identification = identification;
        this.grade = new Grade("아마추어");
    }

    public User(Long id, String identification, String encodedPassword,
                String name, String profileImage, Grade grade, boolean isMyToken) {
        this.id = id;
        this.identification = identification;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.profileImage = profileImage;
        this.grade = grade;
        this.isMyToken = isMyToken;
    }

    public static User fake() {
        return new User(1L, "jel1y", "password",
            "Jun", "profileImage", new Grade("아마추어"), false);
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

    public Grade grade() {
        return grade;
    }

    public boolean isMyToken() {
        return isMyToken;
    }

    public UserDto toDto() {
        return new UserDto(id, identification, name, profileImage, grade.name(), isMyToken);
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

    public void setTokenState() {
        isMyToken = false;
    }

    public void update(String name, String profileImage) {
        this.name = name;
        if (profileImage.equals("")) {
            return;
        }
        this.profileImage = profileImage;
    }

    public void changeGrade(String grade) {
        this.grade = new Grade(grade);
    }
}
