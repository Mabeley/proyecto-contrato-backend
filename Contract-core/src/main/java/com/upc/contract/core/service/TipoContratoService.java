package com.upc.contract.core.service;

import java.util.List;

import com.upc.contract.core.entity.TipoContrato;

public interface TipoContratoService {
	List<TipoContrato> contratosDisponibles() throws Exception;
}
