package com.upc.contract.adm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.upc.contract.core.entity.Documento;
import com.upc.contract.core.entity.Envio;

public interface ConsultaController {
	@GetMapping("/listarEnvios")
	List<Envio> listarEnvios();
	@GetMapping("/listarDocumentos/{idEnvio}")
	List<Documento> listarDocumentos(@PathVariable(name = "idEnvio") Long idEnvio);
}
