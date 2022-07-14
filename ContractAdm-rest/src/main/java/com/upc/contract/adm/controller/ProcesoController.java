package com.upc.contract.adm.controller;

import java.util.List;

import com.upc.contract.core.entity.Envio;
import com.upc.contract.core.entity.TipoContrato;
import com.upc.contract.core.util.ResponseCore;

public interface ProcesoController {
	List<TipoContrato> contratosDisponibles();
	ResponseCore cargarEnvio(Envio envio);
	ResponseCore listarMuestra(String idEnvio, String nombre);
	ResponseCore guardarRegistros(Envio envio);
}
