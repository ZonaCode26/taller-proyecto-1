package com.taller.proyecto.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "tb_cotizacion")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Cotizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String codCotizacion;
	
	@ManyToOne // FK
	@JoinColumn(name = "id_usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_usuario_cotizacion"))
	private Usuario usuario;
	
	private String ruc;
	
	private String estado;
	
	private Double totalBrutoCotizacion;
	
	private Double totalNetoCotizacion;
	
	private LocalDate fechaRegistro;
	
	
	
	@OneToMany(mappedBy = "cotizacion", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<DetalleCotizacion> detalleConsulta;
}
