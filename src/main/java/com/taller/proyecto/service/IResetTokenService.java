package com.taller.proyecto.service;

import com.taller.proyecto.model.ResetToken;

public interface IResetTokenService {

	ResetToken findByToken(String token);
	
	void guardar(ResetToken token);
	
	void eliminar(ResetToken token);

}
