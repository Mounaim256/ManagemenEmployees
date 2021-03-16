package com.spring.employees.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

//import java.util.Random;

import static org.mockito.Mockito.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.employees.model.EmployeesModel;
import com.spring.employees.repository.EmployeesRepository;

class EmployeesServiceImplTest {
	
	@InjectMocks
	EmployeesServiceImpl employeesServiceImpl;

	@Mock
	EmployeesRepository employeesRepository;
	
	
	@BeforeEach
	void setUp() throws Exception {
	 MockitoAnnotations.initMocks(this);
	}

	@Test
	final void testGetEmployee() {
		//Long id = new Random().nextLong();
		
		EmployeesModel employee = new EmployeesModel();
		employee.setId(1L);
		employee.setName("mounaim");
		employee.setDesignation("devlopeur");
		employee.setSalary(7000);
		
		when(employeesRepository.findByName(anyString())).thenReturn(employee);
		
		EmployeesModel emp = employeesServiceImpl.getEmployeeByName(anyString());
		
		assertNotNull(emp); 
	    assertEquals("mounaim", emp.getName());
	}
	
	
	@Test
	final void testGetEmployee_UsernameNotFoundException() {
		
		when(employeesRepository.findByName(anyString())).thenReturn(null);
		
		assertThrows(UsernameNotFoundException.class, 
				() -> {
					employeesServiceImpl.getEmployeeByName(anyString());
					});
	}

}
