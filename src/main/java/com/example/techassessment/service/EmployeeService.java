package com.example.techassessment.service;

import com.example.techassessment.dao.EmployeeDao;
import com.example.techassessment.model.Employee;
import com.example.techassessment.model.dto.EmployeeDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.List;

public interface EmployeeService {

       public EmployeeDto addEmployee(EmployeeDto employeeDto);
       public EmployeeDto fetchById(String id) throws Exception;
       public List<EmployeeDto> fetchAll(int page, int size);
       public EmployeeDto update(EmployeeDto employeeDto) throws Exception;
       public EmployeeDto delete(String id) throws Exception;
}
