package com.delgadotrueba.api.orm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.ObjectUtils;

@Entity
@Table(name="employee")
public class Employee {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;

	@Column(name="image")
	private String image;
	
		
	// define constructors
	
	public Employee() {
		
	}

	public Employee(String firstName, String lastName, String email, String: image) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.image = image;
	}

	// define getter/setter
	
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

	// define tostring

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	public boolean equals(Object obj) {
		/*
		return this == obj || obj != null && getClass() == obj.getClass() 
				&& this.firstName.equals(((Employee) obj).firstName)
				&& this.lastName.equals(((Employee) obj).lastName)
				&& this.email.equals(((Employee) obj).email);
		 */
		
		if (this == obj) {
	      return true;
	    }
	    else if (obj == null || getClass() != obj.getClass()) {
	      return false;
	    }
	  
	    final Employee employee = (Employee) obj;
	  
	    return (
	    		this.firstName.equals(employee.firstName) &&
	    		this.lastName.equals(employee.lastName) &&
	    		this.email.equals(employee.email)	    		
	    		);

	}
	
	
}