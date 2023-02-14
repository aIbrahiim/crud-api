package com.example.techassessment.controller;

import com.example.techassessment.model.dto.EmployeeDto;
import com.example.techassessment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController extends AbstractController<EmployeeDto, EmployeeDto>{

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        return handle(employeeService.addEmployee(employeeDto));
    }
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> fetchById(@PathVariable("id") Object id) throws Exception {
        return handle(employeeService.fetchById(String.valueOf(id)));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> fetchAll(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                      @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        return handle(employeeService.fetchAll(page, size));
    }


    @PutMapping
    public ResponseEntity<EmployeeDto> update(@Valid @RequestBody EmployeeDto employeeDto) throws Exception {
        return handle(employeeService.update(employeeDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<EmployeeDto> delete(@PathVariable("id") Object id) throws Exception {
         return handle(employeeService.delete(String.valueOf(id)));
    }

}
