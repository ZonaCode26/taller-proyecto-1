package com.taller.proyecto.utils;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RequestValidator {

	@Autowired
	private Validator validador;

	public void doValidate(Object bean) {
		
		Set<ConstraintViolation<Object>> result = validador.validate(bean);
		if (!result.isEmpty()) {
			StringBuilder sb = new StringBuilder("Bean state is invalid: ");
			for (Iterator<ConstraintViolation<Object>> it = result.iterator(); it.hasNext();) {
				ConstraintViolation<Object> violation = it.next();
				
				sb.append(violation.getPropertyPath()).append(" - ").append(violation.getMessage());
				if (it.hasNext()) {
					sb.append("; ");
				}
			}
			throw new BeanInitializationException(sb.toString());
		}
	}
	
	public void validate(Object obj) {
	    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	    //Validator validator = factory.getValidator();
 
	    Set<ConstraintViolation<Object>> violations = validador.validate(obj);
	    if (!violations.isEmpty()) {
	      throw new ConstraintViolationException(violations);
	    }
	  }
	
	
//	public static <T> ErrorDTO getConstraintViolationErrorDTO(Set<ConstraintViolation<T>> violations) {
//	    ErrorDTO errorDTO = new ErrorDTO();
//	    errorDTO.setDescription("Validation Error");
//	    errorDTO.setMessage("Bad Request");
//	    errorDTO.setCode(400l);
//	    errorDTO.setMoreInfo("");
//	    List<ErrorListItemDTO> errorListItemDTOs = new ArrayList<>();
//	    for (ConstraintViolation violation : violations) {
//	        ErrorListItemDTO errorListItemDTO = new ErrorListItemDTO();
//	        errorListItemDTO.setCode(400 + "_" + violation.getPropertyPath());
//	        errorListItemDTO.setMessage(violation.getPropertyPath() + ": " + violation.getMessage());
//	        errorListItemDTOs.add(errorListItemDTO);
//	    }
//	    errorDTO.setError(errorListItemDTOs);
//	    return errorDTO;
//	}
	
	
//	public void validate(Object obj) {
//		
//		if (obj == null) {
//			return  Mono.error(new IllegalArgumentException());			
//		}
//
//		Set<ConstraintViolation<T>> violations = this.validador.validate(obj);
//		
//		
//		if (violations == null || violations.isEmpty()) {
//			return Mono.just(obj);
//		}
//
//		return Mono.error(new ConstraintViolationException(violations));
//	}
	
	
	

}
