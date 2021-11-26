package com.taller.proyecto.model.api;

import java.util.List;

import com.taller.proyecto.model.Cotizacion;
import com.taller.proyecto.model.Producto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReequestCreateCotizacion {

	private Cotizacion cotizacion;
	private List<Producto> productos;
	
}
