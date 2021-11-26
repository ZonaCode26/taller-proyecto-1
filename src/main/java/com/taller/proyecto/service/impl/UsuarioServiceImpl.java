package com.taller.proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taller.proyecto.model.Usuario;
import com.taller.proyecto.repository.IUsuarioRepo;
import com.taller.proyecto.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

	@Autowired
	private IUsuarioRepo repo;	
	

	@Override
	public Usuario findUserByEmail(String email) {
		Usuario usuario = repo.findFirstByEmail(email);
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", email));
		}
		return usuario;
		
	}

}
