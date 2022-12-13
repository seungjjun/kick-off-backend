package com.junstudio.kickoff.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    private UserId() {
    }

    public UserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "UserId{" +
            "userId =" + userId +
            '}';
    }
}
