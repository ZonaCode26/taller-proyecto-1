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
@Table(name = "tb_provincia")
@AllArgsConstructor
@NoArgsConstructor
public class Provincia {

	@Id
//	@SequenceGenerator(name = "tb_provincia_id_seq", sequenceName = "tb_provincia_id_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_provincia_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer idDepartamento;
	
	private String codigo;
	
	private String nombre;
}
