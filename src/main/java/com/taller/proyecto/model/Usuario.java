package com.taller.proyecto.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;

	@Column(name = "ruc", nullable = false, unique = true)
	private String ruc;

	@Column(name = "razonSocial")
	private String razonSocial;


	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "codDto")
	private String codDto;

	@Column(name = "codProv")
	private String codProv;
	
	@Column(name = "codDist")
	private String codDist;
	
	@Column(name = "telefonoEmpresa")
	private String telefonoEmpresa;
	
	@Column(name = "contacto")
	private String contacto;
	
	@Column(name = "telefonoContacto")
	private String telefonoContacto;
	
	@Column(name = "celular")
	private String celular;
	
	@Column(name = "cargo")
	private String cargo;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;

//	@Column(name = "username", nullable = false, unique = true)
//	private String username;

	@Column(name = "clave", nullable = false)
	private String password;
	
	@Column(name = "estado", nullable = false)
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"), inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
	private List<Rol> roles;

	
}
