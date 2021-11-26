package com.taller.proyecto.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "tb_detalle_cotizacion")
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCotizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_cotizacion", nullable = false, foreignKey = @ForeignKey(name = "FK_cotizacion_detalle"))
	private Cotizacion cotizacion;
	
	@ManyToOne // FK
	@JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name = "FK_producto_detalle"))
	private Producto producto;
//	private Integer idProducto;
	
	private Integer cantidad;
	
	private Double precioUnidad;
	
	private Double precioTotal;
	
	private LocalDate fechaRegistro;
	

}
