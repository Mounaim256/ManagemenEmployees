package com.spring.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.employees.model.EmployeesModel;

@Repository
public interface EmployeesRepository extends JpaRepository<EmployeesModel, Long>{
	EmployeesModel findByName(String name);
}
