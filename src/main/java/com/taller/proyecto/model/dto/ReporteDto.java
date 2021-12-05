package com.taller.proyecto.model.dto;

public class ReporteDto {
	private int id;
	private String nombre;
	private String descripcion;
	private int cantidad;
	private double precio;
	private double preciototal;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getPreciototal() {
		return preciototal;
	}
	public void setPreciototal(double preciototal) {
		this.preciototal = preciototal;
	}


	

}
