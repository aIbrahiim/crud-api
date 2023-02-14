package com.example.techassessment.dao;

import com.example.techassessment.model.Branch;

import java.util.List;

public interface BranchDao {

    public Object addBranch(Branch employee);
    public Branch fetchById(String id);
    public List<Branch> fetchAll(int page, int size);
    public Branch update(Branch branch);
    public void delete(String id);
}
