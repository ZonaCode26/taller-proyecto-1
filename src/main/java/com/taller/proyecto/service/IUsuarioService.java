package com.taller.proyecto.service;

import com.taller.proyecto.model.Usuario;

public interface IUsuarioService {

	Usuario findUserByEmail(String email);
}
