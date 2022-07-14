package com.upc.contract.core.service;

import java.util.List;

import com.upc.contract.core.entity.Documento;
import com.upc.contract.core.entity.Envio;

public interface ConsultaService {
	List<Envio> listarEnvios() throws Exception;
	List<Documento> listarDocumentos(Long idEnvio) throws Exception;
}
