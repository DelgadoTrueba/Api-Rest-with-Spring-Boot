package com.delgadotrueba.api.servicies;

import java.util.List;

import com.delgadotrueba.api.orm.Employee;

public interface EmployeeService {
	
	public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
}
