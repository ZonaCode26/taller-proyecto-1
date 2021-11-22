package com.taller.proyecto.repository;

import com.taller.proyecto.model.Usuario;

public interface IUsuarioRepo extends IGenericRepo<Usuario, Integer>  {

	//select * from usuario where username = ?
	//Usuario findOneByUsername(String username);	
	
	Usuario findFirstByEmail(String email);	
}
