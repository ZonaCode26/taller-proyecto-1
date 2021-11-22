package com.taller.proyecto.repository;

import java.util.List;

import com.taller.proyecto.model.Distrito;

public interface IDistritoRepo extends IGenericRepo<Distrito, Integer> {

	Distrito findFirstById(Integer id);

	List<Distrito> findAll();

	List<Distrito> findByCodigo(String codigo);

	List<Distrito> findByIdDepartamento(Integer idDepartamento);

	List<Distrito> findByIdProvincia(Integer idProvincia);

	List<Distrito> findByIdDepartamentoAndIdProvincia(Integer idDepartamento, Integer idProvincia);
}
