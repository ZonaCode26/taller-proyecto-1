package com.taller.proyecto.service;

import java.util.List;

import com.taller.proyecto.model.Departamento;
import com.taller.proyecto.model.Distrito;
import com.taller.proyecto.model.Provincia;

public interface ILocalidadService {

	List<Departamento> findAllDepartamento();
	
	List<Provincia> findAllProvincia();
	
	List<Provincia> getProvinciaByIdDepartamento(Integer idDepartamento);
	
	List<Distrito> findAllDistrito();
	
	List<Distrito> getDistritoByIdDepartamentoAndIdProvincia(Integer idDepartamento, Integer idProvincia);
}
