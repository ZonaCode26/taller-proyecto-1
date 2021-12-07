package com.taller.proyecto.security;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	@NotNull(message = "no debe ser nulo.")
	@NotEmpty(message = "no debe ser vacio.")
	
	//@Max(50)
	//@Min( 5)
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = "no cumple con el formato de correo.")
	private String username;
	
	@NotNull(message = "no debe ser nulo.")
	@NotEmpty(message = "no debe ser vacio.")
	//@Max(value = 20,message = "debe ser como maximo 50 caracteres.")
//	@Min(value = 4,message = "debe ser como minimo 4 caracteres.")
	private String password;
	
	private String acceso;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAcceso() {
		return acceso;
	}

	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	
	
}