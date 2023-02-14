package com.example.techassessment.service.impl;

import com.example.techassessment.dao.EmployeeDao;
import com.example.techassessment.exception.BranchException;
import com.example.techassessment.exception.EmployeeException;
import com.example.techassessment.model.Branch;
import com.example.techassessment.model.Employee;
import com.example.techassessment.model.dto.BranchDto;
import com.example.techassessment.model.dto.EmployeeDto;
import com.example.techassessment.service.BranchService;
import com.example.techassessment.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final static Pattern pattern = Pattern.compile("[0-9]+");
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    BranchService branchService;

    @Autowired
    ModelMapper modelMapper;

    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        int id = 0;
        try {
            if (employeeDto == null) {
                throw new EmployeeException("Employee can't be null");
            }
            if (employeeDto.getNationalId().length() < 14) {
                throw new EmployeeException("National Id length should be 14");
            }
            if (!employeeDao.existsByNationalId(employeeDto.getNationalId())) {
                throw new EmployeeException("National id already exists");
            }
            if (!pattern.matcher(employeeDto.getNationalId()).matches()) {
                throw new EmployeeException("National Id should be in digit format only");
            }
            if (!validateEmployeeAge(employeeDto)) {
                throw new EmployeeException("Age is not valid");
            }
            if(employeeDto.getBranch() == null || employeeDto.getBranch().getId() <= 0){
                throw new BranchException("Branch of employee can't be null");
            }
            BranchDto branchDto = branchService.fetchById(String.valueOf(employeeDto.getBranch().getId()));
            if(branchDto == null){
                throw new BranchException("Branch is not found");
            }
            Employee employee = modelMapper.map(employeeDto, Employee.class);
            employee.setBranch(modelMapper.map(branchDto, Branch.class));
            id = (Integer) employeeDao.addEmployee(employee);

        } catch (Exception e) {
            throw new EmployeeException(e.getMessage());
        }
        return employeeDto
                .builder()
                .id(id)
                .build();
    }

    @Override
    public EmployeeDto fetchById(String id) throws Exception {
        Employee employee = employeeDao.fetchById(id);
        if (employee == null) {
            throw new EmployeeException("Employee not found");
        }
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;

    }

    @Override
    public List<EmployeeDto> fetchAll(int page, int size) {
        List<Employee> employeeList = employeeDao.fetchAll(page, size);
        List<EmployeeDto> result = employeeList
                .stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public EmployeeDto update(EmployeeDto employeeDto) throws Exception {
        if (employeeDto == null) {
            throw new EmployeeException("Employee can't be null");
        }
        if(employeeDto.getNationalId().length() < 14){
            throw new EmployeeException("National Id length should be 14");
        }
        if (!employeeDao.existsByNationalId(employeeDto.getNationalId())) {
            throw new EmployeeException("National id already exists");
        }
        if(!pattern.matcher(employeeDto.getNationalId()).matches()){
            throw new EmployeeException("National Id should be in digit format only");
        }
        if (!validateEmployeeAge(employeeDto)) {
            throw new EmployeeException("Age is not valid");
        }
        Employee employee = employeeDao.fetchById(String.valueOf(employeeDto.getId()));
        if (employee == null) {
            throw new EmployeeException("Employee not found");
        }
        BranchDto branchDto = branchService.fetchById(String.valueOf(employeeDto.getBranch().getId()));
        if(branchDto == null){
            throw new BranchException("Branch is not found");
        }
        if(employeeDto.getBranch() == null || employeeDto.getBranch().getId() <= 0){
            throw new BranchException("Branch of employee can't be null");
        }
        employee = modelMapper.map(employeeDto, Employee.class);
        employee = employeeDao.update(employee);
        employeeDto = modelMapper.map(employee, EmployeeDto.class);
        return employeeDto;
    }

    @Override
    public EmployeeDto delete(String id) throws Exception {
        Employee employee = employeeDao.fetchById(id);
        if (employee == null) {
            throw new EmployeeException("Employee not found");
        }
        employeeDao.delete(id);
        return EmployeeDto
                .builder()
                .id(Integer.parseInt(id))
                .build();
    }

    private boolean validateEmployeeAge(EmployeeDto employeeDto) throws EmployeeException {
        if (employeeDto == null) {
            throw new EmployeeException("Employee can't be null");
        }
        try {
            String nationalId = employeeDto.getNationalId();
            int year = Integer.parseInt("19" + nationalId.substring(1, 3));
            int month = Integer.parseInt(nationalId.substring(3, 5));
            int day = Integer.parseInt(nationalId.substring(5, 7));
            LocalDate birthdate = LocalDate.of(year, month, day);
            LocalDate now = LocalDate.now();
            int age = Period.between(birthdate, now).getYears();
            if (age != employeeDto.getAge()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void validateEmployeeInfo(EmployeeDto employeeDto) throws EmployeeException {
        if (!employeeDao.existsByNationalId(employeeDto.getNationalId())) {
            throw new EmployeeException("National id already exists");
        }
        if (!pattern.matcher(employeeDto.getNationalId()).matches()) {
            throw new EmployeeException("National Id should be in digit format only");
        }
        if (!validateEmployeeAge(employeeDto)) {
            throw new EmployeeException("Age is not valid");
        }
    }

}
