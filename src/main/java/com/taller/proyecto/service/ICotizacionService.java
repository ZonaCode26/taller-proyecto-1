package com.taller.proyecto.service;

import java.util.List;

import com.taller.proyecto.model.Cotizacion;
import com.taller.proyecto.model.api.ReequestCreateCotizacion;
import com.taller.proyecto.security.GlobalUsuarioSession;

public interface ICotizacionService {

	Cotizacion findFirstById(Integer id);

	List<Cotizacion> findAll();

	Cotizacion createCotizacion(ReequestCreateCotizacion cotizacion, GlobalUsuarioSession globalSession);

	Cotizacion findFirstByCode(String  code);

}
