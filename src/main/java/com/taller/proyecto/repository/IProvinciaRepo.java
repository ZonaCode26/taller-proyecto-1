package com.taller.proyecto.repository;

import java.util.List;

import com.taller.proyecto.model.Provincia;

public interface IProvinciaRepo extends IGenericRepo<Provincia, Integer>  {

	Provincia findFirstById(Integer id);
	
	List<Provincia> findAll();
	
	List<Provincia> findByCodigo(String codigo);
	
	List<Provincia> findByIdDepartamento(Integer idDepartamento);
}
