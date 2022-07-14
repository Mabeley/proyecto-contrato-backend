package com.upc.contract.core.repository.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.upc.contract.core.entity.Usuario;
import com.upc.contract.core.util.UtilCore;

@Service
public class UsuarioRepositoryBaseImpl implements UsuarioRepositoryBase {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Usuario userValidate(String usuario, String clave) throws Exception {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Usuario> query = cb.createQuery(Usuario.class);
		Root<Usuario> root = query.from(Usuario.class);
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(cb.equal(root.get("codUsuario"), (String) usuario));
		predicates.add(cb.equal(root.get("clave"), (String) clave));
		query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
		List<Usuario> lstRsp = entityManager.createQuery(query).getResultList();
		if(!UtilCore.isNullOrEmpty(lstRsp)) {
			return lstRsp.get(0);
		}
		return null;
	}

}
