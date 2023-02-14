package com.example.techassessment.dao;

import com.example.techassessment.model.Employee;

import java.util.List;

public interface EmployeeDao {
    public Object addEmployee(Employee employee);
    Boolean existsByNationalId(String nationalId);
    public Employee fetchById(String id);
    public List<Employee> fetchAll(int page, int size);
    public Employee update(Employee employee);
    public void delete(String id);
}
