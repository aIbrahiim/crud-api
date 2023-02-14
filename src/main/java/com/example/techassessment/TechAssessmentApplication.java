package com.example.techassessment;

import com.example.techassessment.dao.EmployeeDao;
import com.example.techassessment.dao.EmployeeDaoImpl;
import com.example.techassessment.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TechAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechAssessmentApplication.class, args);

	}

}
