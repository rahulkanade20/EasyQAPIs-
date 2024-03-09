package com.EasyQ.API.controllers;

import com.EasyQ.API.dto.BranchDto;
import com.EasyQ.API.models.Branch;
import com.EasyQ.API.services.BranchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/branches")
public class BranchController {

    Logger logger = LoggerFactory.getLogger(BranchController.class);

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
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
    public void addBranch(@RequestBody Branch branch) {
        branchService.addBranch(branch);
    }

    @PatchMapping("/{id}")
    public void updateBranch(@RequestBody BranchDto branchDto, @PathVariable Long id) {
        logger.info(branchDto.toString());
        branchService.updateBranch(id, branchDto);
    }

//    @PatchMapping("/{id}")
//    public void updateBranch(@RequestBody HashMap<String, Object> requestBody, @PathVariable Long id) {
//        branchService.updateBranch(id, requestBody);
//    }

    @DeleteMapping("/{id}")
    public void deleteBranch(@PathVariable Long id) {
        branchService.deleteBranch(id);
    }
}
