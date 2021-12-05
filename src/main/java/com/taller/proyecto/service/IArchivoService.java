package com.taller.proyecto.service;

import com.taller.proyecto.model.Archivo;

public interface IArchivoService {

	int guardar(Archivo archivo);
	byte[] leerArchivo(Integer idArchivo);
	byte[] leerByCotizacion(Integer idCotizacion);
	
}
