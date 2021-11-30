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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taller.proyecto.model.Cotizacion;
import com.taller.proyecto.model.api.ReequestCreateCotizacion;
import com.taller.proyecto.security.GlobalUsuarioSession;
import com.taller.proyecto.service.ICotizacionService;

@RestController
@RequestMapping("/cotizacion")
@CrossOrigin("*")
public class CotizacionController {

	private static   Logger log =  LogManager.getLogger("CotizacionController-all");
	
	@Autowired
	private ICotizacionService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cotizacion> getById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Cotizacion>(service.findFirstById(id), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Cotizacion>> getAll() {		
		return new ResponseEntity<List<Cotizacion>>(service.findAll(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Cotizacion> saveCotizacion(@RequestBody ReequestCreateCotizacion cotizacion,@RequestAttribute("globalUsuarioSession") GlobalUsuarioSession globalSession) {		
		return new ResponseEntity<Cotizacion>(service.createCotizacion(cotizacion, globalSession), HttpStatus.OK);
	}
	
	@GetMapping(value = "/code/{id}")
	public ResponseEntity<Cotizacion> getByCode(@PathVariable("id") String id) {
		return new ResponseEntity<Cotizacion>(service.findFirstByCode(id), HttpStatus.OK);
	}
}
