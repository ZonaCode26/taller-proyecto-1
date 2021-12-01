package com.taller.proyecto.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionResponseCustom {

	private LocalDateTime fecha;
	private String httpStatus;
	private String message;
	private String code;
	private String backendMesage;
	private List<String> errors;
	
	public ExceptionResponseCustom(LocalDateTime fecha, String httpStatus, String message, String code,
			String backendMesage) {
		this.fecha = fecha;
		this.httpStatus = httpStatus;
		this.message = message;
		this.code = code;
		this.backendMesage = backendMesage;
	}
	
	public ExceptionResponseCustom(LocalDateTime fecha, String httpStatus, String message, String code,
			String backendMesage,List<String> errors ) {
		this.fecha = fecha;
		this.httpStatus = httpStatus;
		this.message = message;
		this.code = code;
		this.backendMesage = backendMesage;
		this.errors = errors;
	}
	
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public String getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBackendMesage() {
		return backendMesage;
	}
	public void setBackendMesage(String backendMesage) {
		this.backendMesage = backendMesage;
	}
	public List<String> getErrors() {
		return errors;
	}
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		builder.append("ExceptionResponseCustom [httpStatus=");
		builder.append(httpStatus);
		builder.append(", message=");
		builder.append(message);
		builder.append(", code=");
		builder.append(code);
		builder.append(", backendMesage=");
		builder.append(backendMesage);
		builder.append(", fecha=");
		builder.append(fecha);
		builder.append("]");
		return builder.toString();
	}

}
