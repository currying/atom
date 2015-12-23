package com.toparchy.atom.permission.data;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.toparchy.atom.permission.model.entity.MemberEntity;

@ApplicationScoped
public class MemberEntityRepository {
	@Inject
	private EntityManager atomEm;

	public MemberEntity findById(String id) {
		return atomEm.find(MemberEntity.class, id);
	}
}
