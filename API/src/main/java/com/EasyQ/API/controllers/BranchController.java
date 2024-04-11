package com.EasyQ.API.controllers;

import com.EasyQ.API.dto.BranchDto;
import com.EasyQ.API.exceptions.UnprocessableRequestException;
import com.EasyQ.API.models.Branch;
import com.EasyQ.API.models.Tenant;
import com.EasyQ.API.services.BranchService;
import com.EasyQ.API.services.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.EasyQ.API.Utils.Constants.*;
import static com.EasyQ.API.Utils.ErrorConstants.*;
import static com.EasyQ.API.Utils.ErrorConstants.CONFLICTING_REQUEST_ERROR_DESCRIPTION;

@RestController
@RequestMapping(value = "/branches")
public class BranchController {

    Logger logger = LoggerFactory.getLogger(BranchController.class);

    private final BranchService branchService;

    private final TenantService tenantService;

    public BranchController(BranchService branchService, TenantService tenantService) {
        this.branchService = branchService;
        this.tenantService = tenantService;
    }

    @GetMapping("/test")
    public String getTestString() {
        return "hello";
    }

    @GetMapping("")
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @GetMapping("/{id}")
    public Optional<Branch> getBranch(@PathVariable Long id) {
        return branchService.getBranch(id);
    }

    @PostMapping("")
    public ResponseEntity<?> addBranch(@RequestBody Branch branch) {
        List<Tenant> tenants = tenantService.getAllTenants();
        boolean isPresent = tenants.stream().anyMatch(tenant -> tenant.getId() == branch.getTenant_id());
        if(isPresent) {
            branchService.addBranch(branch);
            return ResponseEntity.ok(USER_ADDITION_SUCCESS_MESSAGE);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new UnprocessableRequestException(LocalDateTime.now(), CONFLICTING_REQUEST_ERROR, CONFLICTING_REQUEST_ERROR_DESCRIPTION));
    }

    @PatchMapping("/{id}")
    public void updateBranch(@RequestBody BranchDto branchDto, @PathVariable Long id) {
        logger.info(branchDto.toString());
        branchService.updateBranch(id, branchDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
    }

    @GetMapping("/forTenant")
    public ResponseEntity<?> getAllBranchesForTenant(@RequestParam(name="tenantId", required=false) Long id) {
        if(id == null) {
            return ResponseEntity.ok(TENANT_NOT_PROVIDED);
        } else {
            List<Branch> branches = branchService.getAllBranches();
            List<Branch> filteredBranches = branches.stream().filter(branch -> branch.getTenant_id() == id).collect(Collectors.toList());
            if(filteredBranches.isEmpty()) {
                return ResponseEntity.ok(MessageFormat.format(BRANCHES_NOT_FOUND, id));
            } else {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(filteredBranches);
            }
        }
    }
}
