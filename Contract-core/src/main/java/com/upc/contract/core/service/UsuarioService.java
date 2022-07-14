package com.upc.contract.core.service;

import com.upc.contract.core.entity.Usuario;

public interface UsuarioService {
	Usuario userValidate(String usuario, String clave) throws Exception;
	Usuario userUpdate(Usuario oUsuario) throws Exception;
}
