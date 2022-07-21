package com.delgadotrueba.api.repositories;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.delgadotrueba.api.TestConfig;
import com.delgadotrueba.api.dao.EmployeeRepository;
import com.delgadotrueba.api.orm.Employee;

@TestConfig
class EmployeeRepositoryIT {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	private Employee employee1;
	
    @BeforeEach
    void seedDb() {
        this.employee1 = new Employee("David Manuel", "Delgado Conchiña", "dc@gmail.com", "AA");
        this.employeeRepository.save(this.employee1);
    }

	@Test
	void testFindById() {
		Optional<Employee> employee = employeeRepository.findById(this.employee1.getId());
		assertTrue(employee.isPresent());
		assertEquals(this.employee1, employee.get());
	}
	
	@Test
	void testFindByIdNotFound() {
		Optional<Employee> employee = employeeRepository.findById(9898989);
		assertFalse(employee.isPresent());
	}
	
	@Test
    void testReadAll() {
        assertTrue(this.employeeRepository.findAll().size() > 1);
    }
	  
}
