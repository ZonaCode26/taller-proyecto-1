package com.taller.proyecto.exception;

import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.taller.proyecto.utils.Variables;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler{
	
	private   Logger log =  LogManager.getLogger("clientes-error");
	
	
	String mensaje = "";
	
	@Autowired
	private Variables var;
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionResponseCustom> runtimeException(RuntimeException ex, WebRequest request) {
		ExceptionResponseCustom exc = new ExceptionResponseCustom(LocalDateTime.now(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage(),"","");
		log = getLooger(request);
		log.error(exc.toString());
		return new ResponseEntity<ExceptionResponseCustom>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponseCustom> manejarTodasExcepciones(Exception ex, WebRequest request){		
		
		ExceptionResponseCustom exc = new ExceptionResponseCustom(LocalDateTime.now(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage(),var.GENERIC_COD,var.GENERIC_MSG);
		log = getLooger(request);
		log.error(exc.toString());
		return new ResponseEntity<ExceptionResponseCustom>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(ModeloNotFoundException.class)
	public final ResponseEntity<ExceptionResponseCustom> manejarModeloException(ModeloNotFoundException ex, WebRequest request){
		ExceptionResponseCustom exc = new ExceptionResponseCustom(LocalDateTime.now(),String.valueOf(HttpStatus.NOT_FOUND), ex.getMessage(),var.MODEL_NOT_FOUND_COD,var.MODEL_NOT_FOUND_MSG);

		log = getLooger(request);
		log.error(exc.toString());
		return new ResponseEntity<ExceptionResponseCustom>(exc, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public final ResponseEntity<ExceptionResponseCustom> IllegalArgumentException(IllegalArgumentException ex, WebRequest request){
		
		ExceptionResponseCustom exc = new ExceptionResponseCustom(LocalDateTime.now(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage(), "", "");
		log = getLooger(request);
		log.error(exc.toString());
		return new ResponseEntity<ExceptionResponseCustom>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NumberFormatException.class)
	public final ResponseEntity<ExceptionResponseCustom> NumberFormatException(NumberFormatException ex, WebRequest request){
		
		ExceptionResponseCustom exc = new ExceptionResponseCustom(LocalDateTime.now(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage(), "", "");
		log = getLooger(request);
		log.error(exc.toString());
		return new ResponseEntity<ExceptionResponseCustom>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<ExceptionResponseCustom> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){		
		ExceptionResponseCustom exc = new ExceptionResponseCustom(LocalDateTime.now(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage(), var.BAD_ARGUMENT_COD, var.BAD_ARGUMENT_MSG);
		log = getLooger(request);
		log.error(exc.toString());
		return new ResponseEntity<ExceptionResponseCustom>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
	public final ResponseEntity<ExceptionResponseCustom> BadCredentialsException(org.springframework.security.authentication.BadCredentialsException ex, WebRequest request){
		
		ExceptionResponseCustom exc = new ExceptionResponseCustom(LocalDateTime.now(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage(), "", "");
		log =  LogManager.getLogger("logins-all");
		log.info(exc.toString());
		return new ResponseEntity<ExceptionResponseCustom>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public final ResponseEntity<ExceptionResponseCustom> UsernameNotFoundException(UsernameNotFoundException ex, WebRequest request){
		
		ExceptionResponseCustom exc = new ExceptionResponseCustom(LocalDateTime.now(),String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), ex.getMessage(), "", "");
		log =  LogManager.getLogger("logins-all");
		log.info(exc.toString());
		return new ResponseEntity<ExceptionResponseCustom>(exc, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
	
	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ex.getBindingResult().getAllErrors().forEach(e -> {
			mensaje += e.getDefaultMessage().toString() + "\n";
		});
		
		ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		log = getLooger(request);
		log.error(er.toString());		
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}

	
	private Logger getLooger(WebRequest webRequest) {
		if (webRequest.toString().contains("/clientes/")) {
			return LogManager.getLogger("clientes-error");
		}else if (webRequest.toString().contains("/productos/")) {
			return LogManager.getLogger("productos-error");
		}else if (webRequest.toString().contains("/ventas/")) {
			return LogManager.getLogger("ventas-api");
		}else if (webRequest.toString().contains("/detalleventas/")) {
			return LogManager.getLogger("detalleventas-api");
		}		
		return LogManager.getLogger("clientes-error");		
	}
	
	

}
