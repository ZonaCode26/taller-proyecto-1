package com.taller.proyecto.repository;

import java.util.Optional;

import com.taller.proyecto.model.Archivo;

public interface IArchivoRepo extends IGenericRepo<Archivo, Integer>{

	Optional<Archivo> findByIdCotizacion(Integer idCotizacion);

}
