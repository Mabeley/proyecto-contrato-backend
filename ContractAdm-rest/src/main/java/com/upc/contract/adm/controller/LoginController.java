package com.upc.contract.adm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.upc.contract.core.entity.Usuario;
import com.upc.contract.core.util.ResponseCore;


public interface LoginController {
	
	@PostMapping("/acceso")
	ResponseCore validarAcceso(@RequestBody Usuario usuario);
}
