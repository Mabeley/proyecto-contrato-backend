package com.upc.contract.core.service;

import java.util.List;
import java.util.Map;

import com.upc.contract.core.entity.Envio;

public interface ProcesoService {
	Long guardarEnvio(Envio envio) throws Exception;
	Long obtenerIdEnvio() throws Exception;
	List<Map<String, Object>> obtenerRegistros(String idEnvio,String nombre) throws Exception;
	void guardarRegistros(Envio envio) throws Exception;
}
