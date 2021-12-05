package com.taller.proyecto.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.taller.proyecto.model.Archivo;
import com.taller.proyecto.model.Cotizacion;
import com.taller.proyecto.model.Producto;
import com.taller.proyecto.model.api.ReequestCreateCotizacion;
import com.taller.proyecto.model.api.RequestFilterCotizacion;
import com.taller.proyecto.model.api.ResponseDatatable;
import com.taller.proyecto.security.GlobalUsuarioSession;
import com.taller.proyecto.service.IArchivoService;
import com.taller.proyecto.service.ICotizacionService;

@RestController
@RequestMapping("/cotizacion")
@CrossOrigin("*")
public class CotizacionController {

	private static   Logger log =  LogManager.getLogger("CotizacionController-all");
	
	@Autowired
	private ICotizacionService service;
	
	@Autowired
	private IArchivoService serviceArchivo;

	
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
	
	
//	@GetMapping(value = "/generarReporte", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//	public ResponseEntity<byte[]> generarReporte() {
//		byte[] data = null;
//		data = service.generarReporte();
//		return new ResponseEntity<byte[]>(data, HttpStatus.OK);
//	}
//	
	
	@GetMapping(value = "/generar-pdf-cotizacion/{id}")
	public ResponseEntity<byte[]> generarPdfCotizacion(@PathVariable("id") Integer id) {
		
	
		return new ResponseEntity<byte[]>(service.generarPdfCotizacion(id), HttpStatus.OK);
	}
	
	@PostMapping(value = "/crear-cotizacion")
	public ResponseEntity<Cotizacion> creaarCotizacion(@RequestBody ReequestCreateCotizacion cotizacion,@RequestAttribute("globalUsuarioSession") GlobalUsuarioSession globalSession) {		
		return new ResponseEntity<Cotizacion>(service.createCotizacion(cotizacion, globalSession), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/datatable-filter")
	public ResponseEntity<ResponseDatatable<Cotizacion>> getDatatableFilter(@RequestBody RequestFilterCotizacion filter,@RequestAttribute("globalUsuarioSession") GlobalUsuarioSession globalSession ) {		
		
		List<Cotizacion> lista =  service.findAll();
		ResponseDatatable<Cotizacion> entity =  new ResponseDatatable<Cotizacion>(); 
		entity.setData(lista);
		entity.setDraw(0);
		entity.setRecordsTotal(lista.size());
		entity.setRecordsFiltered(0);
		
		return new ResponseEntity<ResponseDatatable<Cotizacion>>(entity, HttpStatus.OK);
				//.flatMap(c -> new ResponseEntity<Cotizacion>(c , HttpStatus.OK));	
		
	}
	
	@PostMapping(value = "/guardarArchivo", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Integer> guardarArchivo(@RequestParam("adjunto") MultipartFile file) throws IOException {
		int rpta = 0;

		Archivo ar = new Archivo();
		ar.setFiletype(file.getContentType());
		ar.setFilename(file.getOriginalFilename());
		ar.setValue(file.getBytes());

		rpta = serviceArchivo.guardar(ar);

		return new ResponseEntity<Integer>(rpta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/leerArchivo/{idCotizacion}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<byte[]> leerArchivo(@PathVariable("idCotizacion") Integer idCotizacion) throws IOException {
				
		byte[] arr = serviceArchivo.leerByCotizacion(idCotizacion); 

		return new ResponseEntity<byte[]>(arr, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/crear-pedido")
	public ResponseEntity<Cotizacion> crearPedido(@RequestBody Cotizacion cotizacion,@RequestAttribute("globalUsuarioSession") GlobalUsuarioSession globalSession) {		
		
		return new ResponseEntity<Cotizacion>(service.createPedido(cotizacion, globalSession), HttpStatus.OK);
	}
	
}
