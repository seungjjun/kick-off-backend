package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Applicant;
import com.junstudio.kickoff.models.CreationNumber;

public class ApplicationPostDto {
    private final Long id;
    private final String reason;
    private final Applicant applicant;
    private final CreationNumber creationNumber;
    private final String state;

    public ApplicationPostDto(Long id,
                              String reason,
                              Applicant applicant,
                              CreationNumber creationNumber,
                              String state
    ) {
        this.id = id;
        this.reason = reason;
        this.applicant = applicant.toDto();
        this.creationNumber = creationNumber.toDto();
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public CreationNumber getCreationNumber() {
        return creationNumber;
    }

    public String getState() {
        return state;
    }
}
