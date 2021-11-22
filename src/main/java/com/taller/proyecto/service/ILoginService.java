package com.taller.proyecto.service;

import com.taller.proyecto.model.Usuario;

public interface ILoginService {

	Usuario verificarNombreUsuario(String usuario);
	void cambiarClave(String clave, String nombre);
}
