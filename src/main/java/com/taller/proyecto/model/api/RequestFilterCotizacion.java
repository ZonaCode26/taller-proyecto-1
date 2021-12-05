package com.taller.proyecto.model.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestFilterCotizacion {
	private String estado;
	private String fechaIni;
	private String fechaFin;
	private String numcotizacion;
	private String nombrecliente;
	private String dni;
	private String ruc;

}
