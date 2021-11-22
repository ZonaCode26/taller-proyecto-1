package com.taller.proyecto.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.taller.proyecto.model.Producto;

public interface IProductoRepo extends IGenericRepo<Producto, Integer>  {

	Producto findFirstById(Integer id);
	
	List<Producto> findAll();
		
	
}
