package com.delgadotrueba.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delgadotrueba.api.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	// that's it ... no need to write any code LOL!
	
}
