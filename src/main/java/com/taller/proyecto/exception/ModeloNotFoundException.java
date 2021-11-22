package com.taller.proyecto.exception;

public class ModeloNotFoundException extends RuntimeException{

	public ModeloNotFoundException(String mensaje) {
		super(mensaje);
	}
}
