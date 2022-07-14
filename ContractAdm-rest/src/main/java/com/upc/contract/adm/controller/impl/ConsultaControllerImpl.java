package com.upc.contract.adm.controller.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.upc.contract.adm.controller.ConsultaController;
import com.upc.contract.core.entity.Documento;
import com.upc.contract.core.entity.Envio;
import com.upc.contract.core.service.ConsultaService;

@RestController
@RequestMapping("/consulta")
public class ConsultaControllerImpl implements ConsultaController{

	@Autowired
	private ConsultaService consultaService;
	private Logger logger = LoggerFactory.getLogger(ConsultaControllerImpl.class);
	
	@Override
	@GetMapping("/listarEnvios")
	public List<Envio> listarEnvios() {
		try {
			List<Envio> lstEnvias = consultaService.listarEnvios();
			return lstEnvias;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	@GetMapping("/listarDocumentos/{idEnvio}")
	public List<Documento> listarDocumentos(@PathVariable(name = "idEnvio")Long idEnvio) {
		try {
			logger.info(idEnvio.toString());
			List<Documento> lstDocumento = consultaService.listarDocumentos(idEnvio);
			return lstDocumento;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	

}
