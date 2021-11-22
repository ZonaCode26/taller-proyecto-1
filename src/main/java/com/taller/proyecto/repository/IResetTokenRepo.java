package com.taller.proyecto.repository;

import com.taller.proyecto.model.ResetToken;

public interface IResetTokenRepo extends IGenericRepo<ResetToken, Integer>{
	
	//from ResetToken rt where rt.token = :?
	ResetToken findByToken(String token);

}
