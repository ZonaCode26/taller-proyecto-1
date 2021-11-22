package com.taller.proyecto.repository;

import java.util.List;

import com.taller.proyecto.model.Departamento;

public interface IDepartamentoRepo extends IGenericRepo<Departamento, Integer>  {

	Departamento findFirstById(Integer id);
	
	List<Departamento> findAll();
}
