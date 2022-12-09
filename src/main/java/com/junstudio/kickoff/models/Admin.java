package com.junstudio.kickoff.models;

import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue
    @Column(name = "admin_id")
    private Long id;

    private String identification;

    private String encodedPassword;

    private String name;

    private String profileImage;

    public Admin() {
    }

    public Admin(Long id, String identification,
                 String encodedPassword,
                 String name,
                 String profileImage) {
        this.id = id;
        this.identification = identification;
        this.encodedPassword = encodedPassword;
        this.name = name;
        this.profileImage = profileImage;
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

    public boolean authenticate(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(password, encodedPassword);
    }

    public static Admin fake() {
        return new Admin(1L, "jel1y", "password", "jun", "profileImage");
    }

    public void changePassword(String password, PasswordEncoder passwordEncoder) {
        encodedPassword = passwordEncoder.encode(password);
    }
}
