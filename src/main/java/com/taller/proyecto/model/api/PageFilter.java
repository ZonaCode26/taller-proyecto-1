package com.taller.proyecto.model.api;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PageFilter {

	private Integer pageNo;
	private Integer pageSize;
	private String  sort;
	private String  sortBy;
		
}
