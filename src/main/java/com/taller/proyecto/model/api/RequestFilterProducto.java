package com.taller.proyecto.model.api;

import lombok.Data;

@Data
public class RequestFilterProducto {

	private String nombre;
	
	private String descripcion;
	
	private Double precio;
	
	private boolean estado;
	
	private PageFilter filter;
	
}
