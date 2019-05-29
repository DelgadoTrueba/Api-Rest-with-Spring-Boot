package com.delgadotrueba.api.servicies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delgadotrueba.api.dao.EmployeeRepository;
import com.delgadotrueba.api.dtos.EmployeeDTO;
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
		
		if (result.isPresent()) {
			theEmployee = new EmployeeDTO(result.get());
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - " + theId);
		}
		
		return theEmployee;
	}

	@Override
	public void save(EmployeeDTO theEmployeeDTO) {
		
		Employee theEmployee = new Employee(
											theEmployeeDTO.getFirstName(),
											theEmployeeDTO.getLastName(),
											theEmployeeDTO.getEmail()
											);
		
		employeeRepository.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}
}
