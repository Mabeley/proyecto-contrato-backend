package com.upc.contract.adm.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.upc.contract.adm.controller.ProcesoController;
import com.upc.contract.core.entity.Envio;
import com.upc.contract.core.entity.TipoContrato;
import com.upc.contract.core.service.ProcesoService;
import com.upc.contract.core.service.TipoContratoService;
import com.upc.contract.core.util.ConstantesCore;
import com.upc.contract.core.util.ResponseCore;
import com.upc.contract.core.util.UtilCore;

@RestController
@RequestMapping("/proceso")
public class ProcesoControllerImpl implements ProcesoController {
	@Autowired
	private TipoContratoService tipoContratoService;
	@Autowired
	private ProcesoService procesoService;
	private Logger logger = LoggerFactory.getLogger(ProcesoControllerImpl.class);

	@Override
	@GetMapping("/listartipos")
	public List<TipoContrato> contratosDisponibles() {
		try {
			System.out.println("==================>"+System.getenv("IPPUBLICA"));
			List<TipoContrato> lstContratos = tipoContratoService.contratosDisponibles();
			return lstContratos;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	@Override
	@PostMapping("/cargarData")
	public ResponseCore cargarEnvio(@RequestBody Envio envio) {
		Long idEnvio;
		ResponseCore response = new ResponseCore();
		try {
			logger.debug("file " + envio.getDatos());
			idEnvio = procesoService.guardarEnvio(envio);
			if (UtilCore.isNullOrEmpty(idEnvio)) {
				response.setEstadoRespuesta(ConstantesCore.RSP_ERROR);
				response.setMensajeRespuesta("Problemas al guardar");
			} else {
				Map<String, Object> parametro = new HashMap<>();
				parametro.put("idEnvio", idEnvio);
				response.setParametros(parametro);
				response.setEstadoRespuesta(ConstantesCore.RSP_OK);
				response.setMensajeRespuesta("Data guardada correctamente");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setEstadoRespuesta(ConstantesCore.RSP_ERROR);
			response.setMensajeRespuesta(e.getMessage());
		}
		return response;
	}

	@Override
	@GetMapping("/listarMuestra/{idEnvio}/{nombre}")
	public ResponseCore listarMuestra(@PathVariable(value = "idEnvio") String idEnvio,
			@PathVariable(value = "nombre") String nombre) {
		ResponseCore response = new ResponseCore();
		try {
			logger.debug("idEnvio " + idEnvio);
			List<Map<String, Object>> lstDocumentos = procesoService.obtenerRegistros(idEnvio, nombre);
			if (UtilCore.isNullOrEmpty(lstDocumentos)) {
				response.setEstadoRespuesta(ConstantesCore.RSP_ERROR);
				response.setMensajeRespuesta("No hay registros para la muestra");
			} else {
				Map<String, Object> parametro = new HashMap<>();
				parametro.put("lstDocumentos", lstDocumentos);
				response.setParametros(parametro);
				response.setEstadoRespuesta(ConstantesCore.RSP_OK);
				response.setMensajeRespuesta("Muestra completa");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setEstadoRespuesta(ConstantesCore.RSP_ERROR);
			response.setMensajeRespuesta(e.getMessage());
		}
		return response;
	}

	@Override
	@PostMapping("/procesarEnvio")
	public ResponseCore guardarRegistros(@RequestBody Envio envio) {
		ResponseCore response = new ResponseCore();
		try {
			logger.debug("idEnvio " + envio.getIdEnvio());
			procesoService.guardarRegistros(envio);
			response.setEstadoRespuesta(ConstantesCore.RSP_OK);
			response.setMensajeRespuesta("Datos procesados correctamente");

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setEstadoRespuesta(ConstantesCore.RSP_ERROR);
			response.setMensajeRespuesta(e.getMessage());
		}
		return response;
	}

}
