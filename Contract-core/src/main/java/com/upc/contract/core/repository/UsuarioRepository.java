package com.upc.contract.core.repository;

import org.springframework.data.repository.CrudRepository;

import com.upc.contract.core.entity.Usuario;
import com.upc.contract.core.repository.base.UsuarioRepositoryBase;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>, UsuarioRepositoryBase {
}
