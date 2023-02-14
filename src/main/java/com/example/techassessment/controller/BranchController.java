package com.example.techassessment.controller;

import com.example.techassessment.model.dto.BranchDto;
import com.example.techassessment.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/branch")
public class BranchController extends AbstractController<BranchDto, BranchDto> {

    @Autowired
    BranchService branchService;

    @PostMapping
    public ResponseEntity<BranchDto> addBranch(@Valid @RequestBody BranchDto branchDto){
        return handle(branchService.addBranch(branchDto));
    }
    @GetMapping("{id}")
    public ResponseEntity<BranchDto> fetchById(@PathVariable("id") Object id) throws Exception {
        return handle(branchService.fetchById(String.valueOf(id)));
    }

    @GetMapping
    public ResponseEntity<List<BranchDto>> fetchAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return handle(branchService.fetchAll(page, size));
    }


    @PutMapping
    public ResponseEntity<BranchDto> update(@Valid @RequestBody BranchDto branchDto) throws Exception {
        return handle(branchService.update(branchDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BranchDto> delete(@PathVariable("id") Object id) throws Exception {
        return handle(branchService.delete(String.valueOf(id)));
    }

}
