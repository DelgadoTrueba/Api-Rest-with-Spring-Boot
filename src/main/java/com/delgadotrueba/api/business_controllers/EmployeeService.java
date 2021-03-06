package com.delgadotrueba.api.business_controllers;

import java.util.List;

import com.delgadotrueba.api.dtos.EmployeeDTO;
import com.delgadotrueba.api.orm.Employee;

public interface EmployeeService {
	
	public List<EmployeeDTO> findAll();
	
	public EmployeeDTO findById(int theId);
	
	public EmployeeDTO save(EmployeeDTO theEmployeeDTO);
	
	public EmployeeDTO update(EmployeeDTO theEmployeeDTO);
	
	public void deleteById(int theId);
}
