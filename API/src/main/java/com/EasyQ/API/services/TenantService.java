package com.EasyQ.API.services;

import com.EasyQ.API.models.Tenant;
import com.EasyQ.API.repository.TenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TenantService {

    Logger logger = LoggerFactory.getLogger(TenantService.class);

    private final TenantRepository tenantRepository;

    private static final String EMAIL_ADDRESS = "email_address";
    private static final String NAME = "name";
    private static final String STATUS = "status";

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public List<Tenant> getAllTenants() {
        List<Tenant> tenants = new ArrayList<>();
        logger.info("Fetching all tenants ...");
        tenantRepository.findAll().forEach(t -> tenants.add(t));
        return tenants;
    }

    public Optional<Tenant> getTenant(Long id) {
        logger.info("Fetching tenant ...");
        return tenantRepository.findById(id);
    }

    public void addTenant(Tenant t) {
        logger.info("adding tenant ...");
        String timeStampString = LocalDateTime.now().toString();
        t.setCreation_time(timeStampString);
        t.setUpdation_time(null);
        tenantRepository.save(t);
    }

    public void updateTenant(Long id, HashMap<String, Object> requestBody) {
        logger.info("updating tenant ...");
        Optional<Tenant> tenant = tenantRepository.findById(id);
        AtomicInteger flag = new AtomicInteger();
        try {
            requestBody.forEach((key, value) -> {
                if(key.equals(EMAIL_ADDRESS)) {
                    tenant.get().setEmail_address((String) value);
                    flag.set(1);
                } else if(key.equals(NAME)) {
                    tenant.get().setName((String) value);
                    flag.set(1);
                } else if(key.equals(STATUS)) {
                    tenant.get().setStatus((Boolean) value);
                    flag.set(1);
                }
            });
            if(flag.get() == 1) {
                String timeStampString = LocalDateTime.now().toString();
                tenant.get().setUpdation_time(timeStampString);
                tenantRepository.save(tenant.get());
            }
        } catch (NoSuchElementException e) {
            logger.info(e.toString());
        }
    }

    public void deleteTenant(Long id) {
        logger.info("deleting tenant ...");
        tenantRepository.deleteById(id);
    }
}
