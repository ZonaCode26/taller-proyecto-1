package com.taller.proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taller.proyecto.model.ResetToken;
import com.taller.proyecto.repository.IResetTokenRepo;
import com.taller.proyecto.service.IResetTokenService;

@Service
public class ResetTokenServiceImpl implements IResetTokenService {

	@Autowired
	private IResetTokenRepo repo;
	
	@Override
	public ResetToken findByToken(String token) {
		return repo.findByToken(token);
	}

	@Override
	public void guardar(ResetToken token) {
		repo.save(token);
	}

	@Override
	public void eliminar(ResetToken token) {
		repo.delete(token);
	}

}
