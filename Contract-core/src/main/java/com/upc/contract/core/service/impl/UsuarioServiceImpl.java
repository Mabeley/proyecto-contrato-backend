package com.upc.contract.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.contract.core.entity.Usuario;
import com.upc.contract.core.repository.UsuarioRepository;
import com.upc.contract.core.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario userValidate(String usuario, String clave) throws Exception {
		return usuarioRepository.userValidate(usuario, clave);
	}

}
