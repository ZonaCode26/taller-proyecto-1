package com.taller.proyecto.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "tb_producto")
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
	
	@Id
//	@SequenceGenerator(name = "tb_producto_id_seq", sequenceName = "tb_producto_id_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_producto_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	private String descripcion;
	
	private Double precio;
	
	private String imagen;
	
	private boolean estado;
	
	private LocalDate fechaRegistro;
	


}
