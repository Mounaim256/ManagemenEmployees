package com.spring.employees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employees.model.EmployeesModel;
import com.spring.employees.service.EmployeesService;

@RestController
@RequestMapping("/api/v1/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeesController {
	
	@Autowired
	private EmployeesService employeesService;

	@GetMapping({"","/"})
	public List<EmployeesModel> getAllEmployees(){
		return this.employeesService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public EmployeesModel getEmployee(@PathVariable Long id) {
		return this.employeesService.getEmployee(id);
	}
	
	
	@GetMapping("/{name}")
	public EmployeesModel getEmployeeByName(@PathVariable String name) {
		return this.employeesService.getEmployeeByName(name);
	}
	
	@PostMapping({"","/"})
	public EmployeesModel saveEmployee(@RequestBody EmployeesModel employeesModel) {
		return this.employeesService.saveEmployee(employeesModel);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		 this.employeesService.deleteEmploye(id);
	}
}
