package com.delgadotrueba.api.data_servicies;

import java.util.ArrayList;
import java.util.List;

import com.delgadotrueba.api.orm.Employee;

public class DatabaseGraph {

	private List<Employee> employeeList;

	public DatabaseGraph() {
		this.employeeList = new ArrayList<Employee>();
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	 
	 
}
