package com.EasyQ.API.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QueueDto {
    private Long id;
    private Boolean status;
    private String creation_time;
    private String updation_time;
    private String name;
    private Long ewt;
    private String email_address;
    private Long branch_id;

    public QueueDto() {
        this.id = null;
        this.status = null;
        this.creation_time = null;
        this.updation_time = null;
        this.name = null;
        this.ewt = null;
        this.email_address = null;
        this.branch_id = null;
    }

    public QueueDto(Long id, Boolean status, String creation_time, String updation_time, String name, Long ewt, String email_address, Long branch_id) {
        this.id = id;
        this.status = status;
        this.creation_time = creation_time;
        this.updation_time = updation_time;
        this.name = name;
        this.ewt = ewt;
        this.email_address = email_address;
        this.branch_id = branch_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(String creation_time) {
        this.creation_time = creation_time;
    }

    public String getUpdation_time() {
        return updation_time;
    }

    public void setUpdation_time(String updation_time) {
        this.updation_time = updation_time;
    }

    @JsonProperty("queue_name")
    public String getName() {
        return name;
    }

    @JsonProperty("queue_name")
    public void setName(String name) {
        this.name = name;
    }

    public Long getEwt() {
        return ewt;
    }

    public void setEwt(Long ewt) {
        this.ewt = ewt;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public Long getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Long branch_id) {
        this.branch_id = branch_id;
    }

    @Override
    public String toString() {
        return "QueueDto{" +
                "id=" + id +
                ", status=" + status +
                ", creation_time='" + creation_time + '\'' +
                ", updation_time='" + updation_time + '\'' +
                ", name='" + name + '\'' +
                ", ewt=" + ewt +
                ", email_address='" + email_address + '\'' +
                ", branch_id=" + branch_id +
                '}';
    }
}
