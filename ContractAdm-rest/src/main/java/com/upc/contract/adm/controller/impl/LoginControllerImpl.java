package com.upc.contract.adm.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upc.contract.adm.controller.LoginController;
import com.upc.contract.adm.util.TokenUtil;
import com.upc.contract.core.entity.Usuario;
import com.upc.contract.core.service.UsuarioService;
import com.upc.contract.core.util.ConstantesCore;
import com.upc.contract.core.util.ResponseCore;
import com.upc.contract.core.util.UtilCore;

@RestController
@RequestMapping("/seguridad")
public class LoginControllerImpl implements LoginController {
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	TokenUtil tokenUtil;
	
	Logger logger = LoggerFactory.getLogger(LoginControllerImpl.class);

	@Override
	@PostMapping("/acceso")
	public ResponseCore validarAcceso(@RequestBody Usuario usuario) {
		Usuario oUsuario;
		ResponseCore response = new ResponseCore();
		try { 
			logger.debug("usuario "+usuario.getCodUsuario());
			oUsuario = usuarioService.userValidate(usuario.getCodUsuario(), UtilCore.generateSHA256(usuario.getClave()));
			if(UtilCore.isNullOrEmpty(oUsuario)) {
				response.setEstadoRespuesta(ConstantesCore.RSP_ERROR);
				response.setMensajeRespuesta("usuario o clave incorrectos");
			}else {
				Map<String, Object> parametro = new HashMap<>();
				oUsuario.setToken(this.tokenUtil.getJWTToken(usuario.getCodUsuario()));
				parametro.put("usuario", oUsuario);
				response.setParametros(parametro);
				response.setEstadoRespuesta(ConstantesCore.RSP_OK);
				response.setMensajeRespuesta("Acceso correcto");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			response.setEstadoRespuesta(ConstantesCore.RSP_ERROR);
			response.setMensajeRespuesta(e.getMessage());
		}
		return response;
	}

	@Override
	@PostMapping("/cambiar")
	public ResponseCore actualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
