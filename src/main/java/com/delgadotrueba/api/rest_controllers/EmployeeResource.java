package com.delgadotrueba.api.rest_controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.delgadotrueba.api.business_controllers.EmployeeService;
import com.delgadotrueba.api.dtos.EmployeeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("employees")
public class EmployeeResource {

	@Autowired
	private EmployeeService employeeService;
	
	// expose "/employees" and return list of employees
	@GetMapping()
	public List<EmployeeDTO> findAll() {
		return employeeService.findAll();
	}

	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/{employeeId}")
	public EmployeeDTO getEmployee(@PathVariable int employeeId) {
		
		EmployeeDTO theEmployee = employeeService.findById(employeeId);
			
		return theEmployee;
	}
	
	// add mapping for POST /employees - add new employee
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping()
	public EmployeeDTO addEmployee(@Valid @RequestBody EmployeeDTO theEmployee) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item ... instead of update
		
		theEmployee.setId(0);
		
		theEmployee = employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	// add mapping for PUT /employees - update existing employee
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping()
	public EmployeeDTO updateEmployee(@Valid @RequestBody EmployeeDTO theEmployee) {
		
		theEmployee = employeeService.update(theEmployee);
		
		return theEmployee;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{employeeId}")
	public void deleteEmployee(@PathVariable int employeeId) {
		
		employeeService.deleteById(employeeId);
		
	}
	
}