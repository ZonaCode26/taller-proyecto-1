package com.taller.proyecto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taller.proyecto.model.Usuario;
import com.taller.proyecto.repository.ILoginRepo;
import com.taller.proyecto.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService{
	
	@Autowired
	private PasswordEncoder bcrypt;
	//private BCryptPasswordEncoder bcrypt;	
	
	@Autowired
	private ILoginRepo repo;

	@Override
	public Usuario verificarNombreUsuario(String usuario) {
		return repo.verificarNombreUsuario(usuario);
	}

	@Override
	public void cambiarClave(String clave, String nombre) {
		bcrypt = 	new BCryptPasswordEncoder();
		repo.cambiarClave(bcrypt.encode(clave), nombre);
	}
	
	

}
