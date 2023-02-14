package com.example.techassessment.service;

import com.example.techassessment.model.dto.BranchDto;

import java.util.List;


public interface BranchService {

    public BranchDto addBranch(BranchDto branchDto);
    public BranchDto fetchById(String id);
    public List<BranchDto> fetchAll(int page, int size);
    public BranchDto delete(String id);
    public BranchDto update(BranchDto branchDto);
}
