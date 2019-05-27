package com.delgadotrueba.api.repositories;


import static org.junit.jupiter.api.Assertions.*;

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
        this.employee1 = new Employee("David Manuel", "Delgado Conchiña", "dc@gmail.com");
        this.employeeRepository.save(this.employee1);
    }

	@Test
	void testFindByMobile() {
		Employee employeeBd = employeeRepository.findByEmail("dc@gmail.com").get();
		assertEquals(this.employee1, employeeBd);
	}
	  
}
