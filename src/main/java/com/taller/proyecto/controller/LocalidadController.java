package com.taller.proyecto.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.proyecto.model.Departamento;
import com.taller.proyecto.model.Distrito;
import com.taller.proyecto.model.Provincia;
import com.taller.proyecto.service.ILocalidadService;

@RestController
@RequestMapping("/localidad")
@CrossOrigin("*")
public class LocalidadController {

	private static   Logger log =  LogManager.getLogger("LocalidadController-all");
	
	@Autowired
	private ILocalidadService service;
	
			
	@GetMapping(value = "/departamento")
	public ResponseEntity<List<Departamento>> getAllDepartamento() {		
		return new ResponseEntity<List<Departamento>>(service.findAllDepartamento(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/provincia")
	public ResponseEntity<List<Provincia>> getAllProvincia() {		
		return new ResponseEntity<List<Provincia>>(service.findAllProvincia(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/provincia/dpto/{id}")
	public ResponseEntity<List<Provincia>> getProvinciaByIdDepartamento(@PathVariable("id") Integer id) {
		return new ResponseEntity<List<Provincia>>(service.getProvinciaByIdDepartamento(id), HttpStatus.OK);
	}
	
	@GetMapping(value = "/distrito")
	public ResponseEntity<List<Distrito>> getAllDistrito() {		
		return new ResponseEntity<List<Distrito>>(service.findAllDistrito(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/distrito//dpto/{idDpto}/prov/{idprov}")
	public ResponseEntity<List<Distrito>> getDistritoByIdDepartamentoAndIdProvincia(@PathVariable("idDpto") Integer idDpto, @PathVariable("idprov") Integer idprov) {		
		return new ResponseEntity<List<Distrito>>(service.getDistritoByIdDepartamentoAndIdProvincia(idDpto,idprov), HttpStatus.OK);
	}
}
