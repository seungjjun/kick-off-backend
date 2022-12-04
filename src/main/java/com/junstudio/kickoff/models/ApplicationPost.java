package com.junstudio.kickoff.models;

import com.junstudio.kickoff.dtos.ApplicationPostDto;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ApplicationPost {
    @Id
    @GeneratedValue
    @Column(name = "application_id")
    private Long id;

    private String reason;

    @Embedded
    private Applicant applicant;

    @Embedded
    private CreationNumber creationNumber;

    private String state;

    public ApplicationPost() {
    }

    public ApplicationPost(Long id,
                           String reason,
                           Applicant applicant,
                           CreationNumber creationNumber,
                           String state
    ) {
        this.id = id;
        this.reason = reason;
        this.applicant = applicant;
        this.creationNumber = creationNumber;
        this.state = state;
    }

    public ApplicationPost(String reason, Applicant applicant, CreationNumber creationNumber) {
        this.reason = reason;
        this.applicant = applicant;
        this.creationNumber = creationNumber;
        this.state = "processing";
    }

    public Long id() {
        return id;
    }

    public String reason() {
        return reason;
    }

    public CreationNumber creationNumber() {
        return creationNumber;
    }

    public Applicant applicant() {
        return applicant;
    }

    public String state() {
        return state;
    }

    public ApplicationPostDto toDto() {
        return new ApplicationPostDto(id, reason, applicant, creationNumber, state);
    }

    public void changeState(String state) {
        this.state = state;
    }

    public static ApplicationPost fake() {
        return new ApplicationPost(
            1L,
            "reason",
            new Applicant("son", "아마추어", "프로"),
            new CreationNumber(1L, 1L),
            "processing");
    }
}
