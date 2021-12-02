package com.example.PollApp.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

public class VoteId implements Serializable {
    private Integer userId;
    private Integer answerId;

    public VoteId() {
    }

    public VoteId(Integer userId, Integer answerId) {
        this.userId = userId;
        this.answerId = answerId;
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getAnswerId() { return answerId; }

    public void setAnswerId(Integer answerId) { this.answerId = answerId; }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true (geekforgeeks.org)
        if (o == this) return true;

        // Check if o is an instance of VoteID or "not null instanceof [type]" also returns false (geekforgeeks.org)
        if (!(o instanceof VoteId)) return false;

        // typecast o to VoteID so that we can compare data members
        VoteId vId = (VoteId) o;

        return Objects.equals(this.userId, vId.userId) && Objects.equals(this.answerId, vId.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId, this.answerId);
    }
}
