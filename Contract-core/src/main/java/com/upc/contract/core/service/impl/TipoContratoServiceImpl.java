package com.upc.contract.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upc.contract.core.entity.TipoContrato;
import com.upc.contract.core.repository.TipoContratoRepository;
import com.upc.contract.core.service.TipoContratoService;

@Service
public class TipoContratoServiceImpl implements TipoContratoService {
	
	@Autowired
	private TipoContratoRepository tipoContratoRepository;

	@Override
	public List<TipoContrato> contratosDisponibles() throws Exception {
		return (List<TipoContrato>) tipoContratoRepository.findAll();
	}

}
