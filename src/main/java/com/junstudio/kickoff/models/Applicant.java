package com.junstudio.kickoff.models;

import javax.persistence.Embeddable;

@Embeddable
public class Applicant {
    private String name;

    private String currentGrade;

    private String applicationGrade;

    public Applicant() {
    }

    public Applicant(String name, String currentGrade, String applicationGrade) {
        this.name = name;
        this.currentGrade = currentGrade;
        this.applicationGrade = applicationGrade;
    }

    public String getName() {
        return name;
    }

    public String getCurrentGrade() {
        return currentGrade;
    }

    public String getApplicationGrade() {
        return applicationGrade;
    }

    public Applicant toDto() {
        return new Applicant(name, currentGrade, applicationGrade);
    }
}
