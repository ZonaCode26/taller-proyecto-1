package com.taller.proyecto.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@Table(name = "tb_archivo")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Archivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idArchivo;

	@Column(name = "filename", length = 50)
	private String filename;

	@Column(name = "filetype", length = 15)
	private String filetype;

	@Column(name = "contenido")
	private byte[] value;
	
	@Column(name = "id_cotizacion")
	private Integer idCotizacion;
//
//	public Integer getIdArchivo() {
//		return idArchivo;
//	}
//
//	public void setIdArchivo(Integer idArchivo) {
//		this.idArchivo = idArchivo;
//	}
//
//	public String getFilename() {
//		return filename;
//	}
//
//	public void setFilename(String filename) {
//		this.filename = filename;
//	}
//
//	public String getFiletype() {
//		return filetype;
//	}
//
//	public void setFiletype(String filetype) {
//		this.filetype = filetype;
//	}
//
//	public byte[] getValue() {
//		return value;
//	}
//
//	public void setValue(byte[] value) {
//		this.value = value;
//	}

}
