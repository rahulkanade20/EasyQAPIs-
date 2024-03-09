package com.EasyQ.API.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="branch")
public class Branch {
    @Id
    @Column(name="branch_id")
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

    @Column(name="address")
    private String address;

    @Column(name="branch_admin_email_id")
    private String email_address;

    @Column(name="t_id")
    private long tenant_id;

    public Branch(long id, boolean status, LocalDateTime creation_time, LocalDateTime updation_time, String name, String address, String email_address, long tenant_id) {
        this.id = id;
        this.status = status;
        this.creation_time = creation_time;
        this.updation_time = updation_time;
        this.name = name;
        this.address = address;
        this.email_address = email_address;
        this.tenant_id = tenant_id;
    }

    public Branch() {

    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", status=" + status +
                ", creation_time='" + creation_time + '\'' +
                ", updation_time='" + updation_time + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email_address='" + email_address + '\'' +
                ", tenant_id=" + tenant_id +
                '}';
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public long getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(long tenant_id) {
        this.tenant_id = tenant_id;
    }
}
