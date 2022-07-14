package com.upc.util.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;

public interface UtilidadController {
	@PostMapping("/notificar")
	Object enviarCorreo(Map<String, Object> datos);
}
