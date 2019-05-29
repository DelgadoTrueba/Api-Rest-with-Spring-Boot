package com.delgadotrueba.api.servicies;

import java.util.List;

import com.delgadotrueba.api.dtos.EmployeeDTO;
import com.delgadotrueba.api.orm.Employee;

public interface EmployeeService {
	
	public List<EmployeeDTO> findAll();
	
	public EmployeeDTO findById(int theId);
	
	public void save(EmployeeDTO theEmployeeDTO);
	
	public void deleteById(int theId);
}
