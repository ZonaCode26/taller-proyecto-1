package com.taller.proyecto.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.proyecto.model.Departamento;
import com.taller.proyecto.model.Distrito;
import com.taller.proyecto.model.Provincia;
import com.taller.proyecto.repository.IDepartamentoRepo;
import com.taller.proyecto.repository.IDistritoRepo;
import com.taller.proyecto.repository.IProvinciaRepo;
import com.taller.proyecto.service.ILocalidadService;

@Service
public class LocalidadServiceImpl implements ILocalidadService{
	
	@Autowired
	private IDepartamentoRepo dtpRepo;

	@Autowired
	private IProvinciaRepo provRepo;

	@Autowired
	private IDistritoRepo distRepo;

	@Override
	public List<Departamento> findAllDepartamento() {
		// TODO Auto-generated method stub
		return dtpRepo.findAll();
	}

	@Override
	public List<Provincia> findAllProvincia() {
		// TODO Auto-generated method stub
		return provRepo.findAll();
	}

	@Override
	public List<Provincia> getProvinciaByIdDepartamento(Integer idDepartamento) {
		// TODO Auto-generated method stub
		return provRepo.findByIdDepartamento(idDepartamento);
	}

	@Override
	public List<Distrito> findAllDistrito() {
		// TODO Auto-generated method stub
		return distRepo.findAll();
	}

	@Override
	public List<Distrito> getDistritoByIdDepartamentoAndIdProvincia(Integer idDepartamento, Integer idProvincia) {
		// TODO Auto-generated method stub
		return distRepo.findByIdDepartamentoAndIdProvincia(idDepartamento, idProvincia);
	}	

	
	
	
	

}
