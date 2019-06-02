package com.delgadotrueba.api.data_servicies;

import java.util.ArrayList;
import java.util.List;

import com.delgadotrueba.api.orm.Employee;
import com.delgadotrueba.api.orm.User;

public class DatabaseGraph {

	private List<Employee> employeeList;
	
	private List<User> userList;

	public DatabaseGraph() {
		this.employeeList = new ArrayList<Employee>();
		this.userList = new ArrayList<User>();
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
}
