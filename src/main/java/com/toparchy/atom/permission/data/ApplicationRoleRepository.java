package com.toparchy.atom.permission.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.toparchy.atom.permission.model.entity.ApplicationRoleEntity;

@ApplicationScoped
public class ApplicationRoleRepository {
	@Inject
	private EntityManager atomEm;

	public ApplicationRoleEntity findById(String id) {
		return atomEm.find(ApplicationRoleEntity.class, id);
	}

	public ApplicationRoleEntity findByName(String name) {
		CriteriaBuilder cb = atomEm.getCriteriaBuilder();
		CriteriaQuery<ApplicationRoleEntity> criteria = cb.createQuery(ApplicationRoleEntity.class);
		Root<ApplicationRoleEntity> applicationRole = criteria.from(ApplicationRoleEntity.class);
		criteria.select(applicationRole).where(cb.equal(applicationRole.get("name"), name));
		return atomEm.createQuery(criteria).getSingleResult();
	}

	public List<ApplicationRoleEntity> findAll() {
		CriteriaBuilder cb = atomEm.getCriteriaBuilder();
		CriteriaQuery<ApplicationRoleEntity> criteria = cb.createQuery(ApplicationRoleEntity.class);
		Root<ApplicationRoleEntity> applicationRole = criteria.from(ApplicationRoleEntity.class);
		criteria.select(applicationRole);
		return atomEm.createQuery(criteria).getResultList();
	}
}
