package com.upc.contract.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.upc.contract.core.entity.Documento;

public interface DocumentoRepository extends CrudRepository<Documento, Long> {

	@Query("SELECT d FROM Documento d WHERE d.idEnvio = :idEnvio")
	List<Documento> listarDocumentos(@Param("idEnvio") Long idEnvio);

}
