package com.EasyQ.API.dto;

public class BranchDto {
    private Long id;
    private Boolean status;
    private String creation_time;
    private String updation_time;
    private String name;
    private String address;
    private String email_address;
    private Long tenant_id;

    public BranchDto(Long id, Boolean status, String creation_time, String updation_time, String name, String address, String email_address, Long tenant_id) {
        this.id = id;
        this.status = status;
        this.creation_time = creation_time;
        this.updation_time = updation_time;
        this.name = name;
        this.address = address;
        this.email_address = email_address;
        this.tenant_id = tenant_id;
    }

    public BranchDto() {
        this.id = null;
        this.status = null;
        this.creation_time = null;
        this.updation_time = null;
        this.name = null;
        this.address = null;
        this.email_address = null;
        this.tenant_id = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isStatus() {
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

    public Long getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(Long tenant_id) {
        this.tenant_id = tenant_id;
    }

    @Override
    public String toString() {
        return "BranchDto{" +
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
}
