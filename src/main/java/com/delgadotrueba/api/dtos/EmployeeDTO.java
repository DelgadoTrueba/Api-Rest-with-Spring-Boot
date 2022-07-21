package com.delgadotrueba.api.dtos;

import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.delgadotrueba.api.orm.Employee;

// Anotaciones
// Entrada = validación
// Salida = json

public class EmployeeDTO {
	
	private int id;
	
	@JsonInclude(Include.NON_NULL)
	private String firstName;
	
	private String lastName;
	
	@Pattern(regexp = com.delgadotrueba.api.dtos.validations.Pattern.EMAIL)	
	private String email;

	private String image;
		
	public EmployeeDTO() {
		 // Empty for framework
	}
	
	public EmployeeDTO(Employee employee) {
		this.id = employee.getId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.email = employee.getEmail();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "EmployeeDTO ["
					+ "id=" + id 
					+ ", firstName=" + firstName 
					+ ", lastName=" + lastName 
					+ ", email=" + email
				+ "]";
	}
	
	
}
