package com.EasyQ.API.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tenant")
public class Tenant {
    @Id
    @Column(name="tenant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="status")
    private boolean status;

    @Column(name="created_at")
    private LocalDateTime creation_time;

    @Column(name="updated_at")
    private LocalDateTime updation_time;

    @Column(name="name")
    private String name;

    @Column(name="tenant_admin_email_id")
    private String email_address;

    public Tenant() {
    }

    public Tenant(long id, boolean status, LocalDateTime creation_time, LocalDateTime updation_time, String name, String email_address) {
        this.id = id;
        this.status = status;
        this.creation_time = creation_time;
        this.updation_time = updation_time;
        this.name = name;
        this.email_address = email_address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(LocalDateTime creation_time) {
        this.creation_time = creation_time;
    }

    public LocalDateTime getUpdation_time() {
        return updation_time;
    }

    public void setUpdation_time(LocalDateTime updation_time) {
        this.updation_time = updation_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", status=" + status +
                ", creation_time='" + creation_time + '\'' +
                ", updation_time='" + updation_time + '\'' +
                ", name='" + name + '\'' +
                ", email_address='" + email_address + '\'' +
                '}';
    }
}
