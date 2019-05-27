package com.delgadotrueba.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.delgadotrueba.api.orm.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmail(String email);
	
}
