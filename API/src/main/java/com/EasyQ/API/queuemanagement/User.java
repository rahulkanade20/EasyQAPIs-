package com.EasyQ.API.queuemanagement;

import java.util.UUID;

public class User {
    private long id;

    private long queueId;

    private String emailAddress;

    private int eta;

    public User(long queueId, String emailAddress) {
        this.id = Math.abs(UUID.randomUUID().getMostSignificantBits());
        this.queueId = queueId;
        this.emailAddress = emailAddress;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQueueId() {
        return queueId;
    }

    public void setQueueId(long queueId) {
        this.queueId = queueId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


}
