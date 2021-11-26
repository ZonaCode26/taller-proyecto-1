package com.taller.proyecto.repository;

import com.taller.proyecto.model.DetalleCotizacion;

public interface IDetalleCotizacionRepo extends IGenericRepo<DetalleCotizacion, Integer>  {

	DetalleCotizacion findFirstById(Integer id);
	
}
