package com.taller.proyecto.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.taller.proyecto.exception.ModeloNotFoundException;
import com.taller.proyecto.model.Producto;
import com.taller.proyecto.model.api.RequestFilterProducto;
import com.taller.proyecto.repository.IProductoRepo;
import com.taller.proyecto.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoRepo repo;	

	
	@Override
	public Producto findFirstById(Integer id) {
		// TODO Auto-generated method stub
		Producto entity =  repo.findFirstById(id);
		
		if (entity==null) {
			throw new ModeloNotFoundException(String.format("Usuario no existe", id ));
		}
		
		return entity;
	}

	@Override
	public List<Producto> findAll() {
		return repo.findAll();
	}

	@Override
	public List<Producto> findAllWithPageable(RequestFilterProducto entityFilter) {
		Producto entity =  filterProducto(entityFilter);
		Pageable pageable = pageableFilter(entityFilter);
		
		ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
			      .withMatcher("nombre", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
			      .withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		 //ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
		 
		Page<Producto> pageProduct = repo.findAll(Example.of(entity,customExampleMatcher), pageable);
		return  pageProduct.getContent();
	}

	private Pageable pageableFilter(RequestFilterProducto entityFilter) {
		
		Sort sortParam = null;
		
		if (StringUtils.trimToEmpty(entityFilter.getFilter().getSort()).isEmpty() ) {
			entityFilter.getFilter().setSort("desc");
		}
		
		if (StringUtils.trimToEmpty(entityFilter.getFilter().getSortBy()).isEmpty() ) {
			entityFilter.getFilter().setSort("id");
		}
		
		if ("desc".equals(entityFilter.getFilter().getSort())) {
			sortParam =  Sort.by(entityFilter.getFilter().getSortBy()).descending();
		}else {
			sortParam =  Sort.by(entityFilter.getFilter().getSortBy()).ascending();
		}
		
		return PageRequest.of(entityFilter.getFilter().getPageNo(), 
				entityFilter.getFilter().getPageSize(), 
				sortParam);
	}

	private Producto filterProducto(RequestFilterProducto entityFilter) {
		return Producto.builder()
				.descripcion(entityFilter.getDescripcion())
				.estado(entityFilter.isEstado())
				.nombre(entityFilter.getNombre())
				.precio(entityFilter.getPrecio())
				.build();
	}

	@Override
	public Page<Producto> findAllWithPageable2(Producto entity, Pageable pageable) {
		Page<Producto> pageProduct = repo.findAll(Example.of(entity), pageable);
		return pageProduct;
	}

	@Override
	public Page<Producto> findAllWithPageable2(RequestFilterProducto entityFilter) {
		Producto entity =  filterProducto(entityFilter);
		Pageable pageable = pageableFilter(entityFilter);
		
		ExampleMatcher customExampleMatcher = ExampleMatcher.matching()
			      .withMatcher("nombre", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
			      .withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
		
		 //ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
		 
		Page<Producto> pageProduct = repo.findAll(Example.of(entity,customExampleMatcher), pageable);
		return  pageProduct;
	}
	
	

}
