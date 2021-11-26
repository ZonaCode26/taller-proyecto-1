package com.taller.proyecto.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller.proyecto.exception.ModeloNotFoundException;
import com.taller.proyecto.model.Cotizacion;
import com.taller.proyecto.model.DetalleCotizacion;
import com.taller.proyecto.model.Producto;
import com.taller.proyecto.model.Usuario;
import com.taller.proyecto.model.api.ReequestCreateCotizacion;
import com.taller.proyecto.repository.ICotizacionRepo;
import com.taller.proyecto.repository.IDetalleCotizacionRepo;
import com.taller.proyecto.repository.IProductoRepo;
import com.taller.proyecto.security.GlobalUsuarioSession;
import com.taller.proyecto.service.ICotizacionService;

@Service
public class CotizacionServiceImpl implements ICotizacionService{
	
	@Autowired
	private ICotizacionRepo repo;	
	
	@Autowired
	private IProductoRepo repoProd;	
	
	@Autowired
	private IDetalleCotizacionRepo repoDetCot;	

	
	@Override
	public Cotizacion findFirstById(Integer id) {
		// TODO Auto-generated method stub
		Cotizacion entity =  repo.findFirstById(id);
		
		if (entity==null) {
			throw new ModeloNotFoundException(String.format("Usuario no existe", id ));
		}
		entity.getUsuario().setPassword(null);
		entity.getUsuario().setEmail(null);		
		return entity;
	}

	@Override
	public List<Cotizacion> findAll() {
		List<Cotizacion> response = repo.findAll();
		
		
		response.forEach(x->{
			x.getUsuario().setPassword(null);
			x.getUsuario().setEmail(null);
		});
		
		 
		 return response;
	}

	@Transactional // (propagation = )
	@Override
	public Cotizacion createCotizacion(ReequestCreateCotizacion createCotizacion, GlobalUsuarioSession globalSession) {

		String codCotizacion = "COT-"+globalSession.getUserId()+ RandomStringUtils.randomAlphanumeric(20).toUpperCase();
		codCotizacion =codCotizacion.substring(0,20);
		
		
		
		
		Cotizacion cotizacion = Cotizacion.builder().estado("ACTIVO").fechaRegistro(LocalDate.now())
				.ruc(globalSession.getRuc()).usuario(Usuario.builder().idUsuario(globalSession.getUserId()).build())
				.codCotizacion(codCotizacion)
				.build();

		repo.save(cotizacion);

		List<Integer> listIds = new ArrayList<>();

		HashMap<Integer, Integer> datosParam = new HashMap<>();

		createCotizacion.getProductos().forEach(x -> {
			datosParam.put(x.getId(), x.getCantidad());
			listIds.add(x.getId());
		});

		List<DetalleCotizacion> listCotizacions = new ArrayList<>();

		List<Producto> getListProd = repoProd.findAllById(listIds);
		for (Producto producto : getListProd) {

			listCotizacions.add(DetalleCotizacion.builder().cantidad(datosParam.get(producto.getId()))
					.precioUnidad(producto.getPrecio()).producto(producto)
					.precioTotal(datosParam.get(producto.getId()) * producto.getPrecio()).cotizacion(cotizacion)
					.fechaRegistro(LocalDate.now()).build());

		}

		repoDetCot.saveAll(listCotizacions);

		cotizacion.getUsuario().setPassword(null);
		cotizacion.getUsuario().setEmail(null);

		return cotizacion;
	}

	@Override
	public Cotizacion findFirstByCode(String code) {
		Cotizacion entity =  repo.findFirstByCodCotizacion(code);
		
		if (entity==null) {
			throw new ModeloNotFoundException(String.format("Cotizacion no existe", code ));
		}
		entity.getUsuario().setPassword(null);
		entity.getUsuario().setEmail(null);		
		return entity;
	}


	

}
