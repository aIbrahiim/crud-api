package com.example.techassessment.service.impl;

import com.example.techassessment.dao.BranchDao;
import com.example.techassessment.exception.BranchException;
import com.example.techassessment.exception.EmployeeException;
import com.example.techassessment.model.Branch;
import com.example.techassessment.model.dto.BranchDto;
import com.example.techassessment.service.BranchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class BranchServiceImpl implements BranchService {
    @Autowired
    BranchDao branchDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public BranchDto addBranch(BranchDto branchDto) {
        int id = 0;
        if(branchDto == null){
            throw new BranchException("Branch can't be null");
        }
        Branch branch = modelMapper.map(branchDto, Branch.class);
        id = (Integer) branchDao.addBranch(branch);
        return branchDto
                .builder()
                .id(id)
                .build();
    }

    @Override
    public BranchDto fetchById(String id) {
        Branch branch = branchDao.fetchById(id);
        if (branch == null) {
            throw new EmployeeException("Employee not found");
        }
        BranchDto branchDto = modelMapper.map(branch, BranchDto.class);
        return branchDto;
    }

    @Override
    public List<BranchDto> fetchAll(int page, int size) {
        List<Branch> branchList = branchDao.fetchAll(page, size);
        List<BranchDto> result = branchList
                .stream()
                .map(b -> modelMapper.map(b, BranchDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public BranchDto delete(String id) {
        Branch branch = branchDao.fetchById(id);
        if (branch == null) {
            throw new BranchException("Branch not found");
        }
        branchDao.delete(id);
        return BranchDto
                .builder()
                .id(Integer.parseInt(id))
                .build();
    }

    @Override
    public BranchDto update(BranchDto branchDto) {
        if (branchDto == null) {
            throw new BranchException("Branch can't be null");
        }
        Branch branch = branchDao.fetchById(String.valueOf(branchDto.getId()));
        if (branch == null) {
            throw new BranchException("Branch not found");
        }
        branch = modelMapper.map(branch, Branch.class);
        branch = branchDao.update(branch);
        branchDto = modelMapper.map(branch, BranchDto.class);
        return branchDto;
    }
}
