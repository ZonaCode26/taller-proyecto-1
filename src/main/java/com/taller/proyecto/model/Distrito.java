package com.taller.proyecto.model;

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
@Table(name = "tb_distrito")
@AllArgsConstructor
@NoArgsConstructor
public class Distrito {

	@Id
//	@SequenceGenerator(name = "tb_distrito_id_seq", sequenceName = "tb_distrito_id_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_distrito_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private Integer idProvincia;
	
	private Integer idDepartamento;
	
	private String codigo;
	
	private String nombre;
	
	private String codigoUbigeo;
}
