package com.toparchy.atom.permission.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.atom.permission.model.entity.ApplicationRole;

@ApplicationScoped
public class ApplicationRoleRepository {
	@Inject
	private EntityManager atomEm;

	public ApplicationRole findById(String id) {
		return atomEm.find(ApplicationRole.class, id);
	}

	public ApplicationRole findByKey(String key) {
		CriteriaBuilder cb = atomEm.getCriteriaBuilder();
		CriteriaQuery<ApplicationRole> criteria = cb.createQuery(ApplicationRole.class);
		Root<ApplicationRole> applicationRole = criteria.from(ApplicationRole.class);
		criteria.select(applicationRole).where(cb.equal(applicationRole.get("key"), key));
		return atomEm.createQuery(criteria).getSingleResult();
	}

	public List<ApplicationRole> findAll() {
		CriteriaBuilder cb = atomEm.getCriteriaBuilder();
		CriteriaQuery<ApplicationRole> criteria = cb.createQuery(ApplicationRole.class);
		Root<ApplicationRole> applicationRole = criteria.from(ApplicationRole.class);
		criteria.select(applicationRole);
		return atomEm.createQuery(criteria).getResultList();
	}
}
