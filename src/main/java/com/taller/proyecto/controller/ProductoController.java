package com.taller.proyecto.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taller.proyecto.model.Producto;
import com.taller.proyecto.model.api.RequestFilterProduct;
import com.taller.proyecto.model.api.RequestFilterProducto;
import com.taller.proyecto.service.IProductoService;

@RestController
@RequestMapping("/producto")
@CrossOrigin
public class ProductoController {

	private static   Logger log =  LogManager.getLogger("ProductoController-all");
	
	@Autowired
	private IProductoService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Producto> getById(@PathVariable("id") Integer id) {
		return new ResponseEntity<Producto>(service.findFirstById(id), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Producto>> getAll() {		
		return new ResponseEntity<List<Producto>>(service.findAll(), HttpStatus.OK);
	}
	
	@PostMapping(value = "/filter")
	public ResponseEntity<List<Producto>> getAllFilter(@RequestBody RequestFilterProducto requestFilter) {		
		return new ResponseEntity<List<Producto>>(service.findAllWithPageable(requestFilter), HttpStatus.OK);
	}
	
	@PostMapping(value = "/filter-data")
	public ResponseEntity<Page<Producto>> getAllFilterPageable(@RequestBody RequestFilterProducto requestFilter) {		
		return new ResponseEntity<Page<Producto>>(service.findAllWithPageable2(requestFilter), HttpStatus.OK);
	}
	
	@GetMapping(value = "/find-all")
	public ResponseEntity<List<Producto>> getFindAllByIds(@RequestParam("ids") List<Integer> ids) {
		
//		List<Integer> listIds = new ArrayList<>();
		
//		ids.forEach(x->{
//			listIds.add(Integer.parseInt(x));
//		});		
		return new ResponseEntity<List<Producto>>(service.findAllByIds(ids), HttpStatus.OK);
	}
	
//	@PostMapping(value = "/v3/filter")
//	public ResponseEntity<Page<Producto>> getAllFilterPageable(@RequestBody RequestFilterProduct requestFilterProduct) {		
//		
//		PageRequest aa = null;
//		return new ResponseEntity<Page<Producto>>(service.findAllWithPageable2(requestFilterProduct.getProducto(),requestFilterProduct.getPageable()), HttpStatus.OK);
//	}
}
