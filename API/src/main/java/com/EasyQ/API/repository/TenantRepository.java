package com.EasyQ.API.repository;

import com.EasyQ.API.models.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, Long> {

}
