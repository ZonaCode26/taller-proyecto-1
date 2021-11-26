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
@Table(name = "tb_departamento")
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

	@Id
//	@SequenceGenerator(name = "tb_departamento_id_seq", sequenceName = "tb_departamento_id_seq", allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_departamento_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	private String codigo;
	
	private String nombre;
	
}
