package com.EasyQ.API.controllers;

import com.EasyQ.API.models.Tenant;
import com.EasyQ.API.services.TenantService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/test")
    public String getTestString() {
        return "hello";
    }

    @GetMapping("")
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/{id}")
    public Optional<Tenant> getTenant(@PathVariable Long id) {
        return tenantService.getTenant(id);
    }

    @PostMapping("")
    public void addTenant(@RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
    }

    @PatchMapping("/{id}")
    public void updateTenant(@RequestBody HashMap<String, Object> requestBody, @PathVariable Long id) {
        tenantService.updateTenant(id, requestBody);
    }

    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
    }
}
