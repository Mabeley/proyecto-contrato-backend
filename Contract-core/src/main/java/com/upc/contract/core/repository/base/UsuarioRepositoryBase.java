package com.upc.contract.core.repository.base;

import com.upc.contract.core.entity.Usuario;

public interface UsuarioRepositoryBase {
	Usuario userValidate(String usuario, String clave) throws Exception;
}
