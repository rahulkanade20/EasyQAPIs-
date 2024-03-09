package com.EasyQ.API.services;

import com.EasyQ.API.dto.BranchDto;
import com.EasyQ.API.dto.BranchMapper;
import com.EasyQ.API.models.Branch;
import com.EasyQ.API.models.Tenant;
import com.EasyQ.API.repository.BranchRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BranchService {
    Logger logger = LoggerFactory.getLogger(BranchService.class);

    private static final String EMAIL_ADDRESS = "email_address";
    private static final String NAME = "name";
    private static final String STATUS = "status";

    private static final String ADDRESS = "address";

    @Autowired
    public BranchMapper branchMapper;

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    public List<Branch> getAllBranches() {
        List<Branch> branches = new ArrayList<>();
        logger.info("Fetching all branches ...");
        branchRepository.findAll().forEach(b -> branches.add(b));
        return branches;
    }

    public Optional<Branch> getBranch(Long id) {
        logger.info("Fetching branch ...");
        return branchRepository.findById(id);
    }

    public void addBranch(Branch b) {
        logger.info("adding branch ...");
        b.setCreation_time(LocalDateTime.now());
        b.setUpdation_time(null);
        branchRepository.save(b);
    }

    public void updateBranch(Long id, BranchDto dto) {
        logger.info("updating branch ...");
        Optional<Branch> branch = branchRepository.findById(id);
        try {
            Branch b = branch.get();
            branchMapper.updateBranchFromDto(dto, b);
            b.setUpdation_time(LocalDateTime.now());
            branchRepository.save(b);
        } catch (NoSuchElementException e) {
            logger.info(e.toString());
        }
    }

//    public void updateBranch(Long id, HashMap<String, Object> requestBody) {
//        logger.info("updating branch ...");
//        Optional<Branch> branch = branchRepository.findById(id);
//        AtomicInteger flag = new AtomicInteger();
//        try {
//            requestBody.forEach((key, value) -> {
//                if(key.equals(EMAIL_ADDRESS)) {
//                    branch.get().setEmail_address((String) value);
//                    flag.set(1);
//                } else if(key.equals(NAME)) {
//                    branch.get().setName((String) value);
//                    flag.set(1);
//                } else if(key.equals(STATUS)) {
//                    branch.get().setStatus((Boolean) value);
//                    flag.set(1);
//                } else if(key.equals(ADDRESS)) {
//                    branch.get().setAddress((String) value);
//                    flag.set(1);
//                }
//            });
//            if(flag.get() == 1) {
////                String timeStampString = LocalDateTime.now().toString();
////                branch.get().setUpdation_time(timeStampString);
//                branch.get().setUpdation_time(LocalDateTime.now());
//                branchRepository.save(branch.get());
//            }
//        } catch (NoSuchElementException e) {
//            logger.info(e.toString());
//        }
//    }

    public void deleteBranch(Long id) {
        logger.info("deleting branch ...");
        branchRepository.deleteById(id);
    }
}
