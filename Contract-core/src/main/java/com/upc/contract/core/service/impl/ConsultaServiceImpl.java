package com.upc.contract.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.contract.core.entity.Documento;
import com.upc.contract.core.entity.Envio;
import com.upc.contract.core.repository.DocumentoRepository;
import com.upc.contract.core.repository.EnvioRepository;
import com.upc.contract.core.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService{

	@Autowired
	private EnvioRepository envioRepository;
	@Autowired
	private DocumentoRepository documentoRepository;
	
	@Override
	public List<Envio> listarEnvios() throws Exception {
		return (List<Envio>) envioRepository.findAll();
	}

	@Override
	public List<Documento> listarDocumentos(Long idEnvio) throws Exception {
		return documentoRepository.listarDocumentos(idEnvio);
	}

}
