package com.delgadotrueba.api.dtos;

public class TokenOutputDTO {
	
	private String token;

	public TokenOutputDTO() {
	    // Empty for framework
	}
	
	public TokenOutputDTO(String token) {
	    this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "TokenOutputDto{" +
					"token='" + token + '\'' +
				'}';
	}
}
