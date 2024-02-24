package com.EasyQ.API.controllers;

import com.EasyQ.API.models.Tenant;
import com.EasyQ.API.services.TenantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @GetMapping("/tenants")
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/tenants/{id}")
    public Optional<Tenant> getTenant(@PathVariable Long id) {
        return tenantService.getTenant(id);
    }

    @PostMapping("/tenants")
    public void addTenant(@RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
    }

    @PutMapping("/tenants/{id}")
    public void updateTenant(@RequestBody Tenant tenant, @PathVariable Long id) {
        tenantService.updateTenant(id, tenant);
    }

    @DeleteMapping("/tenants/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantService.deleteTenant(id);
    }
}
