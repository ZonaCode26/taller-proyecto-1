package com.taller.proyecto.model.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDatatable<T> {

	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private List<T> data;
}
