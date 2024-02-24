package com.EasyQ.API.services;

import com.EasyQ.API.models.Tenant;
import com.EasyQ.API.repository.TenantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = new ArrayList<>();
        tenantRepository.findAll().forEach(t -> tenants.add(t));
        return tenants;
    }

    public Optional<Tenant> getTenant(Long id) {
        return tenantRepository.findById(id);
    }

    public void addTenant(Tenant t) {
        tenantRepository.save(t);
    }

    public void updateTenant(Long id, Tenant t) {
        tenantRepository.save(t);
    }

    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id);
    }
}
