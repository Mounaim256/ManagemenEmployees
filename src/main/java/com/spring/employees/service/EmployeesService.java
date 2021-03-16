package com.spring.employees.service;

import java.util.List;

import com.spring.employees.model.EmployeesModel;

public interface EmployeesService {
	List<EmployeesModel> getAllEmployees();
	EmployeesModel getEmployee(Long id);
	EmployeesModel getEmployeeByName(String name);
	EmployeesModel saveEmployee(EmployeesModel employeesModel);
	void deleteEmploye(Long id);
}
