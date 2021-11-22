package com.taller.proyecto.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taller.proyecto.model.Producto;
import com.taller.proyecto.model.api.RequestFilterProducto;

public interface IProductoService {

	Producto findFirstById(Integer id);
	
	List<Producto> findAll();
	
	List<Producto> findAllWithPageable(RequestFilterProducto entity);
	
	Page<Producto> findAllWithPageable2(RequestFilterProducto entity);
	
	Page<Producto> findAllWithPageable2(Producto entity, Pageable pageable);
	
}
