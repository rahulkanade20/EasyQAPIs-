package com.EasyQ.API.controllers;

import com.EasyQ.API.exceptions.NotFoundException;
import com.EasyQ.API.exceptions.ServerException;
import com.EasyQ.API.exceptions.UnprocessableRequestException;
import com.EasyQ.API.models.Branch;
import com.EasyQ.API.models.Tenant;
import com.EasyQ.API.services.BranchService;
import com.EasyQ.API.services.TenantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.EasyQ.API.Utils.Constants.USER_DELETION_SUCCESS_MESSAGE;
import static com.EasyQ.API.Utils.Constants.USER_UPDATION_SUCCESS_MESSAGE;
import static com.EasyQ.API.Utils.ErrorConstants.*;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    private final TenantService tenantService;

    private final BranchService branchService;

    public TenantController(TenantService tenantService, BranchService branchService) {
        this.tenantService = tenantService;
        this.branchService = branchService;
    }

    @GetMapping("/test")
    public String getTestString() {
        return "hello";
    }

    @GetMapping("")
    public ResponseEntity<?> getAllTenants() {
        List<Tenant> tenants = tenantService.getAllTenants();
        if(tenants == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ServerException(LocalDateTime.now(), SERVER_ERROR, SERVER_ERROR_DESCRIPTION));
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .header("test header", "abc")
                .body(tenants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTenant(@PathVariable Long id) {
        Optional<Tenant> tenant = tenantService.getTenant(id);
        if(tenant == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ServerException(LocalDateTime.now(), SERVER_ERROR, SERVER_ERROR_DESCRIPTION));
        }
        if(tenant.isPresent()) {
            return ResponseEntity.ok(tenant.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundException(LocalDateTime.now(), NOT_FOUND_ERROR, MessageFormat.format(NOT_FOUND_ERROR_DESCRIPTION, id)));
        }
    }

    @PostMapping("")
    public void addTenant(@RequestBody Tenant tenant) {
        tenantService.addTenant(tenant);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateTenant(@RequestBody HashMap<String, Object> requestBody, @PathVariable Long id) {
        List<Tenant> tenants = tenantService.getAllTenants();
        if(tenants == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ServerException(LocalDateTime.now(), SERVER_ERROR, SERVER_ERROR_DESCRIPTION));
        } else {
            boolean isPresent = tenants.stream().anyMatch(tenant -> tenant.getId() == id);
            if(isPresent) {
                tenantService.updateTenant(id, requestBody);
                return ResponseEntity
                        .ok(MessageFormat.format(USER_UPDATION_SUCCESS_MESSAGE, id));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundException(LocalDateTime.now(), NOT_FOUND_ERROR, MessageFormat.format(NOT_FOUND_ERROR_DESCRIPTION, id)));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTenant(@PathVariable Long id) {
        List<Tenant> tenants = tenantService.getAllTenants();
        if(tenants == null) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ServerException(LocalDateTime.now(), SERVER_ERROR, SERVER_ERROR_DESCRIPTION));
        } else {
            boolean isPresent = tenants.stream().anyMatch(tenant -> tenant.getId() == id);
            if(isPresent) {
                List<Branch> branches = branchService.getAllBranches();
                boolean isReference = branches.stream().anyMatch(branch -> branch.getTenant_id() == id);
                if(isReference) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(new UnprocessableRequestException(LocalDateTime.now(), CONFLICTING_REQUEST_ERROR, CONFLICTING_REQUEST_ERROR_DESCRIPTION));
                }
                tenantService.deleteTenant(id);
                return ResponseEntity
                        .ok(MessageFormat.format(USER_DELETION_SUCCESS_MESSAGE, id));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundException(LocalDateTime.now(), NOT_FOUND_ERROR, MessageFormat.format(NOT_FOUND_ERROR_DESCRIPTION, id)));
        }
    }
}
