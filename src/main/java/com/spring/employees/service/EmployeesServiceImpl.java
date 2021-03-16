package com.spring.employees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.employees.model.EmployeesModel;
import com.spring.employees.repository.EmployeesRepository;

@Service
public class EmployeesServiceImpl implements EmployeesService {

	@Autowired
	private EmployeesRepository employeesRepository;

	@Override
	public List<EmployeesModel> getAllEmployees() {
		// TODO Auto-generated method stub
		return this.employeesRepository.findAll();
	}

	@Override
	public EmployeesModel getEmployee(Long id) {
		// TODO Auto-generated method stub
		return this.employeesRepository.findById(id).get();
	}

	@Override
	public EmployeesModel saveEmployee(EmployeesModel employeesModel) {
		// TODO Auto-generated method stub
		return this.employeesRepository.save(employeesModel);
	}

	@Override
	public void deleteEmploye(Long id) {
		// TODO Auto-generated method stub
		this.employeesRepository.deleteById(id);
	}

	@Override
	public EmployeesModel getEmployeeByName(String name) {
		// TODO Auto-generated method stub

		EmployeesModel employee = this.employeesRepository.findByName(name);
		if (employee == null)
			throw new UsernameNotFoundException("user not found with username : " + name);

		return employee;
	}
}
