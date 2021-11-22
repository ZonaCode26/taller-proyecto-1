package com.taller.proyecto.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.taller.proyecto.model.Usuario;
import com.taller.proyecto.repository.IUsuarioRepo;

@Service
public class UsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private IUsuarioRepo repo;	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = repo.findFirstByEmail(username);
		
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		});
		PasswordEncoder bcrypt = 	new BCryptPasswordEncoder();
		
		UserDetails ud = new User(usuario.getEmail(), bcrypt.encode(usuario.getPassword()), roles);
		
		return ud;
	}

}
