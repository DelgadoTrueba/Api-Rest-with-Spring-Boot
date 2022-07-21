package com.delgadotrueba.api.business_controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delgadotrueba.api.dao.EmployeeRepository;
import com.delgadotrueba.api.dtos.EmployeeDTO;
import com.delgadotrueba.api.exceptions.ConflictException;
import com.delgadotrueba.api.orm.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository = theEmployeeRepository;
	}
	
	@Override
	public List<EmployeeDTO> findAll() {
		List<Employee> employees = employeeRepository.findAll();
		
		List<EmployeeDTO> employeesDTO = new ArrayList<EmployeeDTO>();
		for(Employee employee : employees) {
			employeesDTO.add(new EmployeeDTO(employee));
		}
		
		return employeesDTO;
	}

	@Override
	public EmployeeDTO findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		
		EmployeeDTO theEmployee = null;
		
		if (!result.isPresent()) throw new ConflictException("Did not find employee id - " + theId);
			
		theEmployee = new EmployeeDTO(result.get());
		
		return theEmployee;
	}

	@Override
	public EmployeeDTO save(EmployeeDTO theEmployeeDTO) {
		
		Employee theEmployee = new Employee(
											theEmployeeDTO.getFirstName(),
											theEmployeeDTO.getLastName(),
											theEmployeeDTO.getEmail(),
											theEmployeeDTO.getImage()
											);
		
		employeeRepository.save(theEmployee);
		
		return new EmployeeDTO(theEmployee);
	}

	public EmployeeDTO update(EmployeeDTO theEmployeeDTO) {
		
		//puede lanzar una excepción
		Employee theEmployee = this.employeeRepository.findById(theEmployeeDTO.getId()).get();
		
		theEmployee.setFirstName(theEmployeeDTO.getFirstName());
		theEmployee.setLastName(theEmployeeDTO.getLastName());
		theEmployee.setEmail(theEmployeeDTO.getEmail());
		theEmployee.setImage(theEmployeeDTO.getImage());
		
		this.employeeRepository.save(theEmployee);
		
		return new EmployeeDTO(theEmployee);
	}
	
	@Override
	public void deleteById(int theId) {
		
		Optional<Employee> tempEmployee = employeeRepository.findById(theId);
				
		if (!tempEmployee.isPresent()) 
			throw new ConflictException("Did not find employee id - " + theId);
		
		employeeRepository.deleteById(theId);
		
	}
}
