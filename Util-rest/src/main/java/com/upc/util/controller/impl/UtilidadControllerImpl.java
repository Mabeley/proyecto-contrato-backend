package com.upc.util.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upc.util.controller.UtilidadController;
import com.upc.util.service.UtilidadService;

@RestController
@RequestMapping("/utilidad")
public class UtilidadControllerImpl implements UtilidadController {

	@Autowired
	private UtilidadService utilService;
	private Logger logger = LoggerFactory.getLogger(UtilidadControllerImpl.class);

	@Override
	@PostMapping("/notificar")
	public Object enviarCorreo(@RequestBody Map<String, Object> datos) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			String rsp = utilService.enviarCorreo(datos);
			response.put("estado", "OK");
			response.put("mensaje", rsp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.put("estado", "ERROR");
			response.put("mensaje", e.getMessage());
		}
		return response;
	}

}
