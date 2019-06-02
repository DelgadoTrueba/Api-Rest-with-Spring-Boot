package com.delgadotrueba.api.orm;

public enum Role {
	ADMIN, MANAGER, OPERATOR, CUSTOMER, AUTHENTICATED;

	public String roleName() {
		return "ROLE_" + this.toString();
    }
}
