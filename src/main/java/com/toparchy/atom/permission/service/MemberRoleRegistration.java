package com.toparchy.atom.permission.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.picketlink.idm.IdentityManager;
import org.picketlink.idm.PartitionManager;
import org.picketlink.idm.RelationshipManager;
import org.picketlink.idm.model.basic.BasicModel;
import org.picketlink.idm.model.basic.Role;

import com.toparchy.atom.permission.model.Member;
import com.toparchy.atom.permission.model.entity.ApplicationRoleEntity;

@Stateless
public class MemberRoleRegistration {
	@Inject
	private EntityManager atomEm;
	@Inject
	private PartitionManager partitionManager;
	@Inject
	private RelationshipManager relationshipManager;
	@Inject
	private IdentityManager identityManager;

	public void grantRoleToMember(Member member, ApplicationRoleEntity applicationRole) {
		Role role = BasicModel.getRole(identityManager, applicationRole.getName());
		if (!BasicModel.hasRole(relationshipManager, member, role))
			BasicModel.grantRole(relationshipManager, member, role);
	}

	public void revokeRoleFromMember(Member member, ApplicationRoleEntity applicationRole) {
		Role role = BasicModel.getRole(identityManager, applicationRole.getName());
		BasicModel.revokeRole(relationshipManager, member, role);
	}

}
