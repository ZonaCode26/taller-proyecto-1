package com.taller.proyecto.repository;

import com.taller.proyecto.model.Cotizacion;

public interface ICotizacionRepo extends IGenericRepo<Cotizacion, Integer>  {

	Cotizacion findFirstById(Integer id);
	Cotizacion findFirstByCodCotizacion(String codCotizacion);
	
}
