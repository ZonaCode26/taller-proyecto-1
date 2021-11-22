package com.taller.proyecto.model.api;

import org.springframework.data.domain.PageRequest;

import com.taller.proyecto.model.Producto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestFilterProduct {

	private Producto producto;
	
	private PageRequest pageable;
	
}
