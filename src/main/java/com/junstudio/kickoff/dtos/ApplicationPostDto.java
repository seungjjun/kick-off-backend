package com.junstudio.kickoff.dtos;

import com.junstudio.kickoff.models.Applicant;
import com.junstudio.kickoff.models.CreationNumber;

public class ApplicationPostDto {
    private final Long id;
    private final String reason;
    private final Applicant applicant;
    private final CreationNumber creationNumber;

    public ApplicationPostDto(Long id, String reason, Applicant applicant, CreationNumber creationNumber) {
        this.id = id;
        this.reason = reason;
        this.applicant = applicant.toDto();
        this.creationNumber = creationNumber.toDto();
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
}
