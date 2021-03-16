package com.spring.employees.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.anyString;

import com.spring.employees.model.EmployeesModel;
import com.spring.employees.service.EmployeesService;

class EmployeesControllerTest {
	
	@InjectMocks
	EmployeesController employeesController;

	@Mock
	EmployeesService employeesService;
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this );
	}

	@Test
	final void testGetEmployeeByName() {
		EmployeesModel employee = new EmployeesModel();
		employee.setId(1L);
		employee.setName("mounaim");
		employee.setDesignation("devlopeur");
		employee.setSalary(7000);
		
		when(employeesService.getEmployeeByName(anyString())).thenReturn(employee);
		
		EmployeesModel actualEmployee = employeesController.getEmployeeByName(anyString());
		
		assertNotNull(actualEmployee);
		assertEquals(employee.getName(), actualEmployee.getName());
	}

}
